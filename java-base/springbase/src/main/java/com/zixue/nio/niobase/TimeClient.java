package com.zixue.nio.niobase;

import java.io.IOException;

import com.zixue.nio.client.TimeClientHandler;


public class TimeClient {
    public static void main(String... args) throws IOException {
        int port = 8888;
        new Thread(new TimeClientHandler("127.0.0.1",port),"TimeClientHandler-001").start();
    }
}

