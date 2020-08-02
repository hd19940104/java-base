package com.zixue.chatroom.net.server.handle;

import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zixue.chatroom.net.clink.utils.CloseUtils;
import com.zixue.chatroom.net.server.TCPServer;
import com.zixue.chatroom.net.server.bean.ClientInfo;
/**
 * 
 * @ClassName  ClientHandler 
 * @Description 客户端处理类
 * @author 一只会飞的小猴子
 * @date  2019年8月14日 下午5:14:06 
 *
 */
public class ClientHandler {
    private final SocketChannel socketChannel;
    /**
     * 读取客户端
     */
    private final ClientReadHandler readHandler;
    /**
     * 给客户端发送
     */
    private final ClientWriteHandler writeHandler;
    /**
     * 客户端回调接口
     */
    private final ClientHandlerCallback clientHandlerCallback;
    /**
     * 客户端实体
     */
    private final ClientInfo clientInfo=new ClientInfo();
    /**
     * 
     * Title  
     * Description  客户端处理类构造方法
     * @param socket
     * @param clientHandlerCallback
     * @throws IOException
     */
    public ClientHandler(SocketChannel socketChannel, ClientHandlerCallback clientHandlerCallback) throws IOException {
        this.socketChannel = socketChannel;
        //设置非阻塞
        socketChannel.configureBlocking(false);
        
        Selector reradSelector = Selector.open();
        socketChannel.register(reradSelector, SelectionKey.OP_READ);
        
        Selector weiteSelector = Selector.open();
        socketChannel.register(weiteSelector, SelectionKey.OP_WRITE);
        
        
        this.readHandler = new ClientReadHandler(reradSelector);
        this.writeHandler = new ClientWriteHandler(weiteSelector);
        this.clientHandlerCallback = clientHandlerCallback;
        this.clientInfo.setAddress(socketChannel.getRemoteAddress().toString().split(":")[0].replace("/", ""));
        this.clientInfo.setPort(Integer.parseInt(socketChannel.getRemoteAddress().toString().split(":")[1]));
        System.out.println("新客户端连接：" + clientInfo);
    }
    public ClientInfo getClientInfo() {
        return clientInfo;
    }
    public void exit() {
        readHandler.exit();
        writeHandler.exit();
        CloseUtils.close(socketChannel);
        System.out.println("客户端已退出：" + clientInfo.toString());
    }

    public void send(String str) {
        writeHandler.send(str);
    }

    public void readToPrint() {
        readHandler.start();
    }

    private void exitBySelf() {
        exit();
        clientHandlerCallback.onSelfClosed(this);
    }
    /**
     * 
     * @ClassName  ClientHandlerCallback 
     * @Description 回调接口
     * @author 一只会飞的小猴子
     * @date  2019年8月14日 下午5:16:37 
     *
     */
    public interface ClientHandlerCallback {
    	/**
    	 * 
    	 * @Title  onSelfClosed 
    	 * @Description  关闭通知
    	 * @param handler void
    	 */
        void onSelfClosed(ClientHandler handler);
        /**
         * 
         * @Title  onNewMessageArrived 
         * @Description  收到消息通知
         * @param handler
         * @param msg void
         */
        void onNewMessageArrived(ClientHandler handler,String msg);
        
    }

    class ClientReadHandler extends Thread {
        private boolean done = false;
        private final Selector selector;
        private final ByteBuffer byteBuffer;
        

        ClientReadHandler(Selector selector) {
            this.selector = selector;
            this.byteBuffer = ByteBuffer.allocate(256);
        }

        @Override
        public void run() {
            super.run();
            try {
                do {
                    // 客户端拿到一条数据
                	if (selector.select()==0) {
						if (done) {
							break;
						}
						continue;
					}
                	Iterator<SelectionKey> iterator = selector.selectedKeys().iterator();
                	 while (iterator.hasNext()) {
 						if (done) {
 							break;
 						}
                 		SelectionKey selectionKey = (SelectionKey) iterator.next();
 						iterator.remove();
 						//检查当前key的状态是否使我们关注的
 						//客户端到达状态
 						if (selectionKey.isReadable()) {
 							SocketChannel channel = (SocketChannel)selectionKey.channel();
 							ByteBuffer readBuff = ByteBuffer.allocate(1024);
 							
 							byteBuffer.clear();
 							int read = channel.read(byteBuffer);
 							if (read>0) {
 								//丢弃换行符
 								String str = new String(byteBuffer.array(),0,byteBuffer.position()-1);
 	 		                    //通知其他客户端
 	 		                    clientHandlerCallback.onNewMessageArrived(ClientHandler.this, str);
							}
 							if (read==0) {
	 		                        System.out.println("客户端已无法读取数据！");
	 		                        // 退出当前客户端
	 		                        ClientHandler.this.exitBySelf();
	 		                        break;
	 		                    }
 							
 						}
 					}
                } while (!done);
            } catch (Exception e) {
                if (!done) {
                    System.out.println("连接异常断开");
                    ClientHandler.this.exitBySelf();
                }
            } finally {
                // 连接关闭
                CloseUtils.close(selector);
            }
        }

        void exit() {
            done = true;
            CloseUtils.close(selector);
        }
    }

    class ClientWriteHandler {
        private boolean done = false;
        private final Selector weiteSelector;
        private final ExecutorService executorService;
        private final ByteBuffer byteBuffer;
        
        ClientWriteHandler(Selector weiteSelector) {
            this.weiteSelector = weiteSelector;
            this.byteBuffer = ByteBuffer.allocate(256);
            this.executorService = Executors.newSingleThreadExecutor();
        }

        void exit() {
            done = true;
            CloseUtils.close(weiteSelector);
            executorService.shutdownNow();
        }

        void send(String str) {
            executorService.execute(new WriteRunnable(str));
        }

        class WriteRunnable implements Runnable {
            private final String msg;

            WriteRunnable(String msg) {
                this.msg = msg;
            }

            @Override
            public void run() {
                if (ClientWriteHandler.this.done) {
                    return;
                }
                byteBuffer.clear();
                byteBuffer.put(msg.getBytes());
                byteBuffer.flip();//反转
                
                while(!done && byteBuffer.hasRemaining()) {
                	 try {
                     	int write = socketChannel.write(byteBuffer);
                     	if (write<0) {
							System.out.println("客户端无法发送数据");
							ClientHandler.this.exitBySelf();
							break;
                     	}
                     } catch (Exception e) {
                         e.printStackTrace();
                     }
                	
                	
                }
               
            }
        }
    }
}
