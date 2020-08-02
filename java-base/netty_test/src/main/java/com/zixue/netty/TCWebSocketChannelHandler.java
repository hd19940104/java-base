package com.zixue.netty;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
 
import java.util.concurrent.TimeUnit;
 
/**
 * ��ʼ������ʱ��ĸ������
 * Timely communication
 *
 */
public class TCWebSocketChannelHandler extends ChannelInitializer<SocketChannel> {
 
	@Override
	protected void initChannel(SocketChannel e) throws Exception {
		e.pipeline().addLast("http-codec", new HttpServerCodec());
		e.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));
		e.pipeline().addLast("http-chunked", new ChunkedWriteHandler());
		// ��Ӿ���Ĵ�����������addLast������addFirst�����handler��
		// ��һ�����������֣��޾���Ҫ�������дnull��ϵͳ���Զ�������
		e.pipeline().addLast("handler", new TCWebSocketHandler());
		/**ͨ��ʹ�ùܵ���ChannelPipeline��ʽ����������
		 * ��һ�����õĹܵ��ȴ���Ȼ���ƽ�����һ���ܵ���������ÿ���ܵ�������
		 * ����handler���Ծ����Ƿ�������ж�
		 * ChannelPipeline��ChannelHandler����������Servlet��Filter������{@link ChannelPipeline}
		 * Netty�е��¼���Ϊinbound�¼���outbound�¼���
		 * inbound�¼�ͨ����I/O�̴߳���������TCP��·�����¼�����·�ر��¼������¼����쳣֪ͨ�¼��ȡ���������file��ʼ{@link ChannelHandlerContext}
		 * outbound�¼������ڷ��͡�ˢ�¡��Ͽ����ӡ��󶨱��ص�ַ�ȹر�channel
		 */
	}
 
 
 
}
