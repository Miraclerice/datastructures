package com.itxie.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * 插入排序
 *
 * @author 小谢同学
 * @version 1.0
 * @date 2022/09/06  22:40:48
 **/


public class InsertSort {
    public static void main(String[] args) {
//        int[] arr = {10, 5, 115, 41, 56, 2};
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            //[0,80000)随机数
            arr[i] = (int) (Math.random() * 80000);
        }
        System.out.println("排序前");
        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = format.format(date1);
        System.out.println("排序前时间是" + dateStr1);
        long t1 = System.currentTimeMillis();
        //System.out.println(Arrays.toString(arr));

        insertSort(arr);

        long t2 = System.currentTimeMillis();

        System.out.println("排序后");
       // System.out.println(Arrays.toString(arr));
        System.out.println(t2-t1);

        Date date2 = new Date();
        String dateStr2 = format.format(date2);
        System.out.println("排序后时间是" + dateStr2);

    }

    public static void insertSort(int[] arr) {
        for (int i = 1; i < arr.length; i++) {
            //定义插入的数
            int insertVal = arr[i];
            int insertIndex = i - 1;
            //给insertVal找插入位置
            //1.insertIndex>=0保证insertVal寻找的插入位置不越界
            //2.insertVal < arr[insertIndex]待插入的数据，还没有找到插入数据
            //3.就需要将arr[insertIndex]后移
            while (insertIndex >= 0 && insertVal < arr[insertIndex]) {
                arr[insertIndex + 1] = arr[insertIndex];
                insertIndex--;
            }
            //当退出循环，说明插入位置找到，indexIndex+1
            //判断是否需要赋值
            if(insertIndex + 1 != i){
                arr[insertIndex + 1] = insertVal;
            }
//            System.out.println("第" + i + "轮插入");
//            System.out.println(Arrays.toString(arr));
        }

    }
}
