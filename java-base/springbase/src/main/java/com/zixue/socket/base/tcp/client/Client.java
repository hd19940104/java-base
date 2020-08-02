package com.zixue.socket.base.tcp.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Inet4Address;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

/**
 * 
 * @ClassName Client
 * @Description TCP 客户端
 * @author 一只会飞的小猴子
 * @date 2019年8月12日 下午1:36:05
 *
 */
public class Client {
	public static void main(String[] args) throws UnknownHostException, IOException {
		Socket socket = null;
		try {
			socket = new Socket();
			// 超时时间
			socket.setSoTimeout(3000);
			socket.connect(new InetSocketAddress(Inet4Address.getLocalHost(), 20002), 3000);
			 System.out.println("已发起服务器连接，并进入后续流程～");
		        System.out.println("客户端信息-->" + socket.getLocalAddress() + " P-->" + socket.getLocalPort());
		        System.out.println("服务器信息-->" + socket.getInetAddress() + " P-->" + socket.getPort());

			send(socket);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			 System.out.println("异常关闭");
		}
		  System.out.println("客户端已退出～");
	}

	public static void send(Socket socket) throws IOException, InterruptedException {
		PrintWriter out = null;
		BufferedReader in =null;
		try {
			InputStream key_in = System.in;
			BufferedReader key_input = new BufferedReader(new InputStreamReader(key_in));

			out = new PrintWriter(socket.getOutputStream());
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			boolean flag = true;
			while (flag) {
				// 键盘读取一行
				String readLine = key_input.readLine();
				// 发送到服务器
				out.println(readLine);
				out.flush();
				// 从服务器读取
				String line = in.readLine();
				System.out.println("接受服务器信息-->"+line);
				if ("bye".equalsIgnoreCase(line)) {
					flag = false;
				}
			}

		} catch (SocketTimeoutException e)
        {
			System.out.println(socket.isClosed()+" "+socket.isConnected());
            if (!socket.isClosed() && socket.isConnected())
                System.out.println("读取数据超时!");
            else
                System.out.println("连接超时");
        } finally {
			socket.close();
			out.close();
			in.close();
		}
	}
}
