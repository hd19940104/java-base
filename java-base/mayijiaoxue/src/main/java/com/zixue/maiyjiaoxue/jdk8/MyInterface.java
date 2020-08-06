package com.zixue.maiyjiaoxue.jdk8;
@FunctionalInterface
 interface MyInterface {
    public void test();
    //只能有一个抽象方法s
//    public void demo();
    //重写父类Object 不算
    public String toString();
}
class MyTest{
    public void mytest(MyInterface myInterface){
        System.out.println("this is start");
        myInterface.test();
        System.out.println("this is end");
    }

    public static void main(String[] args) {
        MyTest myTest = new MyTest();
        myTest.mytest(new MyInterface() {
            @Override
            public void test() {
                System.out.println("this is test()");
            }
        });

        myTest.mytest(()->{
            System.out.println("this is interface");
        });
    }



}
