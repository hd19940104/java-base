package com.zixue.algorithm.designPattern;

/**
 * 代理模式
 */
public class DemoProxy {

    public static void main(String[] args) {
        new B(new C()).say();
    }

}


interface A{
    void  say();
}


class B implements  A{
    @Override
    public void say() {
        c.say();
    }

    C c;

    public B(C c) {
        super();
        this.c = c;
    }
}


class  C implements  A{
    @Override
    public void say() {
        System.out.println("this is C proxy");
    }
}

