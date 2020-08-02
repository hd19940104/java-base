package com.zixue.socket.base.tcp2udp.client;

import java.io.IOException;

import com.zixue.socket.base.tcp2udp.client.bean.ServerInfo;

public class Client {
    public static void main(String[] args) {
        ServerInfo info = UDPSearcher.searchServer(10000);
        System.out.println("Server:" + info);

        if (info != null) {
            try {
                TCPClient.linkWith(info);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
