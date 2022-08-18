package com.wx.class04;

public class PaperFolding {


     /*



    请把一段纸条放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
    如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是 下折痕 下折痕 和 上折痕。
    给定一个输入参数N，代表纸条从下边向上方连续对折N次，请从上到下打印所有折痕的方向。

    例如：N=1 打印 down N=2 打印 down down up



                            凹
                    凹              凸
               凹       凸     凹        凸
            凹    凸  凹   凸 凹  凸    凹  凸



    第一个节点是凹 后面 左凹右凸   中序遍历



     */


    public static void main(String[] args) {


        int N = 3;

        printAllFolds(N);


    }


    private static void printAllFolds(int n) {

        printProcess(1, n, true);


    }

    /**
     *
     * @param i 第几层
     * @param n 共几层
     * @param down 当前节点是凹还是凸 true 凹 false 凸
     */
    private static void printProcess(int i, int n, boolean down) {
        if (i > n) {
            return;
        }
        // 左
        printProcess(i + 1, n, true);
        // 当前节点
        System.out.println(down ? "凹" : "凸");
        // 右
        printProcess(i + 1, n, false);

    }



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













}
