package com.zixue.socket.base.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.UUID;

/**
 * 
 * @ClassName  UDPProvider 
 * @Description UDP提供者
 * @author 一只会飞的小猴子
 * @date  2019年8月13日 上午9:27:11 
 *
 */
public class UDPProvider {
	public static void main(String[] args) throws IOException {
		String sn = UUID.randomUUID().toString();
		Provider provider = new Provider(sn);
		provider.start();
		
		//读取任意键盘信息退出
		System.in.read();
		provider.exit();
	}
	
	
	private static class Provider extends Thread{
		private final String sn;
		private boolean done = false;
		private DatagramSocket ds = null;
		
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println("UDPProvider started");
			try {
				//构造监听
				ds = new DatagramSocket(20001);
			} catch (SocketException e) {
				System.out.println("异常通过");
			}
			while (!done) {
				
				//构造接收实体
				final byte[] buf = new byte[1024];
				DatagramPacket receivePack = new DatagramPacket(buf, buf.length);
				try {
					ds.receive(receivePack);
				} catch (IOException e) {
					System.out.println("异常通过");
				}
				//接收信息
				String ip = receivePack.getAddress().getHostAddress();
				int datalen = receivePack.getLength();
				String data = new String(receivePack.getData(),0,datalen);
				System.out.println("ip:"+ip+"============data:"+data);
				int resposePort = MessageCreate.parsePort(data);
				if (resposePort!=-1) {
					String resposeData = MessageCreate.buildWithSn(sn);
					byte[] resposeBytes =resposeData.getBytes();
					DatagramPacket resposePack = new DatagramPacket(resposeBytes, 
																	resposeBytes.length,
																	receivePack.getAddress(),
																	resposePort);
					try {
						ds.send(resposePack);
					} catch (IOException e) {
						System.out.println("异常通过");
					}
					System.out.println("UDPProvider send");
				}
				
			}
			
		}
		public Provider(String sn) {
			super();
			this.sn =sn;
		}
		void exit() {
			done=true;
			close();
		}
		private void close() {
			if (ds!=null) {
				ds.close();
				ds =null;
			}
		}
	}
}
