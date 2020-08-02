package com.org.bling.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisFuture;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.concurrent.ExecutionException;
import java.util.function.Consumer;


public class LettuceUse {
    public static void main(String[] args){

        // RedisClient redisClient = RedisClient.create("redis://password@localhost:6379/0");
        // 同步方式操作
        /*
        RedisClient redisClient = RedisClient.create("redis://9.134.3.226:6379/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        syncCommands.set("key", "Hello, Redis!");

        connection.close();
        redisClient.shutdown();
        */

        // 异步方式操作
        RedisClient redisClient = RedisClient.create("redis://9.134.3.226:6379/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisAsyncCommands<String, String> asyncCommands = connection.async();

        // 同步拿到结果
        /*
        RedisFuture<String> future = asyncCommands.get("key");
        String value = null;
        try {
             value = future.get();
             value = future.get(1. TimeUnit.MINTUS);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(value);
        */


        RedisFuture<String> future = asyncCommands.get("key");
        future.thenAccept(new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        });


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        connection.close();
        redisClient.shutdown();
    }
}
