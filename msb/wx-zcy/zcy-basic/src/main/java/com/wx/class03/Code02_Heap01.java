package com.wx.class03;

public class Code02_Heap01 {


     /*
     堆结构：
     [3，4，5，1，0，8，2]
     大根堆
     heapsize=0   目前大根堆上没有数

     需求：把一堆数组织成大根堆
     思路：和自己的父节点pk，如果大于父节点，交换值


     需求：把大根堆的最大值取出后，要求剩下的元素依然是大根堆
     思路：
     比如heapsize=10
     取出位置为0的数字，作为返回值，然后将最后一个元素放到位置0，将heapsize--，heapsize代表了当前数组的边界
     然后需要调整变更后的数组为大根堆，思路：从位置为0的元素开始，跟自己的两个孩子的比较大的那个值节点pk，小于孩子节点，就交换位置，继续往下pk，
     直到边界为止。

     int m = arr[0]
     arr[0] = arr[9]
     heapsize=9



     堆排序：
     先把整个数组排成大根堆。


     建堆：
     从上往下：o(nlogn)         heapinsert
     从下网上：收敛于 O(n)  更快  heapify


      */

    public static class MyMaxHeap_ {


        private int heapSize;
        private int[] heap;
        private final int limit;


        public MyMaxHeap_(int limit) {
            heap = new int[limit];
            heapSize = 0;
            this.limit = limit;
        }

        public boolean isEmpty() {
            return heapSize == 0;
        }

        public boolean isFull() {
            return heapSize == limit;
        }


        public void push(int value) {

            if (heapSize == limit) {
                throw new RuntimeException("heap is Full");
            }

            heap[heapSize] = value;
            // 调整大根堆
            heapInsert(heap, heapSize++);
            
            
        }

        private void heapInsert(int[] arr, int idx) {
            /*int p = (idx-1)/2;
            while (p != idx && p >= 0) {
                if (arr[p] >= arr[idx]) {
                    break;
                }
                swap(arr, p, idx);
                idx = p;
                p = (idx - 1) / 2;
            }*/

            while (arr[idx] > arr[(idx - 1) / 2]) {
                swap(arr, (idx - 1) / 2, idx);
                idx = (idx - 1) / 2;
            }

        }


        public int pop() {
            int ans = heap[0];
            swap(heap, 0, --heapSize); // 堆缩了一个
            heapify(heap, 0, heapSize); // 从0位置出发
            return ans;
        }

        /**
         * 从index位置，往下看，不断的下沉 停：我的孩子都不比我大 或者已经没孩子了
         * <p>
         * 复杂度 logN
         *
         * @param arr
         * @param index
         * @param heapSize 堆大小
         */
        private void heapify(int[] arr, int index, int heapSize) {
            // 索引
            int left = 2 * index + 1;
            while (left < heapSize) {
                // 比较大的孩子索引
                int largestChild = (left + 1 < heapSize) && arr[left + 1] > arr[left] ? left + 1 : left;
                int largest = arr[largestChild] > arr[index] ? largestChild : index;
                if (largest == index) {
                    break;
                }
                swap(arr, largest, index);
                index = largest;
                left = index * 2 + 1;
            }


        }

        private void swap(int[] heap, int a, int b) {
            heap[a] = heap[a] ^ heap[b];
            heap[b] = heap[a] ^ heap[b];
            heap[a] = heap[a] ^ heap[b];
        }


        public static void heapSort(int[] arr) {

            if (arr == null || arr.length < 2) {
                return;
            }
            for (int i = 0; i < arr.length; i++) {


            }



        }


    }

    public static void main(String[] args) {
        System.out.println(-1/2);
    }


}
