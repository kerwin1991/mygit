package com.wx.class04;


import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class LevelTraversalBT {


      /*

        求出二叉树节点数最多层的节点数？

        实现二叉树的按层遍历

        1、其实就是宽度优先遍历，用队列

        2、可以通过设置flag变量的方式，来发现某一层的结束（看题目）


     */






    public static void level(Node head) {

        if (head != null) {
            // 队列
            Queue<Node> queue = new LinkedList<>();
            queue.add(head);
            while (!queue.isEmpty()) {
                Node cur = queue.poll();

                System.out.println(cur.value);

                if (cur.left != null) {
                    queue.add(cur.left);
                }

                if (cur.right != null) {
                    queue.add(cur.right);
                }

            }


        }


    }

    // 哪一层最宽？  节点数量最多的，不一定是最后一层最宽
    // 发现哪一层开始 结束。发现一层的开始或者结束就行，每一层的结束一定意味着下一层的开始


    public static void maxWidthUseMap(Node head) {

        if (head == null) {
            return;
        }
        // 队列
        Queue<Node> queue = new LinkedList<>();
        // map 记录这个节点在哪行
        Map<Node, Integer> nodeLevelMap = new HashMap<>();
        int curLevel = 1; // 统计到哪行了
        int curLevelNodes = 0; // 当前这行的节点数量
        int max = 0; // 最大节点数量

        nodeLevelMap.put(head, 1); // 当前节点在第一行
        queue.add(head);

        while (!queue.isEmpty()) {
            // 弹出一个统计一个
            Node curNode = queue.poll();
            // 当前节点的所在层
            Integer curNodeLevel = nodeLevelMap.get(curNode);
            // 把弹出节点的子节点加入队列，入队列的顺序就是从高到低的顺序
            if (curNode.left != null) {
                // 子节点肯定在当前节点下一层
                nodeLevelMap.put(curNode.left, curNodeLevel + 1);
                queue.add(curNode.left);
            }
            if (curNode.right != null) {
                nodeLevelMap.put(curNode.right, curNodeLevel + 1);
                queue.add(curNode.right);
            }

            if (curLevel == curNodeLevel) {
                //正在统计的这一行
                curLevelNodes++;
                // max = Math.max(max, curLevelNodes); // 在这加不太好
            } else {
                // 是将上一层的节点数量跟max做了对比
                max = Math.max(max, curLevelNodes);
                //新的一行开始了
                curLevel++;
                curLevelNodes=1;
            }
        }
        // 把最后一层的节点数量跟max比较
        max = Math.max(max, curLevelNodes);
        System.out.println("最宽的层的宽度是：max=" + max);

    }

    /**
     * 不使用map的统计
     * @param head
     * @return
     */
    public static int maxNotWidthUseMap(Node head) {

        if (head == null) {
            return 0;
        }
        // 进入循环前 初始化变量
        // 当前层最后一个节点，第一个节点一定是第一层最后一个节点
        Node curLevelEnd = head;
        // 下一层的最后一个节点
        Node nextLevelEnd = null;
        // 当前的最长层
        int max = 0;
        // 当前层的节点数量 弹出时再统计
        int currentLevelNodes = 0;
        // 队列
        Queue<Node> queue = new LinkedList<>();
        // 初始化
        queue.add(head);

        while (!queue.isEmpty()) {

            // 弹出 入栈是有序的，弹出也是有序的，
            Node curNode = queue.poll();
            // 子节点入队列，并且改变下一层的最后一个节点
            if (curNode.left != null) {
                queue.add(curNode.left);
                nextLevelEnd = curNode.left;
            }
            // 某一层的最后一个节点弹出时,下一层的最后一个节点正好刚刚入队列
            // 因此，此时的 nextLevelEnd 就是一个endNode，被弹出时，表示该层已结束，就可统计次数了
            if (curNode.right != null) {
                queue.add(curNode.right);
                nextLevelEnd = curNode.right;
            }
            // 统计个数
            currentLevelNodes++;

            // 上一层已经结束
            if (curNode == curLevelEnd) {
                max = Math.max(max, currentLevelNodes);
                currentLevelNodes = 0;
                curLevelEnd = nextLevelEnd;
            }
        }
        return max;

    }








    class Node{
        private int value;

        private LevelTraversalBT.Node left;

        private LevelTraversalBT.Node right;


    }

}


