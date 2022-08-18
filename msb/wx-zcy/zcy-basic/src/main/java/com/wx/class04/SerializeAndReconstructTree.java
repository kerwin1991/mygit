package com.wx.class04;

import java.util.LinkedList;
import java.util.Queue;

/**
 *
 * 二叉树的序列化 反序列化
 *
 *
 */


public class SerializeAndReconstructTree {


    /*
        二叉树的序列化和反序列化
        1 可以用先序或者中序后者后序或者安层遍历，来实现二叉树的序列化
            把内存里的二叉树，做成文件的形式保存下来。

            例如：
            序列化-使用先序遍历，存到数组里，注意如果子节点为空，需要用null补齐
            反序列化-



        2 用了什么方式序列化，就用什么样的方式反序列化


            1
          1    n
        n   1
           n n

       序列化： 1 1 n 1 n n n

               1
             n    1
                1   n
              n  n

       序列化： 1 n 1 1 n n n

     */


    /**
     * 按照先序 序列化
     * @param head
     * @return
     */
    public static Queue<String> preSerial(Node head) {

        Queue<String> ans = new LinkedList<>();
        pres(head, ans);
        return ans;

    }

    private static void pres(Node head, Queue<String> ans) {

        if (head == null) {
            // 空的话需要占位
            ans.add(null);
        } else {
            ans.add(String.valueOf(head.getValue()));
            pres(head.getLeft(), ans);
            pres(head.getRight(), ans);
        }
    }

    /**
     * 按照先序 反序列化
     * @param queue
     * @return
     */
    public static Node buildTree(Queue<String> queue) {
        if (queue == null || queue.size() == 0) {
            return null;
        }

        return preb(queue);


    }

    //
    private static Node preb(Queue<String> queue) {
        // 弹出
        String value = queue.poll();
        if (value == null) {
            return null;
        }
        Node node = new Node();
        node.setValue(Integer.valueOf(value));
        // 同样的方式，建立左树 右树
        node.setLeft(preb(queue));
        node.setRight(preb(queue));
        return node;
    }

    // - - - - - - ------------------------------------


    // 按层序列化
    /*

    n表示null

                1
             2      3
          n   4   5    6
             n n 7 8  n  n
                n nn n


    序列化的结果是： 1 2 3 n 4 5 6 n n 7 8 n n n n n n

    注意：使用null占位

     */

    /**
     * 按照层 序列化
     * @param head
     * @return
     */
    public static Queue<String> levelSerial(Node head) {

        // 存序列化结果的队列
        Queue<String> ans = new LinkedList<>();

        if (head == null) {
            // 空节点，依然占位
            ans.add(null);
            return ans;
        }

        // 遍历使用的队列
        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 弹出前 (入遍历队列时)  序列化
        ans.add(String.valueOf(head.getValue()));

        while (!queue.isEmpty()) {
            // 可以考虑用复用head引用 少一个临时变量node
            Node node = queue.poll();

            if (node.getLeft() != null) {
                // 序列化 并 入遍历队列
                ans.add(String.valueOf(node.getLeft().getValue()));
                queue.add(node.getLeft());
            } else {
                // 只序列化
                ans.add(null);
            }

            if (node.getRight() != null) {
                // 序列化 并 入遍历队列
                ans.add(String.valueOf(node.getRight().getValue()));
                queue.add(node.getRight());
            } else {
                // 只序列化
                ans.add(null);
            }
        }

        return ans;
    }


    /*

        n表示null

                    1
                 2      3
              n   4   5    6
                 n n 7 8  n  n
                    n nn n


        序列化的结果是： 1 2 3 n 4 5 6 n n 7 8 n n n n n n

        注意：使用null占位

     */

    /**
     * 按照层 反序列化
     * @param levelList
     * @return
     */
    public static Node buildByLevelQueue(Queue<String> levelList) {

        if (levelList == null || levelList.size() == 0) {
            return null;
        }

        // 先生成头结点，方法返回的也是这个头结点
        // 弹出levelList第一个元素
        Node head = generateNode(levelList.poll());
        //
        Queue<Node> queue = new LinkedList<>();
        // 非空才入队列
        if (head != null) {
            queue.add(head);
        }
        while (!queue.isEmpty()) {
            // 弹出的时候给子节点赋值
            // 这个临时变量可以提到外面 复用
            Node node = queue.poll();
            // 因为有null补齐，所以非空一定有左右子节点
            // 弹出队列的时候再给子节点赋值
            node.setLeft(generateNode(levelList.poll()));
            node.setRight(generateNode(levelList.poll()));

            if (node.getLeft() != null) {
                // 非空时才入队列
                queue.add(node.getLeft());
            }

            if (node.getRight() != null) {
                queue.add(node.getRight());
            }
        }

        return head;
    }



    public static Node generateNode(String value) {
        if (value == null) {
            return null;
        } else {
            Node node = new Node();
            node.setValue(Integer.valueOf(value));
            return node;
        }
    }


    /*

    题目：

    如何设计一个打印整棵树的打印函数？ 开放题
    - 可补成满二叉树


    这个题目跳过，没有练习了。。。。。。。。。。。。。。





     */









}
