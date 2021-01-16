package com.zixue.algorithm.list;

public class Demo {
    static class Node{
        int val;
        Node next;
        Node(int v){
            val=v;
        }
    }

    public static void main(String[] args) {
        Node n1=new Node(0);
        n1.val=1;
        System.out.print(n1);
    }
    public Node addTwoNumbers(Node n1,Node n2){
        Node node=new Node(0);

        int sum=0;
        while (n1!=null || n2!=null|| sum!=0 ){
            if (n1!=null){
                sum+=n1.val;
                n1=n1.next;
            }
            if (n2!=null){
                sum+=n2.val;
                n2=n2.next;
            }

            node.next=new Node(sum%10);
            sum=sum/10;
            node=node.next;

        }


        return node;
    }

}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode  listNode= new ListNode(0);
        ListNode p = new ListNode(0);
        p = listNode;
        int sum = 0;

        while (l1 != null || l2 != null || sum != 0) {
            if (l1 != null) {
                sum += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                sum += l2.val;
                l2 = l2.next;
            }
            p.next = new ListNode(sum % 10);
            sum = sum / 10;
            p = p.next;
        }
        return listNode.next;
    }
}
