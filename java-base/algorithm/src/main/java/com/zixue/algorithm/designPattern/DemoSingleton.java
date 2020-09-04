package com.zixue.algorithm.designPattern;

/**
 * 单例模式
 */
public class DemoSingleton {
    //饿汉模式
    private  static  final DemoSingleton demoSingleton=new DemoSingleton();

    public DemoSingleton getDemoSingleton(){
        return demoSingleton;
    }

}
class  Demo{
    //懒汉模式
    private  Demo demo=null;
    //线程不安全（需要加同步锁synchronized）
    public  Demo getDemo(){
        if (demo==null){
            demo=new Demo();
        }
        return demo;
    }
    public  void say(){
        System.out.println("this is say by Demo");
    }

}




