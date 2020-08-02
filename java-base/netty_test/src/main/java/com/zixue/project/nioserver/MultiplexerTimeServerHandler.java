package com.zixue.project.nioserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;

public class MultiplexerTimeServerHandler implements Runnable {

    private Selector selector = null;
    private ServerSocketChannel servChannel = null;
    private int port;
    private boolean stop;

    /**
     * ��ʼ����·���������󶨼����˿�
     *
     * @param port
     */
    public MultiplexerTimeServerHandler(int port) {
        try {
            selector = Selector.open();
            servChannel = ServerSocketChannel.open();
            //������
            servChannel.configureBlocking(false);
            servChannel.socket().bind(new InetSocketAddress(port), 1024);
            //��ͨ����ע��selector,����Ȥ�¼���OP_ACCEPT
            servChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("����������" + port);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }


    }

    public void stop() {
        this.stop = true;
    }

    public void run() {
        while (!stop) {
		//ѭ����������ʱ��
            try {
                selector.select(1000);
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> it = selectionKeys.iterator();
                SelectionKey key = null;
                while (it.hasNext()) {
                    key = it.next();
                    it.remove();
                    try {
                        handleInput(key);
                    } catch (IOException e) {
                        if (key != null) {
                            key.cancel();
                            if (key.channel() != null)
                                key.channel().close();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
        //��·�������رպ�����ע���channel��pipe����Դ�����Զ�ȥע�Ტ�ر�
        // �����в���Ҫ�ظ��ͷ���Դ
        if (selector != null) {
            try {
                selector.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
	//�����¼�
    private void handleInput(SelectionKey key) throws IOException {
        if (key.isValid()) {
            if (key.isAcceptable()) {
                ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                SocketChannel sc = ssc.accept();
                sc.configureBlocking(false);
                sc.register(selector, SelectionKey.OP_READ);
            }
            if (key.isReadable()) {
                SocketChannel sc = (SocketChannel) key.channel();
                ByteBuffer readBuff = ByteBuffer.allocate(1024);
                //���������������Ƿ�������
                int read = sc.read(readBuff);
                if (read > 0) {
                    readBuff.flip();
                    byte[] bytes = new byte[readBuff.remaining()];
                    readBuff.get(bytes);
                    String body = new String(bytes, "utf-8");
                    System.out.println("�����յ����" + body);
                    String currentTime = "time".equals(body) ? new Date(System.currentTimeMillis()).toString() : "��Ч����";
                    doWrite(sc, currentTime);
                } else if (read < 0) {
                    //�Զ���·�ر�
                    key.cancel();
                    sc.close();
                } else {
                    //����0�ֽ�
                }
            }
        }
    }

    private void doWrite(SocketChannel sc, String content) throws IOException {
        if (content != null && content.trim().length() > 0) {
            byte[] bytes = content.getBytes();
            ByteBuffer byteBuffer = ByteBuffer.allocate(bytes.length);
            byteBuffer.put(bytes);
            byteBuffer.flip();
            //���ܴ���д������
            sc.write(byteBuffer);
        }
    }
}
