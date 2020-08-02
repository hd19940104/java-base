package com.zixue.chatroom.net.server;




import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.zixue.chatroom.net.clink.utils.CloseUtils;
import com.zixue.chatroom.net.server.handle.ClientHandler;
/**
 * 
 * @ClassName  TCPServer 
 * @Description 服务器tcp
 * @author 一只会飞的小猴子
 * @date  2019年8月14日 下午5:18:37 
 *
 */
public class TCPServer implements ClientHandler.ClientHandlerCallback {
	/**
	 * 服务器tcp端口
	 */
    private final int port;
    private ClientListener mListener;
    /**
	 * 打开监听
	 */
    Selector selector;
    /**
     * 服务器server
     */
    ServerSocketChannel server;
    /**
     * 服务器接收客户端的列表
     */
    private List<ClientHandler> clientHandlerList = new ArrayList<>();
    /**
     * 转发线程池
     */
    private final ExecutorService forwardingThreadPoolExecutor;
    /**
     * 存放接受的客户端ip端口
     */
    private HashMap<Object, Object> map = new HashMap<>();
    public TCPServer(int port) {
        this.port = port;
        this.forwardingThreadPoolExecutor = Executors.newSingleThreadExecutor();
    }

    public boolean start() {
        try {
        	selector = Selector.open();
        	ServerSocketChannel server = ServerSocketChannel.open();
        	server.configureBlocking(false);//非阻塞
        	server.socket().bind(new InetSocketAddress(port));//绑定本地端口
        	server.register(selector, SelectionKey.OP_ACCEPT);//注册到监听上去
        	this.server =server;
        	
        	System.out.println("服务器信息：" + server.getLocalAddress().toString());
        	
        	//启动客户端监听
            ClientListener listener =mListener= new ClientListener();
            listener.start();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public void stop() {
        if (mListener != null) {
            mListener.exit();
        }
        CloseUtils.close(server);
        CloseUtils.close(selector);
        
        synchronized (TCPServer.this) {
            for (ClientHandler clientHandler : clientHandlerList) {
                clientHandler.exit();
            }
            clientHandlerList.clear();
        }
        // 停止线程池
        forwardingThreadPoolExecutor.shutdownNow();}

    public synchronized void broadcast(String str) {
        for (ClientHandler clientHandler : clientHandlerList) {
            clientHandler.send(str);
        }
    }
    @Override
    public synchronized void onSelfClosed(ClientHandler handler) {
        clientHandlerList.remove(handler);
        map.remove(handler.getClientInfo().getPort());
        System.out.println("map-->"+map);
        
    }

    @Override
    public void onNewMessageArrived(final ClientHandler handler, final String msg) {
        // 打印到屏幕
        System.out.println("Received-" + handler.getClientInfo() + ":" + msg);
        // 异步提交转发任务
        forwardingThreadPoolExecutor.execute(() -> {
            synchronized (TCPServer.this) {
                for (ClientHandler clientHandler : clientHandlerList) {
                    if (clientHandler.equals(handler)) {
                        // 跳过自己
                        continue;
                    }
                    // 对其他客户端发送消息
                    clientHandler.send("来自【"+handler.getClientInfo().getAddress()+":"+handler.getClientInfo().getPort() +"】-->"+ msg);
                }
            }
        });

    }
    /**
     * 开启服务器tcp监听
     */
    private class ClientListener extends Thread {
        private boolean done = false;

        @Override
        public void run() {
            super.run();
            Selector selector = TCPServer.this.selector;
            System.out.println("服务器准备就绪～");
            // 等待客户端连接
            do {
                // 得到客户端
                Socket client;
                try {
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
						if (selectionKey.isAcceptable()) {
							ServerSocketChannel channel = (ServerSocketChannel)selectionKey.channel();
							//非阻塞状态拿到客户端连接
							SocketChannel socketChannel = channel.accept();
							try {
			                	map.put(socketChannel.getRemoteAddress().toString().split(":")[1],
			                			socketChannel.getRemoteAddress().toString().split(":")[0].replace("/", ""));
			                	System.out.println("map-->"+map);
			                	// 客户端构建异步线程
			                    ClientHandler clientHandler = new ClientHandler(socketChannel, TCPServer.this);
			                    // 读取数据并打印
			                    clientHandler.readToPrint();
			                    // 添加同步处理
			                    synchronized (TCPServer.this) {
			                        clientHandlerList.add(clientHandler);
			                    }
			                } catch (IOException e) {
			                    e.printStackTrace();
			                    System.out.println("客户端连接异常：" + e.getMessage());
			                }
							
						}
					}
                } catch (IOException e) {
                   e.printStackTrace();
                }
                
            } while (!done);
            System.out.println("服务器已关闭！");
        }

        void exit() {
            done = true;
            //唤醒当前阻塞
            selector.wakeup();
        }
    }
}
