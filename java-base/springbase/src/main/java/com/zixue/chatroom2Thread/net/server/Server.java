package com.zixue.chatroom2Thread.net.server;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.zixue.chatroom2Thread.net.clink.core.IoContext;
import com.zixue.chatroom2Thread.net.clink.impl.IoSelectorProvider;
import com.zixue.chatroom2Thread.net.foo.constants.TCPConstants;

public class Server {
    public static void main(String[] args) throws IOException {
        IoContext.setup()
                .ioProvider(new IoSelectorProvider())
                .start();

        TCPServer tcpServer = new TCPServer(TCPConstants.PORT_SERVER);
        boolean isSucceed = tcpServer.start();
        if (!isSucceed) {
            System.out.println("Start TCP server failed!");
            return;
        }

        UDPProvider.start(TCPConstants.PORT_SERVER);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        String str;
        do {
            str = bufferedReader.readLine();
            tcpServer.broadcast(str);
        } while (!"00bye00".equalsIgnoreCase(str));

        UDPProvider.stop();
        tcpServer.stop();

        IoContext.close();
    }
}
