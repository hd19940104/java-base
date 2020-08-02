package com.zixue.socket.base.tcp2udp.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zixue.socket.base.tcp2udp.constants.TCPConstants;

public class Server {
    public static void main(String[] args) throws IOException {
    	//开启tcp服务
        TCPServer tcpServer = new TCPServer(TCPConstants.PORT_SERVER);//30401
        boolean isSucceed = tcpServer.start();
        if (!isSucceed) {
            System.out.println("Start TCP server failed!");
            return;
        }
        //开启udp服务
        UDPProvider.start(TCPConstants.PORT_SERVER);//30401

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        do {
            str = bufferedReader.readLine();
            tcpServer.broadcast(str);
        } while (!"00bye00".equalsIgnoreCase(str));

        UDPProvider.stop();
        tcpServer.stop();
    }
}
