package com.wx.class03;

import org.junit.Test;

import java.util.Arrays;

public class SmallSum {

    // 求小和  找左边比右边小的/找右边比左边大的 相等先拷贝右边
    // 逆序对  找左边比右边大的/找右边比左边小的 相等先拷贝左边  课后实现吧。。。。。。
    public static void main(String[] args) {
        int[] arr = new int[]{1,3,4,2,5};
        int result = process(arr, 0, arr.length - 1);
        System.out.println(result);
    }

    public static int process(int[] arr, int l, int r) {
        if (l == r) {
            return 0;
        }
        int mid = l + ((r - l) >> 1);
        return process(arr, l, mid) +
        process(arr, mid + 1, r) +
        merge(arr, l, mid, r);
    }


    // 将 l - m，m+1 - r 上合并  复杂度 o(n) + o(n)  --> o(n)
    private static int merge(int[] arr, int L, int M, int R) {
        int[] help = new int[R - L + 1];
        int i = 0;
        int p1 = L;
        int p2 = M + 1;
        int res = 0;
        // 会有一个先越界
        while (p1 <= M && p2 <= R) {
            res += arr[p1] < arr[p2] ? (R - p2 + 1) * arr[p1] : 0;
            help[i++] = arr[p1] < arr[p2] ? arr[p1++] : arr[p2++];  // 只有小于 才先拷贝左边，等于 先拷贝右边
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
        return res;
    }


    // ---------   - -   - -- - - - - - - - - - - - -- - - - - - - - - - - - - - - - - - - - - - - - - -- - - -  --



    /**
     * 快速排序
     * partition 分层 分区
     * <p>
     * 数组arr 一个数num，小于等于num的放数组左边，大于num的放数组右边
     * 要求额外空间复杂度o(1) 时间复杂度o(n)
     * 思路：用一个变量p记录当前最后一个小于num的位置，初始值-1 遇到大于的继续遍历i++ 遇到小于的 与p的下一个交换 i++ p++
     * <p>
     * 升级：分三块 小于num放左边 等于放中间 大于的放右边 【荷兰国旗问题】
     * 思路：
     * 小于区域指针p1默认-1 大于区域指针p2默认数组的长度size
     * i == num  i++
     * i < num i与小于区域右一个交换 小于区域右扩 i++
     * i > num i与大于区域左一个交换 大于区域左扩 i不动
     */

    // 在arr的L R范围上完荷兰国旗的划分 以arr[R]作为划分值 划分成小于arr[R]的放在L-R的左边 大于在右边
    // 返回等于区域的左边界 右边界
    public static int[] netherlandsFlag(int[] arr, int L, int R) {
        if (L > R) {
            return new int[]{-1,-1};
        }
        if (L == R) {
            return new int[]{L,R};
        }
        int base = arr[R];
        int l = L - 1; // 小于区域
        int r = R + 1; // 大于区域
        int p = L; // 移动指针
        while (p < r) {
            if (arr[p] < base) {
                // 交换
                swap(arr, ++l, p++);
            } else if (arr[p] > base) {
                swap(arr, --r, p);
            } else {
                p++;
            }
        }
        System.out.println(l + "-" + r);
        return new int[]{l+1,r-1};
    }
    // 交换两索引下的数据
    private static void swap(int[] arr, int idx1, int idx2) {
        if (idx1 == idx2) {
            return;
        }
        arr[idx1] = arr[idx1] ^ arr[idx2];
        arr[idx2] = arr[idx1] ^ arr[idx2];
        arr[idx1] = arr[idx1] ^ arr[idx2];
    }

    @Test
    public void testnetherlandsFlag() {
        System.out.println(Arrays.toString(netherlandsFlag(new int[]{3,1,4,2,7,2}, 0, 5)));
    }


}
