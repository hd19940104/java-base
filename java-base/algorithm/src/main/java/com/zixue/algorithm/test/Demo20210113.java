package com.zixue.algorithm.test;

import com.google.common.collect.Lists;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Demo20210113 {
    public static void main(String[] args) {
//        HashMap hashMap = new HashMap();
//        Queue queue =new LinkedBlockingDeque();
//        StringBuffer stringBuffer=new StringBuffer();
//        Vector vector = new Vector();
//        ArrayList arrayList = new ArrayList();
//        LinkedList linkedList = new LinkedList();
//        HashMap hash=new HashMap();
//        Executors.newCachedThreadPool();
//        AtomicInteger integer=new AtomicInteger(1);
        ArrayList<Object> objectArrayList = Lists.newArrayList();
        objectArrayList.add("1");
        objectArrayList.add("2");
        objectArrayList.add("3");
        System.out.println(objectArrayList);
        Stream<Object> stream = objectArrayList.stream();
        List<Object> first = stream.filter(a->a.toString()=="1").collect(Collectors.toList());
        System.out.println(first);

    }
}
