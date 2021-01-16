package com.zixue.ssm.framework;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ServerMain {
    public static volatile boolean running =true;
    public static void main(String[] args) {
       final ClassPathXmlApplicationContext applicationContext
                =new ClassPathXmlApplicationContext("classpath:/spring/spring*.xml");
        try {
            applicationContext.start();

            Runtime.getRuntime().addShutdownHook(new Thread(){
                @Override
                public void run() {
                  applicationContext.stop();
                  running=false;
                  ServerMain.class.notify();
                }
            });

            synchronized(ServerMain.class){
                while (running){
                    ServerMain.class.wait();

                }
            }
        }catch (Exception e){
            System.exit(0);
        }



    }
}
