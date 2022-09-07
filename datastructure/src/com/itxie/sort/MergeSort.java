package com.itxie.sort;

import java.util.Arrays;

/**
 * 归并排序 (空间换时间）
 *
 * @author 小谢同学
 * @version 1.0
 * @date 2022/09/07  11:03:10
 **/


public class MergeSort {
    public static void main(String[] args) {
        //int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            //[0,80000)随机数
            arr[i] = (int) (Math.random() * 8000000);
        }

        int[] temp = new int[arr.length];

        long l1 = System.currentTimeMillis();
        merageSort(arr, 0, arr.length - 1, temp);
        //System.out.println("归并排序后：" + Arrays.toString(arr));
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
    }

    /**
     * 归并排序
     *
     * @param arr   排序的原始数组
     * @param left  左索引
     * @param right 右索引
     * @param temp  临时数组
     */
    public static void merageSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            //向左递归分解
            merageSort(arr, left, mid, temp);
            //向右递归分解
            merageSort(arr, mid + 1, right, temp);
            //合并
            merge(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并方法
     *
     * @param arr   排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  临时数组
     */
    public static void merge(int[] arr, int left, int mid, int right, int[] temp) {
        //初始化i，左边有序序列的初始索引
        int i = left;
        //初始化j，右边有序序列的初始索引
        int j = mid + 1;
        //指向临时数组的当前索引
        int t = 0;

        //1.先把左右两边（有序）的数据按照规则填充到temp数组，直到左右两边的有序序列有一边处理完毕为止
        while (i <= mid && j <= right) {
            //如果左边的有序序列的当前元素小于等于右边有序序列的当前元素，将左边当前元素拷贝到temp
            if (arr[i] <= arr[j]) {
                //同时指针需要后移
                temp[t++] = arr[i++];
            } else {
                //如果右边的有序序列的当前元素小于等于左边有序序列的当前元素，将右边当前元素拷贝到temp
                temp[t++] = arr[j++];
            }
        }

        //2.把有剩余数据的一边数据依次全部填充到temp
        //左边的有序序列还有剩余的元素，就全部填充到temp
        while (i <= mid) {
            temp[t++] = arr[i++];
        }

        //左边的有序序列还有剩余的元素，就全部填充到temp
        while (j <= right) {
            temp[t++] = arr[j++];
        }

        //3.将temp数组拷贝到arr
        //注意，并不是每次拷贝所有
        t = 0;
        int tempLeft = left;
        //查看每次的临时数组
        //System.out.println("tempLeft = " + tempLeft + "\tright = " + right);
        while (tempLeft <= right) {
            arr[tempLeft++] = temp[t++];
        }
    }
}
