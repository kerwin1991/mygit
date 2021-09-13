package com.wx.class03;

import org.junit.Test;

import java.util.Arrays;

public class QuickSort {



    /*
    1.0
    partiction
    [       x]
    思路：<=x 放左边 >x 放右边 最后把x放在 >x 第一个数的最左边，一轮后，x所处的
    位置，即是最后排好序，所处的位置。即每一轮递归就会固定一个数
    [<=arr[r]  arr[r]  >arr[r]]

     */

    public void quicksort1(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process1(arr, 0, arr.length - 1);
    }

    private void process1(int[] arr, int L, int R) {
        // 使得在L-R上有序
        if (L >= R) {
            return;
        }
        // 使得M位置的数固定
        int M = partition(arr, L, R);
        process1(arr, L, M - 1);
        process1(arr, M + 1, R);
    }

    private int partition(int[] arr, int L, int R) {
        int base = arr[R];
        int l = L-1;
        int r = R;
        int p = L;
        while (p < r) {
            if (arr[p] <= base) {
                p++;
                l++;
            } else {
                swap(arr, p, --r);
            }
        }
        swap(arr, p, R);
        return p;
    }

    // 2.0
    /*
    利用荷兰国旗 一次搞定一批等于一个数的值


    复杂度，最坏的情况，有序的情况 1，2，4，5，6，7

     */
    public void quicksort2(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process2(arr, 0, arr.length - 1);
    }

    private void process2(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        int[] equalArea = SmallSum.netherlandsFlag(arr, L, R);
        process2(arr, L, equalArea[0] - 1);
        process2(arr, equalArea[1] + 1, R);
    }

    // 3.0

    /*
    之前的版本都是以arr[R]为划分值，3.0 是随机选一个数，如选i位置的数，跟R位置数交换后，作为划分值， 之后快排流程同 2.0
    快排复杂度：nlogn  但是 2.0 1.0 最坏情况 复杂度是 n^2,   3.0 的复杂度是nlogn
    时间复杂度 o(nlogn) 最差情况 o(n^2)  但是这种最差情况是任何case有很小概率发生的；      有一种最差是某种情况，一定最差。
    空间复杂度 o(logn)  最差情况 o(n)

     */

    public void quicksort3(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        process3(arr, 0, arr.length - 1);
    }


    private void process3(int[] arr, int L, int R) {
        if (L >= R) {
            return;
        }
        // 随机的   如果打到中间的值，就比较好  打偏了  综合算下来 复杂度收敛于  n^logn
        swap(arr, (int) (L + Math.random() * (R - L + 1)), R);

        int[] equalArea = SmallSum.netherlandsFlag(arr, L, R);
        process3(arr, L, equalArea[0] - 1);
        process3(arr, equalArea[1] + 1, R);
    }




    private static void swap(int[] arr, int idx1, int idx2) {
        if (idx1 == idx2) {
            return;
        }
        arr[idx1] = arr[idx1] ^ arr[idx2];
        arr[idx2] = arr[idx1] ^ arr[idx2];
        arr[idx1] = arr[idx1] ^ arr[idx2];
    }


    @Test
    public void t1() {
        int[] arr1 = {1, 2, 2, 9, 7, 3, -1, 4};
        quicksort1(arr1);
        System.out.println(Arrays.toString(arr1));

        int[] arr2 = {1, 2, 2, 9, 7, 3, -1, 4};
        quicksort2(arr2);
        System.out.println(Arrays.toString(arr2));

        int[] arr3 = {1, 2, 2, 9, 7, 3, -1, 4};
        quicksort3(arr3);
        System.out.println(Arrays.toString(arr3));

    }

    /**
     * 堆
     * 数组 完全二叉树 （要不这一层是满的，要不这一层正在从左到右依次变满的，要不满，也是最后一层不满，上面的都得是满的）
     * 用数组结构 做出个堆结构
     *            0
     *       1          2
     *    3    4     5     6
     *  7  8 9  10 11 12  13 14
     * 左节点 2n+1   右节点  2n+2   父节点 (n-1)/2
     * 比如，定义个size=7的数组，那么0-6范围就
     *
     *      *            1
     *      *       2          3
     *      *    4    5     6     7
     *      *  8  9 10 11 12 13 14 15
     *      * 左节点 2n   右节点  2n+1   父节点 n/2
     *      固定0位置空着，为了运算快，不用乘法，使用位运算。2n n<<1, 2n+1 n<<1|1,  n/2  n>>1  , 所以很多时候从1开始
     *
     * 堆：
     * 大根堆：每一颗子树的最大值都是自己头节点的值。有任何一颗子树不满足，就不是大根堆。
     * 小根堆：            小
     * 两个都不符合，就不是堆。只是个完全二叉树。
     * 堆：不是大根堆 就是 小根堆
     *
     */
















}

