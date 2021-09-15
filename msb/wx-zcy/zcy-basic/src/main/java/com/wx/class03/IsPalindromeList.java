package com.wx.class03;


import java.util.Stack;

/**
 * 回文链表
 */
public class IsPalindromeList {







    public static boolean isPalindrome1(Node head) {
        // 栈的实现方式
        Stack<Node> stack = new Stack<>();
        Node cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }

        while (head != null) {
            if (head.value != stack.pop().value) {
                return false;
            }
            head = head.next;
        }
        return true;
    }


    // need o(1) extra space
    public static boolean isPalindrome2(Node head) {
        // 快慢指针
        /*
        1 2 3 3 2 1
        1 2 3 5 3 2 1

        1->2->3->null  <-3<-2<-1
        1->2->3->5->null  <-3<-2<-1

         */
        // 1 使用快慢指针找到中点的位置
        Node n1 = head;
        Node n2 = head;
        while (n2.next != null && n2.next.next != null) {
            n1 = n1.next;
            n2 = n2.next.next;
        }
        // n1 在第一个3(偶数)或者5(基数)位置了。。n2在倒数第二个位置或者在最后一个
        // 2 后半段翻转方向，n1指向null 后面翻转
        // n2 变成另一个指针 和n1 完成后半段翻转
        n2 = n1.next;
        n1.next = null;
        Node n3 = null;
        while (n2 != null) {
            n3 = n2.next;
            n2.next = n1;
            n1 = n2;
            n2 = n3;// 下一个
        }
        // 最后结束的时候 n1来到了最后一个节点  n2 n3为null
        // n2 n3 变为非null 成为一个可用的指针
        n3 = n1; // 保存了尾巴
        n2 = head;

        // 2 判断是否回文，n1 n2 一头一尾













        return true;
    }


    public static class Node{
        public int value;
        public Node next;

        public Node(int data) {
            this.value = data;
        }
    }


}
