package com.zixue.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
 
/**
 * �������ڣ���������Ӧ��
 *Timely communication
 *springboot ��������ʱ����� new TCNettyServer(8888).run();
 */
public class TCNettyServer {
 
	private int port;
 
	public TCNettyServer(int port) {
		this.port = port;
	}
 
	public void run() {
		// ��������Reactor�̳߳ء�netty�ǻ���NIO�ģ������̴߳���ġ�
		// ���ڽ���Client�����ӵ�
		EventLoopGroup bossGroup = new NioEventLoopGroup();
		// ��������ͨ�Ŷ�д
		EventLoopGroup workGroup = new NioEventLoopGroup();
		try {
			// ����һ��������Bootstrap�����Ƕ����ǵ�Server����һϵ�е�����
			ServerBootstrap b = new ServerBootstrap();
			b.group(bossGroup, workGroup);
			// ָ��ʹ��NioServerSocketChannel�������͵�ͨ��
			b.channel(NioServerSocketChannel.class);
			/*
			 * ʹ�� childHandler ȥ��ʼ�������� ���handler�����������Ѿ����ӵĿͻ��˵�Channel�Ķ�����״̬��
			 * 
			 * ���󶨵�MyWebSocketChannelHandler()���������˷���˳�ʼ�������Լ�
			 */
			b.childHandler(new TCWebSocketChannelHandler());
 
			System.out.println("netty����˿����ȴ��ͻ�������....");
			Channel ch = b.bind(port).sync().channel();
			ch.closeFuture().sync();
 
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// ���ŵ��˳�����
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
	}
 
	public static void main(String[] args) {
		new TCNettyServer(8080).run();
	}
 
}
