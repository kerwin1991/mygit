package com.wx.class03;

import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

public class HeapSort {



    // 复杂度  n*logn   优势：  额外空间复杂度o(1) 没有递归
    public static void heapSort(int[] arr) {

        if (arr == null || arr.length < 2) {
            return;
        }
        // 1 变为大根堆
        // 1.1 方法一 n*logn
        for (int i = 0; i < arr.length; i++) {
            heapInsert(arr, i);  // 每遍历到一个元素  当前元素前面的元素调整成一个大根堆 遍历结束，这个arr是个大根堆
        }
        // 1.2 方法二
        // o(n)   <-----  T(N) = N/2*1 + N/4*2 + N/8*3 + N/16*4
        //                2T(N) = N + N/2*2 + N/4*3 + N/8*4
        //                T(N) = N + N/2 + N/4 + N/8 - N/4  收敛于 T(N)
//        for (int i = arr.length-1; i >=0 ; i--) {
//            heapify(arr, i, arr.length);
//        }

        int heapSize = arr.length;
        swap(arr, 0, --heapSize);// 最大元素放到arr最后
        // n*logn
        while (heapSize > 0) {
            heapify(arr, 0, heapSize); //
            swap(arr, 0, --heapSize);
        }


    }

    // 调整结构
    // 新加进来的数，先停在index位置，依次往上移动，移到0位置，或者pk不过父亲，停。
    // 大根堆的insert过程。
    private static void heapInsert(int[] arr, int idx) {
            /*int p = (idx-1)/2;
            while (p != idx && p >= 0) {
                if (arr[p] >= arr[idx]) {
                    break;
                }
                swap(arr, p, idx);
                idx = p;
                p = (idx - 1) / 2;
            }*/
        // -1/2 = 0   包含了pk到头结点的情况了 当idx=0 (idx-1)/2=0
        while (arr[idx] > arr[(idx - 1) / 2]) {
            swap(arr, (idx - 1) / 2, idx);
            idx = (idx - 1) / 2;
        }

    }

    // 从idex位置往下沉
    // 停：我的较大孩子都不再比idx大，已经没孩子了
    private static void heapify(int[] arr, int index, int heapSize) {
        // 索引
        int left = 2 * index + 1;
        while (left < heapSize) {// 如果有左孩子，有孩子，可能有，可能没有
            // 选出比较大的孩子索引
            // 如果有右孩子，且右孩子大，取右孩子索引，否则取左孩子索引
            int largestChild = (left + 1 < heapSize) && arr[left + 1] > arr[left] ? left + 1 : left;
            // 父跟最大的孩子比较一次，取最大的那个
            int largest = arr[largestChild] > arr[index] ? largestChild : index;
            // 如果父就是最大的了 不需要再下沉了 跳出
            if (largest == index) {
                break;
            }
            // 要交换 父要下沉
            swap(arr, largest, index);
            // 父来到最大的位置了
            index = largest;
            left = index * 2 + 1;
        }


    }

    private static void swap(int[] heap, int a, int b) {
        heap[a] = heap[a] ^ heap[b];
        heap[b] = heap[a] ^ heap[b];
        heap[a] = heap[a] ^ heap[b];
    }


    // java 自带的优先级堆
    @Test
    public void sysHeap() {
        // 默认小根堆
        PriorityQueue<Integer> heap = new PriorityQueue<>();
        heap.add(5);
        heap.add(7);
        heap.add(4);
        System.out.println(heap.peek());// 看看
        heap.add(2);
        heap.add(1);
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }


    // 优先级队列可以接受重复值的
    // 有序队列 是不能有重复的 treemap 的key不能重复
    @Test
    public void sysHeap2() {
        // 大根堆 传了个比较器
        PriorityQueue<Integer> heap = new PriorityQueue<>(new MyComparator());
        heap.add(5);
        heap.add(7);
        heap.add(4);
        heap.add(2);
        heap.add(1);
        heap.add(7);
        while (!heap.isEmpty()) {
            System.out.println(heap.poll());
        }

    }





    public static class MyComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2 - o1;
        }
    }


    public static void main(String[] args) {
        System.out.println(-1/2);
    }


    @Test
    public void test1() {
        // 一个arr 所有数排好序，（升序），每个数移动的距离不会超过K，比如5，
        /*
        搞一个小根堆，放如前k+1个数，然后弹出一个数，一定就是最小的数，就是0位置的数
        继续把k+2位置的数放入堆，弹，放入1位置
        。。。
         */



    }

















}
