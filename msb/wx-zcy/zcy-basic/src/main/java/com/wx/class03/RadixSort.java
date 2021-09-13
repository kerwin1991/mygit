package com.wx.class03;


import java.util.Arrays;

/**
 * 基数排序
 *
 * 不能有负数
 * 如果有了，可以找到最小值，如-7，所有数加7再排序，完了再统一减7
 *
 */
public class RadixSort {


    public static void radixSort(int[] arr) {
        if (arr == null || arr.length < 2) {
            return;
        }
        radixSort(arr, 0, arr.length - 1, maxbits(arr));
    }


    private static int maxbits(int[] arr) {
        int max = Integer.MIN_VALUE;
        for (int i : arr) {
            max = Math.min(max, i);
        }
        int res = 0;
        while (max != 0) {
            res++;
            max = max/10;
        }
        return res;
    }

    /**
     * arr 在 l-r范围排序
     * @param arr
     * @param l
     * @param r
     * @param digit arr最大值的十进制位数
     */
    private static void radixSort(int[] arr, int l, int r, int digit) {
        // digit 就是所有数据进出桶的次数

        final int radix = 10;
        int[] help = new int[r - l + 1];
        for (int d = 1; d <= digit; d++) {
            /*
            10个空间
            count[i]当前位是i的数字有多少个
            count[2]当前位是2的数字有多少个
             */
            int[] count = new int[radix];
            // 统计数量
            for (int i = l; i <= r; i++) {
                int v = getDigit(arr[i], d);
                // 统计数量
                count[v]++;
            }
            // 转换count 为 count[i]当前位<=i的数字有多少个
            for (int i = 1; i < count.length; i++) {
                count[i] = count[i - 1] + count[i];
            }
            //
            /*int[] origin = Arrays.copyOfRange(arr, l, r);
            for (int i = r; i >= l; i--) {
                int v = getDigit(origin[i], d);
                arr[--count[v]] = origin[i];
            }*/

            for (int i = r; i >= l; i--) {
                int v = getDigit(arr[i], d);
                help[count[v] - 1] = arr[i];
                count[v]--;
            }
            for (int i = 0,j=l; i < help.length; i++,j++) {
                arr[j] = help[i];
            }
        }
    }

    // 返回num d位上的值
    // 自己实现的 需要跟老师的核对下。。。。。
    private static int getDigit(int num, int d) {
        for (int i = 1; i < d; i++) {
            num = num / 10;
        }
        return num % 10;
    }



    public static void main(String[] args) {
//        System.out.println(getDigit(123, 4));

        int[] arr = new int[]{103,13,27,25,17,9};

        radixSort(arr);

        System.out.println(Arrays.toString(arr));


        String a = "{\"requestId\":\"d5a10f2d-c4f7-4b10-aaf4-f38d33d28eaa\",\"acceptance\":{\"result\":\"REJECTED\",\"reasons\":[\"UNCLASSIFIED_DOCUMENT\"]}}";



    }


}
