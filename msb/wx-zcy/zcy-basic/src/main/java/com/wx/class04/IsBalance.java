package com.wx.class04;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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



    private static Info process(Node X) {




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

    private static MaxDistanceInfo maxDistanceProcess(Node x) {
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


        例子3.1：给定一颗二叉树的头节点head，返回这颗二叉树中最大的二叉搜素子树的大小
        -- 例子3.2：给定一颗二叉树的头节点head，返回这颗二叉树中最大的二叉搜素子树的头结点。(com.wx.class04.MaxSubBSTHead)

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


    // 主函数  返回最大搜索二叉子树大小
    public static int bstMain(Node head) {
        return bstProcess(head).maxSubBSTSize;
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


    private static HappyInfo happyProcess(Employee x) {
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


    /*


        例5：给定一颗二叉树的头节点head，返回这颗二叉树是不是满二叉树
        满二叉树：每一层节点都是满的
        满足的性质：高度 L 节点数量 N ， 2^L - 1 = N


     */


    /**
     * 是否是满二叉树
     * 主函数
     * @param head
     * @return
     */
    public static boolean isFull(Node head) {

        if (head == null) {
            return true;
        }
        FullInfo fullInfo = isFullProcess(head);
        return (1 << fullInfo.height) - 1 == fullInfo.nodes;


    }


    private static FullInfo isFullProcess(Node x) {
        if (x == null) {
            return new FullInfo(0, 0);
        }
        FullInfo leftInfo = isFullProcess(x.getLeft());
        FullInfo rightInfo = isFullProcess(x.getRight());
        int height = Math.max(leftInfo.height, rightInfo.height) + 1;
        int nodes = leftInfo.nodes + rightInfo.nodes + 1;
        return new FullInfo(height, nodes);
    }




    public static class FullInfo{
        public int height;
        public int nodes;

        public FullInfo(int height, int nodes) {
            this.height = height;
            this.nodes = nodes;
        }
    }






    /*


        例6：给定一颗二叉树的头节点head，返回这颗二叉树是不是完全二叉树
        完全二叉树：不双全的节点只能出现在最后

        满足的性质：(写代码的思路)
        - 任何节点，有右 没有 左，肯定不是完全二叉树
        - 一旦遇到左右孩子不双全，后序遇到的节点都是叶子节点


                    1
             2              3
         4       5      6       7

       8   9   10  11  12 13  14

       7不双全，后面的 8-14都是叶子节点




                      1
             2              3
         4       5      6       7

       8   9


       5节点不双全，后面的6 7 8 9 都是叶子节点

     */


    // 解法一：宽度优先遍历的解法
    public static boolean isCBT1(Node head) {
        if (head == null) {
            return true;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.add(head);
        // 是否出现了不双全的节点，只有左没有右，或者左右都没有
        boolean leaf = false;
        // 宽度优先遍历
        while (!queue.isEmpty()) {

            Node node = queue.poll();
            // System.out.println(node.getValue());

            Node l = node.getLeft();
            Node r = node.getRight();
            if (
                    (l == null && r != null)   // 有右没左 直接false

                    || (leaf && (l!=null || r!=null))  // 出现了不全节点后，出现了非叶子节点，直接false
                //  || (leaf && !(l==null && r==null))

                    ) {
                return false;
            }


            if (l != null) {
                queue.add(l);
            }
            if (r != null) {
                queue.add(r);
            }

            // 遇到左右孩子不双全的节点了
            if (!(l != null && r != null)) {
                leaf = true;
            }
            /*if (l == null || r == null) {
                leaf = true;
            }*/

        }
        return true;
    }




    // 解法二：递归套路的解法


    /*



    x的左树  右树

    4种情况
    - 左右都是满二叉
    - 左边不满，右满
    - 左边满没有缺口，右满
    - 左边满 右边有缺口

    x需要向左右要的信息：三个信息
    左树是否满的  高度  是否是完全二叉树
    右树是否满的  高度  是否是完全二叉树


    4种情况示例

    - 如果左树满&&右树满 && 左高==右高，就能确定x也是满的
            a
         b     c
      d    e  f  g

    - 左树是完全二叉树 右是满二叉树 左的高度大于右高度

            a
         b     c
      d

    - 左满，右满，左高度比右高度大1
            a
         b     c
      d    e

    - 左边满，右完全二叉树，且高度一样
            a
         b     c
      d    e  f

    如果四种都不成立，必不是完全二叉树。满足一种情况就是完全二叉树

     */





    public static class Info2{
        // 是否满二叉树
        public boolean isFull;
        // 是否完全二叉树
        public boolean isCBT;
        // 高度
        public int height;

        public Info2(boolean isFull, boolean isCBT, int height) {
            this.isFull = isFull;
            this.isCBT = isCBT;
            this.height = height;
        }
    }


    // 对于任何节点都要返回Info2
    public static Info2 process2(Node x) {
        if (x == null) {
            return new Info2(true, true, 0);
        }
        Info2 leftInfo = process2(x.getLeft());
        Info2 rightInfo = process2(x.getRight());

        int heigh = Math.max(leftInfo.height, rightInfo.height) + 1;

        boolean isFull = leftInfo.isFull && rightInfo.isFull && leftInfo.height == rightInfo.height;

        // 最不好加工的是是否是完全二叉树 分4种情况
        boolean isCBT = false;
        if (isFull) {
            // 情况1
            isCBT = true;
        } else {
            // 不满  但可能是完全二叉树
            if (leftInfo.isCBT && rightInfo.isCBT) {
                // 左右必须都是完全 才有可能是完全

                // 情况2
                if (leftInfo.isCBT
                        && rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                // 情况3
                if (leftInfo.isFull
                        && rightInfo.isFull
                        && leftInfo.height == rightInfo.height + 1) {
                    isCBT = true;
                }
                // 情况4
                if (leftInfo.isFull
                        && rightInfo.isCBT
                        && leftInfo.height == rightInfo.height) {
                    isCBT = true;
                }
            }
        }
        return new Info2(isFull, isCBT, heigh);
    }





    /*


        例7：给定一棵二叉树的头结点head，和另外两个节点 a b，返回a b的最低公共祖先。


                    1
             2              3
         4       5      6       7

       8   9   10  11  12 13  14



                      1
             2              3
         4       5      6       7

       8   9   10
             11



     */








}



