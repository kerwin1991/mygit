package com.wx.class04;

import java.util.List;

public class IsBalance {





    /*

    二叉树的递归套路

    可以解决面试中绝大多数的二叉树问题，尤其是树形dp问题


    本质是利用递归遍历二叉树的便利性


    套路：
    假设以x节点为头，假设可以向x左树和右树要任何信息
    在上一步的假设下，讨论以x为头结点的树，得到答案的可能性（最重要）（常见的可能性：跟x有关 和 跟x无关）
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



    /*


        例子2：给定一颗二叉树的头节点head，任何两个节点之间都存在距离，返回整棵二叉树的最大距离？



                        a
                  b            c
             d       e      f      g
         h                       i     j


       两个节点的距离：最精简的方式从一个节点到另一个节点经过几个节点
       例如：d-e  3 , f-i 4

       可能性：
       - 最大距离跟X无关 如 图1
         左树最大距离  右树最大距离  取max
       - 最大距离跟X有关
         左树离自己最远的点，到右树离自己最远的点，左树的高度+1+右树的高度
       结论：每棵树返回自己的最大距离 和 高度


       图1
                            x
                    i               i
                 i      i
              i             i
            i                 i
           i                    i
          i
         i

     */



    // 这就是动态规划：子树返回自己的信息给父节点，父节点加工后，再向上返回，子树的信息就没用了

    public static MaxDistanceInfo maxDistanceProcess(Node x) {
        if (x == null) {
            return new MaxDistanceInfo(0, 0);
        }
        MaxDistanceInfo leftInfo = maxDistanceProcess(x.getLeft());
        MaxDistanceInfo rightInfo = maxDistanceProcess(x.getRight());
        // 当前节点的高度就是左右树最大高度+1
        int height = Math.max(leftInfo.getHeight(), rightInfo.getHeight()) + 1;
        // 最大距离呢  取 跟x节点无关情况的最大距离  与 跟x节点有关情况的最大距离 的较大的值
        // 无关
        int distance1 = Math.max(leftInfo.getMaxDistance(), rightInfo.getMaxDistance());
        // 有关
        int distance2 = leftInfo.getHeight() + 1 + rightInfo.getHeight();
        int maxDistance = Math.max(distance1, distance2);
        // -- 以上三行可以合并成一行代码，分开好理解
        return new MaxDistanceInfo(maxDistance, height);
    }


    // 主函数
    public static int maxDistance(Node head) {
        return maxDistanceProcess(head).getMaxDistance();
    }



    public static class MaxDistanceInfo{
        private int maxDistance;
        private int height;

        public MaxDistanceInfo(int maxDistance, int height) {
            this.maxDistance = maxDistance;
            this.height = height;
        }

        public int getMaxDistance() {
            return maxDistance;
        }

        public void setMaxDistance(int maxDistance) {
            this.maxDistance = maxDistance;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }
    }



/*


        例子3：给定一颗二叉树的头节点head，返回这颗二叉树中最大的二叉搜素子树的头结点

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
        最大子搜size  是不是搜索二叉树  max
        右树：
        最大子搜size  是不是搜索二叉树  min
        统一：
        最大子搜size  是不是搜索二叉树  max  min

     */


    public static BSTInfo bstProcess(Node x) {
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

        // 假如此时跟x节点无关。 其左右子树的最大满足搜索二叉树节点数量，取大值
        int maxSubBSTSize = 0;
        if (leftInfo != null) {
            maxSubBSTSize = leftInfo.getMaxSubBSTSize();
        }
        if (rightInfo != null) {
            maxSubBSTSize = Math.max(maxSubBSTSize, rightInfo.getMaxSubBSTSize());
        }


        boolean isAllBST = false;

        // 考虑跟x是否有关，有关的前提是： x>left.max && x < right.min && left.isBST && right.isbst
        // 成立的前提下 isAllBST=true, maxSubBSTSize = 以X为头的所有节点的和
        /*if (
                (leftInfo == null ? true : leftInfo.isAllBST())
                &&
                (rightInfo == null ? true : rightInfo.isAllBST())
                &&
                (leftInfo == null ? true : leftInfo.getMax() < x.getValue())
                &&
                (rightInfo == null ? true : rightInfo.getMin() > x.getValue())
        ) {
            isAllBST = true;
        }*/

        if (
                (leftInfo == null || leftInfo.isAllBST())
                &&
                (rightInfo == null || rightInfo.isAllBST())
                &&
                (leftInfo == null || leftInfo.getMax() < x.getValue())
                &&
                (rightInfo == null || rightInfo.getMin() > x.getValue())
        ) {
            isAllBST = true;

            maxSubBSTSize = (rightInfo == null ? 0 : rightInfo.getMaxSubBSTSize())
                    + 1
                    + (leftInfo == null ? 0 : leftInfo.getMaxSubBSTSize());
        }





        return new BSTInfo(isAllBST, maxSubBSTSize, min, max);
    }



    public static class BSTInfo{
        // 是否是搜索二叉树
        private boolean isAllBST;
        // 满足搜索二叉树的最大节点数量
        private int maxSubBSTSize;
        // 整棵树最小值
        private int min;
        // 整棵树最大值
        private int max;

        public BSTInfo(boolean isAllBST, int maxSubBSTSize, int min, int max) {
            this.isAllBST = isAllBST;
            this.maxSubBSTSize = maxSubBSTSize;
            this.min = min;
            this.max = max;
        }

        public boolean isAllBST() {
            return isAllBST;
        }

        public void setAllBST(boolean allBST) {
            isAllBST = allBST;
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
    }




    /*


    例4：派对的最大快乐值：
    题目：
    每个人只有唯一的上级，不存在下级指向上级，叶子节点没有下属
    除基层外，每个员工只有一个上级和多个直接下级
    公司办party，你可以决定哪些员工来，哪些不来
    规则：
    - 如果某个员工来了，那么这个员工的所有直接下级不能来
    - 派对的整体快乐值是所有到场员工快乐值的累加
    - 你的目标是让整体快乐值尽量大
    求：
    给定一颗多叉树 头结点boss，请返回派对的最大快乐值。

    分析：
    x来  x不来

    x来：所有子树节点不来时的快乐值总和
    x不来：子节点a 来/不来的较大快乐值 + 子节点b 来/不来的较大快乐值 + c 。。。


     */


    // 主函数
    public static int happyProcessMain(Employee head) {
        HappyInfo happyInfo = happyProcess(head);
        return Math.max(happyInfo.no, happyInfo.yes);
    }


    public static HappyInfo happyProcess(Employee x) {
        // 基层员工没有nexts
        if (x.nexts.isEmpty()) {
            // 来: x.happy  不来 0
            return new HappyInfo(x.happy, 0);
        }
        int yes = x.happy;
        int no = 0;
        // 遍历每一个子节点
        for (Employee next : x.nexts) {
            // 得到每一子节点的happy值
            HappyInfo nextInfo = happyProcess(next);
            // yes:每个子节点不来的相加
            yes += nextInfo.no;
            // no:取子 yes no较大
            no += Math.max(nextInfo.no, nextInfo.yes);
        }
        return new HappyInfo(yes, no);
    }





    public static class Employee{
        // 员工的快乐值
        public int happy;
        // 这名员工有哪些直接下属
//        List<Employee> subordinates;
        List<Employee> nexts;

    }


    // 任何子树返回的信息
    public static class HappyInfo{
        // 这个节点来的时候的快乐值
        public int yes;
        // 这个节点不来的时候的快乐值
        public int no;

        public HappyInfo(int yes, int no) {
            this.yes = yes;
            this.no = no;
        }
    }




}



