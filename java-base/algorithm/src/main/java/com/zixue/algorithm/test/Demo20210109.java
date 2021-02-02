package com.zixue.algorithm.test;

import java.util.*;

public class Demo20210109 {

    /**
     *  求两数之和
     * @param nums
     * @param target
     * @return
     */
    public static  int[] twoSum(int[] nums,int target){

        int len = nums.length;
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        hashMap.put(nums[0],0 );

        for(int i=1;i<len;i++){
            int key=target-nums[i];

            if (hashMap.containsKey(key)){
                return new int[]{i,hashMap.get(key)};
            }
            hashMap.put(nums[i],i);
        }
        return null;

    }
    class ListNode{
        int data;
        ListNode head;
        ListNode tail;
        String string="";
        

    }
    public ListNode addTwoNumbers(ListNode l1,ListNode l2){
        ListNode listNode = new ListNode();





        return listNode;

    }


    /**
     * 冒泡排序
     * @param array
     * @return
     */
    public  static int[] bubbleSort(int[] array){

        if (array.length==0){
            return array;
        }

        for (int i=0;i<array.length;i++){
            for (int j=0;j< array.length-i-1;j++){
                if (array[j+1]<array[j]){
                    int temp = array[j + 1];
                    array[j + 1] = array[j];
                    array[j] = temp;
                }
            }
        }
        return array;


    }

    /**
     * 选择排序
     * @param array
     * @return
     */
    public static int[] selectionSort(int[] array) {

        if (array.length==0){
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            int min=i;
            //找到最小的数
            for (int j=i;j< array.length;j++){
                if (array[j]<array[min]){
                    min=j;
                }
            }
            int temp =array[min];
            array[min]=array[i];
            array[i]=temp;



        }


        return array;
    }



    public String decodeString(String s) {

        Stack<String> stack=new Stack<String>();
        for(int i=0;i<s.length();i++) {
            if(s.charAt(i)==']') {
                String string="";
                while(!stack.peek().equals("[")) {
                    string=stack.pop()+string;
                }
                stack.pop();

                String countString="";
                while((!stack.isEmpty())&&(stack.peek().charAt(0)>='0'&&stack.peek().charAt(0)<='9')) {
                    countString=stack.pop()+countString;
                }
                int count=Integer.parseInt(countString);

                String retString="";
                for(int j=0;j<count;j++) {
                    retString=retString+string;
                }
                stack.push(retString);
            }
            else {
                String str=""+s.charAt(i);
                stack.push(str);
            }
        }

        String aaa="";
        while(!stack.isEmpty()) {
            aaa=stack.pop()+aaa;
        }
        return aaa;
    }


    LinkedHashMap linkedHashMap=new LinkedHashMap();
    TreeMap treeMap = new TreeMap();
}
