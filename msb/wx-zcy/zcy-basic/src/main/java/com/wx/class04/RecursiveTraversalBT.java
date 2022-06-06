package com.wx.class04;

import java.util.Stack;


/**
 * 二叉树的遍历
 *
 * >>> 递归
 *
 * >>> 非递归
 *
 *
 *
 */
public class RecursiveTraversalBT {


    /*

    二叉树：定义

    先序：对于任何子树来说，都是，先头，再左，再右
    中序：对于任何子树来说，都是，先左，再头，再右
    后序：对于任何子树来说，都是，先左，再右，再头



    递归序：
    每一个节点都会走一遍左右，收集信息，每个节点都会来到3次，所以可以做到随便加工先序 中序 后序

    a 递归方式实现二叉树的先中后序遍历

    *理解递归序
    *先中后都可以在递归序的基础上加工出来
    *第一次到达一个节点就打印就是先序 第二次打印即中序 第三次打印即后序


    b 非递归方式实现二叉树的先中后遍历
    任何递归函数都可以改成非递归
    自己设计压栈的来实现




     */

    public static void f(Node head) {
        if (head == null) {
            return;
        }
        // System.out.println(head.value);
        f(head.left);
        f(head.right);
    }



    // 先序打印所有节点
    public static void pre(Node head) {
        if (head == null) {
            return;
        }
        System.out.println(head.value);
        pre(head.left);
        pre(head.right);
    }

    // 中序
    public static void in(Node head) {
        if (head == null) {
            return;
        }
        in(head.left);
        System.out.println(head.value);
        in(head.right);
    }
    // 后序
    public static void pos(Node head) {
        if (head == null) {
            return;
        }
        pos(head.left);
        pos(head.right);
        System.out.println(head.value);
    }


    // 先序 非递归的打印
    /*
    先序
    步骤：借助栈
    - 弹出就打印
    - 如有右，压入右
    - 如有左，压入左
    上面步骤不断循环，循环前先把头结点压入栈。
    只要栈不为空，就一直循环。


    先序：头 左 右
    如果打印顺序是：头 右 左，只要把先序的步骤二三换一下就行。

    再反过来，头 右 左，反过来，就是 后序


    后序 非递归
    步骤：
    - 弹出就把值压入另一个栈
    - 如有左，压入左
    - 如有右，压入右
    - 打印另一个栈里的值




    中序：左 头 右
    步骤：
    - 1、整条左边界依次入栈；
    - 2、1进行不下去了，就弹出，打印，head来到弹出节点的右节点，继续执行1




     */


    // 中序打印所有节点 非递归
    public static void in2(Node head) {
        System.out.println("in-order");
        if (head != null) {
            Stack<Node> stack = new Stack<>();
            while (!stack.isEmpty() || head != null) {

                if (head != null) {
                    stack.push(head);
                    head = head.left;
                } else {
                    head = stack.pop();
                    System.out.println(head.value);
                    head = head.right;
                }
            }
        }
    }




    // 先序打印所有节点 非递归
    public static void pre2(Node head) {

        if (head != null) {
            Stack<Node> stack = new Stack<>();
            stack.add(head);
            while (!stack.isEmpty()) {
                // 可以覆盖 head 引用
                Node pop = stack.pop();
                // 弹出就打印
                System.out.println(pop.value);
                if (pop.right != null) {
                    stack.push(pop.right);
                }
                if (pop.left != null) {
                    stack.push(pop.left);
                }
            }

        }

    }




    // 后序打印所有节点 非递归
    public static void pos2(Node head) {

        if (head != null) {
            // 使用了两个栈实现后序，一个栈也可实现，比较复杂。。。
            Stack<Node> s1 = new Stack<>();
            Stack<Node> s2 = new Stack<>();
            s1.add(head);
            while (!s1.isEmpty()) {
                // 可以覆盖 head 引用
                head = s1.pop();
                // 弹出就压栈
                s2.push(head);
                if (head.left != null) {
                    s1.push(head.left);
                }
                if (head.right != null) {
                    s1.push(head.right);
                }
            }

            while (!s2.isEmpty()) {
                System.out.println(s2.pop().value);
            }

        }

    }

    // 一个栈 实现 后序遍历  。。左 右 头
    public static void pos3(Node h) {
        if (h != null) {
            Stack<Node> stack = new Stack<>();
            // 先把头结点放进去，为了少用一个临时变量，复用h，
            // 多定义一个可能更好理解 hh=null, hh替换while里的h
            stack.push(h);
            Node c = null;
            // 利用 h 判断 c 的左右孩子是否已经打印完了
            while (!stack.isEmpty()) {
                c = stack.peek();
                //
                // h（上次打印的)既不是当前的左孩子也不是右孩子，证明还没开始处理 新节点
                // 左右孩子都没有处理呢，先搞左
                // 为啥这里既关心左树 还关心了右树， h != c.right ，因为如果右树处理完了，左肯定处理了。右处理了，左就一定不需要处理了
                if (c.left != null && h != c.left && h != c.right) {
                    // 先搞左树
                    stack.push(c.left);
                }
                // h（上次打印的)不是当前c的右孩子，证明右孩子没处理呢，去处理右
                else if (c.right != null && h != c.right) {
                    stack.push(c.right);
                }
                // 如果前两个分支都没有中，说明左右孩子都处理完了，打印当前节点，搞自己，打印
                else {
                    System.out.print(stack.pop().value + " ");
                    // h 永远跟踪上次打印的节点
                    h = c;
                }

            }
        }
        System.out.println();

    }











    class Node{
        private int value;

        private Node left;

        private Node right;


    }



}

