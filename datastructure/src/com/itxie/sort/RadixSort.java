package com.itxie.sort;

import java.util.Arrays;

/**
 * 基数排序
 *
 * @author 小谢同学
 * @version 1.0
 * @date 2022/09/07  12:04:17
 **/


public class RadixSort {
    private static final int BUCKET_LENGTH = 10;

    private static final int BUCKET_PLUS = 10;

    public static void main(String[] args) {

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            //[0,80000)随机数
            arr[i] = (int) (Math.random() * 8000000);
        }

        //int[] arr = {53, 3, 542, 748, 14, 214};
        long l1 = System.currentTimeMillis();

        radixSort(arr);

        long l2 = System.currentTimeMillis();

        System.out.println(l2 - l1);

        //System.out.println(Arrays.toString(arr));
    }

    public static void radixSort(int[] arr) {
        //获取最大数的位数
        int maxNum = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > maxNum) {
                maxNum = arr[i];
            }
        }

        //得到最大数的位数
        int maxLength = (maxNum + "").length();

        //定义二维数组，表示10个桶子，每个桶就是一个二维数组
        int[][] bucket = new int[BUCKET_LENGTH][arr.length];

        //记录每个桶中实际存放多少个数据，定义一个一维数组来记录各个桶的每次放入的数据个数
        int[] bucketElementCounts = new int[BUCKET_LENGTH];

        for (int i = 0, n = 1; i < maxLength; i++, n *= BUCKET_PLUS) {
            //对每一个元素的对应位进行排序处理
            for (int value : arr) {
                //取出每个元素的位数
                int digitOfElement = (value / n) % 10;
                //放入对应桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = value;
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标取出数据，放入原来数组）
            int index = 0;
            //遍历每一桶，并将桶中数据放入原数组
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据才放入原数组
                if (bucketElementCounts[k] != 0) {
                    //循环该桶（即第k个一维数组），放入
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        //取出元素放入arr
                        arr[index++] = bucket[k][l];
                    }
                }
                //该次处理完后，需要将每个桶中数据其清空,bucketElementCounts[k] = 0;
                bucketElementCounts[k] = 0;
            }
            //System.out.println("第" + (i + 1) + "轮，对该位的排序处理arr = " + Arrays.toString(arr));
        }
    }
}
