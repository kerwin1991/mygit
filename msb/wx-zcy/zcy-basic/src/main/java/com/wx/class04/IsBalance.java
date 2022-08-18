package com.wx.class04;

public class IsBalance {





    /*

    二叉树的递归套路

    可以解决面试中绝大多数的二叉树问题，尤其是树形dp问题


    本质是利用递归遍历二叉树的便利性


    套路：
    假设以x节点为头，假设可以向x左树和右树要任何信息
    在上一步的假设下，讨论以x为头结点的树，得到答案的可能性（最重要）
    列出所有可能性后，确定到底需要向左树和右树要什么样的信息
    把左树信息和右树信息求全集，就是任何一颗子树都需要返回的信息S
    递归函数都返回S，每一颗子树都这么要求
    写代码，在代码中考虑如何把左树的信息和右树的信息整合出整棵树的信息


    例子：给定一颗二叉树的头节点head，返回这颗二叉树是不是平衡二叉树（每一个节点的左树和右树的高度差不能超过1）


     */




    /**
     * 是否平衡二叉树
     * @param head
     * @return
     */
    public static boolean isBalance(Node head) {
        return process(head).isBalance;
    }



    public static Info process(Node X) {




        /*

                        a
                  b            c
            d         e     f      g
         h                      i




         */

        if (X == null) {
            return new Info(true, 0);
        }
        Info leftInfo = process(X.getLeft());
        Info rightInfo = process(X.getRight());

        // 父节点是否平衡？ 子树有一个不平衡 就不平衡； 如何子树都平衡，高度差>1 就不平衡
        boolean balance = true;
        if (!leftInfo.isBalance || !rightInfo.isBalance || Math.abs(leftInfo.heigh-rightInfo.heigh) > 1) {
            balance = false;
        }

        // 父节点的高度=取子节点最大高度 + 1 (头结点的高度)
        int heigh = Math.max(leftInfo.heigh, rightInfo.heigh) + 1;

        return new Info(balance, heigh);

    }




    // 左右都要求一样
    public static class Info{
        // 是否平衡
        public boolean isBalance;
        // 高度
        public int heigh;

        public Info(boolean isBalance, int heigh) {
            this.isBalance = isBalance;
            this.heigh = heigh;
        }
    }









}



