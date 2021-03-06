package com.zixue.nio.niobase;

import java.io.IOException;

import com.zixue.nio.nioserver.MultiplexerTimeServerHandler;

public class TimeServer {

    public static void main(String... args) throws IOException {
        int port = 8888;

        MultiplexerTimeServerHandler server = new MultiplexerTimeServerHandler(port);
        new Thread(server,"nio-MultiplexerTimeServerHandler-001").start();
    }
}
