package com.itxie.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 希尔排序
 *
 * @author 小谢同学
 * @version 1.0
 * @date 2022/09/06  23:22:20
 **/


public class ShellSort {
    public static void main(String[] args) {
        //int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};

        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            //[0,80000)随机数
            arr[i] = (int) (Math.random() * 8000000);
        }

        System.out.println("排序前：");

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = format.format(date1);
        System.out.println("排序前时间是" + dateStr1);

        long l1 = System.currentTimeMillis();

        //System.out.println(Arrays.toString(arr));

        //shellSort(arr);
        shellSort2(arr);

        long l2 = System.currentTimeMillis();

        System.out.println("排序后：");

        System.out.println(l2 - l1);

        Date date2 = new Date();
        String dateStr2 = format.format(date2);
        System.out.println("排序后时间是" + dateStr2);
        //System.out.println(Arrays.toString(arr));
    }

    /**
     * 交换式希尔排序
     *
     * @param arr 数组
     */
    public static void shellSort(int[] arr) {
        int temp = 0;
        int count = 0;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //遍历各组中所有元素，步长为gap
                for (int j = i - gap; j >= 0; j -= gap) {
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮后：");
//            System.out.println(Arrays.toString(arr));
        }
    }

    /**
     * 对交换式希尔排序改进：移位法希尔排序
     *
     * @param arr 数组
     */
    public static void shellSort2(int[] arr) {
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            for (int i = gap; i < arr.length; i++) {
                //待插入的索引
                int j = i;
                int temp = arr[j];
                if (arr[j] < arr[j - gap]) {
                    while (j - gap >= 0 && temp < arr[j - gap]) {
                        //移动
                        arr[j] = arr[j - gap];
                        j -= gap;
                    }
                    //退出while后，就给temp找的插入位置
                    arr[j] = temp;
                }
            }
//            System.out.println("希尔排序第" + (++count) + "轮后：");
//            System.out.println(Arrays.toString(arr));
        }
    }

}
