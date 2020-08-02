package com.zixue.socket.base.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.Inet4Address;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import com.arjuna.ats.internal.arjuna.recovery.Listener;

/**
 * 
 * @ClassName  UDPSearcher 
 * @Description UDP搜索者
 * @author 一只会飞的小猴子
 * @date  2019年8月13日 上午9:27:11 
 *
 */
public class UDPSearcher {
	
	private static final int LISTEN_PORT=30000;
	public static void main(String[] args) throws IOException, InterruptedException {
		
		System.out.println("UDPSearcher started");
		Listener listener = listen();
		sendBroadcast();
		//读取任意键盘信息退出
		System.in.read();
		List<Device> devicesAndClose = listener.getDevicesAndClose();
		for (Device device : devicesAndClose) {
			System.out.println("Device:"+device.toString());
		}
	}
	/**
	 * 
	 * @throws InterruptedException 
	 * @Title  listen 
	 * @Description  监听 void
	 */
	@SuppressWarnings("unused")
	private static Listener listen() throws InterruptedException {
		CountDownLatch countDownLatch = new CountDownLatch(1);
		Listener listener =new Listener(LISTEN_PORT, countDownLatch);
		listener.start();
		
		countDownLatch.await();
		return listener;
	}
	/**
	 * 
	 * @Title  sendBroadcast 
	 * @Description  广播 void
	 */
	@SuppressWarnings("unused")
	private static void sendBroadcast() {

		DatagramSocket ds =null;
		try {
			System.out.println("广播 started");
			//作为搜索方，不用指定端口
			ds = new DatagramSocket();
			//发送
			String requestData = MessageCreate.buildWithPost(LISTEN_PORT);
			byte[] requestBytes =requestData.getBytes();
			DatagramPacket requestPack = new DatagramPacket(requestBytes,requestBytes.length);
			requestPack.setAddress(Inet4Address.getByName("255.255.255.255"));
			requestPack.setPort(20001);
			ds.send(requestPack);
			ds.close();
			System.out.println("广播 finshed");
		} catch (UnknownHostException e) {
			System.out.println("异常通过（设置广播号出错）");
		} catch (IOException e) {
			System.out.println("异常通过（发送）");
		
		} finally {
			
		}
	
	}
	
	

	private static class Device{
		int port;
		String ip;
		String sn;
		public Device(int port, String ip, String sn) {
			super();
			this.port = port;
			this.ip = ip;
			this.sn = sn;
		}
		@Override
		public String toString() {
			return "Device [port=" + port + ", ip=" + ip + ", sn=" + sn + "]";
		}
	}
	private static class Listener extends Thread{
		private final int listenPort ;
		private final CountDownLatch countDownLatch;
		private final List<Device> devices =new ArrayList<>();
		private boolean done =false;
		private DatagramSocket ds =null;
		public Listener(int listenPort, CountDownLatch countDownLatch) {
			super();
			this.listenPort = listenPort;
			this.countDownLatch = countDownLatch;
		}

		private void close() {
			if (ds!=null) {
				ds.close();
				ds =null;
			}
		}
		List<Device> getDevicesAndClose() {
			done =true;
			close();
			return devices;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			countDownLatch.countDown();
			try {
				ds = new DatagramSocket(listenPort);
				while (!done) {
					//构造接收实体
					final byte[] buf = new byte[1024];
					DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
					ds.receive(receivePack);
					//接收信息
					//发送端
					String ip = receivePack.getAddress().getHostAddress();
					int port  =receivePack.getPort();
					int datalen = receivePack.getLength();
					String data = new String(receivePack.getData(),0,datalen);
					System.out.println("ip:"+ip+"============data:"+data);
					
					String sn = MessageCreate.parseSn(data);
					if (sn!=null) {
						Device device = new Device(port, ip, sn);
						devices.add(device);
					}
				}
				
			} catch (Exception e) {
			}finally {
				close();
			}
			System.out.println("UDPSearcher finshed");
		}
	}
	
}
