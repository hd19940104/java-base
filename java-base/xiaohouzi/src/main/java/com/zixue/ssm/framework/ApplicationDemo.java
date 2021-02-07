package com.zixue.ssm.framework;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import sun.lwawt.macosx.CSystemTray;

public class ApplicationDemo implements ApplicationListener {
    String name;
    static String name1;
    final  static Double Pi = 3.14;
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        String name;
    }
   static     int a=1;
    void demo1(){

//        synchronized (Pi){
            for (int i=0;i<10;i++){
        //        synchronized (Pi){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        synchronized (this){
                        synchronized (ApplicationDemo.class){
                            for (int i=0;i<10000;i++){
//                            synchronized (Pi){
                                a--;//++a
//                            }

                            }
                        }

                        //System.out.println("thread2"+a);
                    }
                }).start();
        //        }

            }
//        }


    }

    public static void main(String[] args) throws InterruptedException {
        for (int i=0 ;i<1000;i++){
            new ApplicationDemo().demo1();
            new ApplicationDemo().demo1();
        }

        Thread.sleep(1000);
        System.out.println(a);
    }
}
