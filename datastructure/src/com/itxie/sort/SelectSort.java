package com.itxie.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 选择排序
 *
 * @author 小谢同学
 * @version 1.0
 * @date 2022/09/06  22:04:23
 **/


public class SelectSort {
    public static void main(String[] args) {
//        int[] arr = new int[]{101, 34, 119, 1, 10, 3};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,80000)随机数
            arr[i] = (int) (Math.random() * 80000);
        }
        System.out.println("排序前");

//        Date date1 = new Date();
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        String dateStr1 = format.format(date1);
//        System.out.println("排序前时间是" + dateStr1);

        long l1 = System.currentTimeMillis();
        //System.out.println("排序前" + Arrays.toString(arr));
        selectSort(arr);
        //System.out.println("排序后" + Arrays.toString(arr));
        System.out.println("排序后");

//        Date date2 = new Date();
//        String dateStr2 = format.format(date2);
//        System.out.println("排序后时间是" + dateStr2);
        long l2 = System.currentTimeMillis();
        System.out.println(l2-l1);
    }


    //时间复杂度O(n^2)
    public static void selectSort(int[] arr) {

        for (int i = 0; i < arr.length - 1; i++) {
            //最小值索引
            int minIndex = i;
            //最小值
            int minValue = arr[i];
            for (int j = i + 1; j < arr.length; j++) {
                if (minValue > arr[j]) {
                    minValue = arr[j];
                    minIndex = j;
                }
            }
            //将最小值放在arr[i],交换
            if (minIndex != i) {
                arr[minIndex] = arr[i];
                arr[i] = minValue;
            }
            /*System.out.println("第" + (i + 1) + "轮后");
            System.out.println(Arrays.toString(arr));*/
        }
    }
}
