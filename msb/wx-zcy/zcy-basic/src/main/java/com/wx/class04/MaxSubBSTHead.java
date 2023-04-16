package com.wx.class04;

public class MaxSubBSTHead {

    /*


        -- 例子3.1：给定一颗二叉树的头节点head，返回这颗二叉树中最大的二叉搜索子树的大小
        例子3.2：给定一颗二叉树的头节点head，返回这颗二叉树中最大的二叉搜素子树的头结点

        搜索二叉树：整棵树没有重复值，左树值比我小，右树比我大，每棵子树都如此
        最大：根据节点数量判断大小。有相同的返回一个就行。





                        5
                  3            7
             2       4      6      8
         1                            9



                        5
                  3            7
             2       4      8      6
         1                      9

         最大：

                  3
             2       4
         1


        分析可能性
        - X无关



        - X有关
        左树是搜索二叉树 右树是搜索二叉树 左最大<x<右最小


        左树：
        最大子搜size  是不是搜索二叉树  max 最大子搜索二叉树头结点
        右树：
        最大子搜size  是不是搜索二叉树  min 最大子搜索二叉树头结点
        统一：
        最大子搜size  是不是搜索二叉树  max  min

     */


    // 主函数  返回最大搜索二叉子树大小
    public static Node maxSubbstHeadMain(Node head) {
        return bstProcess(head).maxSubBSTHead;
    }


    private static BSTInfo bstProcess(Node x) {
        if (x == null) {
            return null;
        }
        BSTInfo leftInfo = bstProcess(x.getLeft());
        BSTInfo rightInfo = bstProcess(x.getRight());

        // 整棵树最小最大值
        int min = x.getValue();
        int max = x.getValue();

        if (leftInfo != null) {
            min = Math.min(min, leftInfo.getMin());
            max = Math.min(max, leftInfo.getMax());
        }
        if (rightInfo != null) {
            min = Math.min(min, rightInfo.getMin());
            max = Math.min(max, rightInfo.getMax());
        }
        // 最大子搜索二叉树头结点
        Node maxSubBSTHead = null;
        // 假如此时跟x节点无关。 其左右子树的最大满足搜索二叉树节点数量，取大值
        int maxSubBSTSize = 0;
        if (leftInfo != null) {
            maxSubBSTSize = leftInfo.getMaxSubBSTSize();
            maxSubBSTHead = leftInfo.getMaxSubBSTHead();
        }
        if (rightInfo != null && rightInfo.maxSubBSTSize > maxSubBSTSize) {
            maxSubBSTSize = rightInfo.getMaxSubBSTSize();
            maxSubBSTHead = rightInfo.getMaxSubBSTHead();
        }


        // boolean isAllBST = false;

        // 考虑跟x是否有关，有关的前提是： x>left.max && x < right.min && left.isBST && right.isbst
        // 成立的前提下 isAllBST=true, maxSubBSTSize = 以X为头的所有节点的和

        if (
                (leftInfo == null || leftInfo.maxSubBSTHead==x.getLeft())
                        &&
                        (rightInfo == null || rightInfo.maxSubBSTHead==x.getRight())
                        &&
                        (leftInfo == null || leftInfo.getMax() < x.getValue())
                        &&
                        (rightInfo == null || rightInfo.getMin() > x.getValue())
                ) {
            maxSubBSTHead = x;

            maxSubBSTSize = (rightInfo == null ? 0 : rightInfo.getMaxSubBSTSize())
                    + 1
                    + (leftInfo == null ? 0 : leftInfo.getMaxSubBSTSize());
        }





        return new BSTInfo(maxSubBSTSize, min, max, maxSubBSTHead);
    }



    public static class BSTInfo{
        // 是否是搜索二叉树 可以根据maxSubBSTHead 是否是这棵树的头结点判断，
        // isAllBST;
        // 满足搜索二叉树的最大节点数量
        private int maxSubBSTSize;
        // 整棵树最小值
        private int min;
        // 整棵树最大值
        private int max;
        // 最大子搜索二叉树头结点
        private Node maxSubBSTHead;


        public BSTInfo(int maxSubBSTSize, int min, int max, Node maxSubBSTHead) {
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
            this.maxSubBSTHead = maxSubBSTHead;
        }

        public int getMaxSubBSTSize() {
            return maxSubBSTSize;
        }

        public void setMaxSubBSTSize(int maxSubBSTSize) {
            this.maxSubBSTSize = maxSubBSTSize;
        }

        public int getMin() {
            return min;
        }

        public void setMin(int min) {
            this.min = min;
        }

        public int getMax() {
            return max;
        }

        public void setMax(int max) {
            this.max = max;
        }

        public Node getMaxSubBSTHead() {
            return maxSubBSTHead;
        }

        public void setMaxSubBSTHead(Node maxSubBSTHead) {
            this.maxSubBSTHead = maxSubBSTHead;
        }
    }





}
