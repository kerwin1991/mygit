package com.wx.class03;


// 有bug 看老师的code02 是没有bug的。。。。。

public class TrieTree {


    // 前缀树 节点类型
    public static class Node1{

        public int pass;
        public int end;

        public Node1[] nexts;

        //  nexts

        public Node1() {
            pass = 0;
            end = 0;
            // 根据是否为空 判断是否有那条路
            /**
             * char tmp = 'b'  找这条路(tmp - 'a') 是否存在
             * 0 a
             * 1 b
             * 2 c
             * ...
             * 25 z
             * nexts[i] == null i方向路不存在，否则存在
             *
             */
            nexts = new Node1[26];
        }
    }

    public static class Trie1{
        private Node1 root;

        public Trie1() {
            root = new Node1();
        }


        // 添加一个word
        public void insert(String word) {
            // abc  挂在数上
            if (word == null || word.length() == 0) {
                return;
            }

            char[] str = word.toCharArray();
            Node1 node = this.root;
            node.pass++;
            for (int i = 0; i < str.length; i++) {
                int path = str[i] - 'a';//
                if (node.nexts[path] == null) {
                    node.nexts[path] = new Node1();
                }
                node = node.nexts[path];
                node.pass++;
            }
            node.end++;
        }



        // 删除一个word 比较复杂 要避免内存泄漏
        /*
        +abc
        +absk
        -absk  最后sk节点没用了 可删掉
        不删的话，长时间会导致内存泄漏
         */
        public void delete(String word) {
            // 先查 看到底有没有
            if (search(word) != 0) {
                char[] chs = word.toCharArray();
                Node1 node = this.root;
                node.pass--;
                for (int i = 0; i < chs.length; i++) {
                    int path = chs[i] - 'a';//
                    if (--node.nexts[path].pass == 0) {
                        node.nexts[path] = null;
                        return;// 删到s的时候截断，后面全部删除了，java中k会被回收的，头结点找不到k
                    }
                    node = node.nexts[path];
                }
                node.end--;

            }






        }

        // 判断一个word 出现了几次
        public int search(String word) {
            if (word == null) {
                return 0;
            }
            char[] chs = word.toCharArray();
            Node1 node = this.root;
            int path = 0; // 复用这个变量
            for (int i = 0; i < chs.length; i++) {
                path = chs[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.end;
        }

        // 所有加入的字符串中，有几个是以pre这个字符串作为前缀的
        public int prefixNumber(String pre) {
            if (pre == null) {
                return 0;
            }
            char[] chs = pre.toCharArray();
            Node1 node = this.root;
            int path = 0; // 复用这个变量
            for (int i = 0; i < chs.length; i++) {
                path = chs[i] - 'a';
                if (node.nexts[path] == null) {
                    return 0;
                }
                node = node.nexts[path];
            }
            return node.pass;

        }



    }

    public static void main(String[] args) {
        String a = "{\"requestId\":\"d5a10f2d-c4f7-4b10-aaf4-f38d33d28eaa\",\"acceptance\":{\"result\":\"REJECTED\",\"reasons\":[\"UNCLASSIFIED_DOCUMENT\"]}}";
    }

}
