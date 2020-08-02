package com.zixue.project.niobase;

import java.io.IOException;

import com.zixue.project.client.TimeClientHandler;

public class TimeClient {
    public static void main(String... args) throws IOException {
        int port = 8888;
        new Thread(new TimeClientHandler("127.0.0.1",port),"TimeClientHandler-001").start();
    }
}

