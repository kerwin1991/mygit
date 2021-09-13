package com.wx.class03;

import org.junit.Test;

import java.util.Arrays;
// 归并排序
public class MergeSort {

    @Test
    public void test() {
        int[] arr = new int[]{3,0,1,10,1,8,7,20};
        process(arr, 0, arr.length-1);
        System.out.println(Arrays.toString(arr));
    }

    // T(n)  =  2 * T(n/2) + o(n)  a=2 b=2 d=1  套上节课讲的公式
    // 最后 o(n*logn)


    // 递归函数 让 l-r 上有序
    public static void process(int[] arr, int l, int r) {
        if (l == r) {
            return;
        }
        int mid = l + ((r - l) >> 1);
        process(arr, l, mid);
        process(arr, mid + 1, r);
        merge(arr, l, mid, r);
    }
    // 将 l - m，m+1 - r 上合并  复杂度 o(n) + o(n)  --> o(n)
    private static void merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        // 会有一个先越界
        while (p1 <= M && p2 <= R) {
            help[i++] = arr[p1] <= arr[p2] ? arr[p1++] : arr[p2++];
        }
        // 灭有越界的 全部拷贝到help
        // 右边先越界了
        while (p1 <= M) {
            help[i++] = arr[p1++];
        }
        // 左边先越界了
        while (p2 <= R) {
            help[i++] = arr[p2++];
        }
        for (int ii = 0; ii < help.length; ii++) {
            arr[L + ii] = help[ii];
        }
    }

    // 非递归写法  o(n*logn)  没测试呢
    public void mergeSort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        int N = arr.length;
        int mergeSize = 1;

        while (mergeSize < N) {
            int L = 0;

            while (L < N) {
                int M = L + mergeSize - 1;
                if (M >= N) {
                    break;
                }
                int R = Math.min(M + mergeSize, N - 1);
                merge(arr, L, M, R);
                L = R + 1;
            }

            if (mergeSize > N / 2) {
                break;
            }
            mergeSize <<= 1;
        }
    }


    /**
     * o(n*logn) 比 o(n^2) 好在哪儿
     * n^2 浪费比较行为 前一轮比较行为 不影响后面的比较行为
     */


    /**
     * 常见面试题
     * 数组，一个数左边比它小的数的总和，叫数的小和，所有数的小和，累加起来叫数组小和，求数组小和
     * [1,3,4,2,5]
     * 肯定不能用暴力方法，面试的时候是没有分数的
     *
     *
     * merge的时候产生小和
     * 左边比右边小，产生小和 看右边还有几个数 就产生几个小和
     * 左边跟右边一样 不产生 先拷贝右边
     * 左边跟右边大 不产生
     *
     * SmallSum
     */







}
