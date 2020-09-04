package com.zixue.algorithm.designPattern;

/**
 * 工厂模式
 */
public class DemoFactory {
    public <T> T getDemo(Class<T> c){
        T t =null;

        if (t==null){
            try {
                t = (T) Class.forName(c.getName()).newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return t;
    }
    void say(){
        System.out.println("this is say");
    }
    public static void main(String[] args) {
        new DemoFactory().getDemo(DemoFactory.class).say();
       new  DemoFactory().getDemo(Demo.class).say();

    }

}
