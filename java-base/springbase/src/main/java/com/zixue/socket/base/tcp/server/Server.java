package com.zixue.socket.base.tcp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 
 * @ClassName Server
 * @Description TCP服务端
 * @author 一只会飞的小猴子
 * @date 2019年8月12日 下午1:36:30
 *
 */
public class Server {
	public static void main(String[] args) throws IOException {
		@SuppressWarnings("resource")
		ServerSocket serverSocket = new ServerSocket(20002);
		System.out.println("服务器准备就绪");

		for (;;) {
			// 等待
			Socket accept = serverSocket.accept();
			ClientHandler handler = new ClientHandler(accept);
			handler.start();
		}

	}

	/**
	 * 
	 * @ClassName ClientHandler
	 * @Description 异步处理类
	 * @author 一只会飞的小猴子
	 * @date 2019年8月12日 下午2:12:08
	 *
	 */
	private static class ClientHandler extends Thread {
		private Socket socket;

		ClientHandler(Socket socket) {
			this.socket = socket;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();
			System.out.println("新客户端连接-->" + socket.getInetAddress() + "-->" + socket.getPort());
			PrintWriter out = null;
			BufferedReader in = null;
			try {
				// 服务器输出
				out = new PrintWriter(socket.getOutputStream());
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				boolean flag = true;
//				System.out.println(in.readLine());
				while (flag) {
					// 从客户端
					String readLine = in.readLine();
					 System.out.println("接收客户端信息-->" + readLine);
					if ("bye".equalsIgnoreCase(readLine))
						flag = false;
					// 发送
					out.println(readLine);
					out.flush();
				}

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				try {
					socket.close();
					out.close();
					in.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			 System.out.println("客户端已退出-->" + socket.getInetAddress() +
	                    " P-->" + socket.getPort());
		}
	}
}
