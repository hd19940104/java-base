package com.zixue.maiyjiaoxue.jdk8;

import java.util.ArrayList;
import java.util.List;

public class MyDemo  {
    public static void main(String[] args) {
        List list = new ArrayList();

        list.add("abd");
        list.add("ebd");
        list.add("fbd");
        list.add("ibd");
        List list2 = new ArrayList();
        list.forEach(l->{
            System.out.println(l.toString().toUpperCase());
        });
        list.forEach(i->list2.add(i.toString().toUpperCase()));
        list2.forEach(i->System.out.println(i));
    }
}
