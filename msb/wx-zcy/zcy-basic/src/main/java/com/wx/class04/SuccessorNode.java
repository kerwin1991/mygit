package com.wx.class04;

// 后继节点
public class SuccessorNode {






    /*

    题目：

    给定二叉树结构定义
    Node{
        V value
        Node left
        Node right

        // 多了一个指向父的节点
        Node parent
    }

    给你二叉树中的某个节点，返回该节点的后继节点？


    解释：


                    1
               2         3
            4     5   6      7

    后继节点：中序遍历的结果中，指定节点的下一个节点，就是后继节点

    4 2 5 1 6 3 7

    2的后继节点是5，7没有后继节点。


    方法一：先遍历，再找后继，复杂度o(n)
    复杂度高

    找规律，直接从节点x找到它的后继
    如果节点x有右树。那么后继一定是右树的最左节点
    如果节点x没有右树，按照下面的方式找。。


                            a
                 b                         c
         d             e           f             g

      h      i      j      k    l      m      n     o


    h d i b j e k a l f m c n g o

    h-d
    i-b
    j-e
    k-a

    节点x如果没有右树，往上找，直到上一个节点不是其父节点的右节点(是左节点)，这个父就是该节点X的后继


    分析思路：

    中序：左 头 右

    某个节点x，左树的最后一个节点a的后继就是x
    如果一个节点不是父节点的左节点，那一定不是父节点左树的最后一个节点
    整棵树的最右节点没有后继

     */


    public static Node getSuccessorNode(Node node) {


        if (node == null) {
            return node;
        }

        if (node.right != null) { // 有右子树

            return getLeftMost(node.right);

        } else {
            // 没有右子树
            // 找父节点，一直找到当前节点是父节点左孩子位置，该父就是后继
            Node parent = node.parent;
            while (parent != null && parent.right == node) { // 当前节点是其父节点的右孩子
                node = parent;
                parent = node.parent;
            }
            return parent;

        }

    }

    // 获取节点左树上最左的节点
    private static Node getLeftMost(Node node) {

        if (node == null) {
            return node;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }


    public static class Node{


        private int value;

        private Node left;

        private Node right;

        private Node parent;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public Node getParent() {
            return parent;
        }

        public void setParent(Node parent) {
            this.parent = parent;
        }
    }


    /*
    请把一段纸条放在桌子上，然后从纸条的下边向上方对折1次，压出折痕后展开。此时折痕是凹下去的，即折痕突起的方向指向纸条的背面。
    如果从纸条的下边向上方连续对折2次，压出折痕后展开，此时有三条折痕，从上到下依次是 下折痕 下折痕 和 上折痕。
    给定一个输入参数N，代表纸条从下边向上方连续对折N次，请从上到下打印所有折痕的方向。

    例如：N=1 打印 down N=2 打印 down down up





     */








}
