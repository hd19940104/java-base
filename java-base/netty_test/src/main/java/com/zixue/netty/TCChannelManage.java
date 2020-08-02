package com.zixue.netty;


import io.netty.channel.Channel;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;
 
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;
 
/**
 * �洢�������̵�ȫ������
 * Timely communication
 * ͨ���洢
�����ɾ��ɶ�������⼴��
 *
 */
public class TCChannelManage {
	
	/**
	 * �洢ÿһ���ͻ��˽������ʱ��channel����
	 */
	public static ChannelGroup group = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
 
	// ����
    private static ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock(true);
 
    public static ConcurrentMap<String, Channel> userIdAndChannelMap = new ConcurrentHashMap<>();
 
	public static void send(String senderId, String receiverId, String message, Channel senderChannel) {
	    // ���Ϳ϶���AҪ��B����A���Ƿ���Ϣ�Ķ���B�������ˣ������ȶ���
        try {
            rwLock.readLock().lock();
            // 1.Ѱ��receiverId��channel
            Channel receiverChannel = userIdAndChannelMap.get(receiverId);
            if (receiverChannel == null) {
                // ʹ�÷����ߵ�ͨ����֪�����ߣ���Ҫ�����Ǹ��˲�����
                senderChannel.writeAndFlush(new TextWebSocketFrame(receiverId + "������"));
                return;
            }
            // 2.���͡�A��B����B��Ҫ�յ���Ϣ����ʵ��ͨ��B��channel��B����Ϣ
            receiverChannel.writeAndFlush(new TextWebSocketFrame(senderId + "������Ϣ===��" + message));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            rwLock.readLock().unlock();
        }
    }
 
    public static boolean hasChannel(String id) {
        Channel channel = userIdAndChannelMap.get(id);
        if (channel == null) {
            return false;
        } else {
            return true;
        }
    }
 
}