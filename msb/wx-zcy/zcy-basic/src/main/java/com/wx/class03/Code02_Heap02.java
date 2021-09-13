package com.wx.class03;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Code02_Heap02 {

    // 堆
    public static class MyHeap<T>{

        private ArrayList<T> heap;

        private Map<T, Integer> indexMap;

        private int heapSize;

        private Comparator<? super T> comparator;


        public MyHeap(Comparator<? super T> comparator) {
            this.comparator = comparator;
            heap = new ArrayList<>();
            indexMap = new HashMap<>();
            heapSize = 0;
        }


        public boolean isEmpty() {

            return heapSize == 0;
        }

        public int size() {
            return heapSize;
        }



    }





    /*
    前缀树：
    单个字符串中，把字符从前往后，加到一个多叉树。
    ["abc","abd","sdk","ab"]
    Node： pass  end





     */








}
