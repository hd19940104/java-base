package com.zixue.algorithm.thread;

import sun.misc.Unsafe;

/**
 * synchronized
 *  原子性
 *      sychronized 反映到字节码的层面是monitorenter和monitorexit，在synchronized块之间代码也具备原子性
 *  可见先：
 *      volatile :通过变量修改会将新值立即同步回主内存，来保证可见性
 *      synchronized ：会通过对一个变量执行unlock操作之前，必须把此变量同步主内存中保证可见先
 *      final ：被final修饰的字段在构造器中一旦初始化完成，并且构造器没有把this的引用传递出去，那在其他线程中就能看见final字段的值
 *  有序性
 */
public class DemoSynchronized {
    public static void main(String[] args) {
        Unsafe.getUnsafe();
    }
}
