package com.itxie.sort;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class BubbleSort {
    public static void main(String[] args) {
        /*int[] arr = {6, 9, -5, 4, 21, 100};
        System.out.println("排序前");
        System.out.println(Arrays.toString(arr));*/

        //测试冒泡排序时间复杂度O(n^2)
        int[] arr = new int[80000];
        for (int i = 0; i < 80000; i++) {
            arr[i] = (int) (Math.random() * 80000);
            //[0,80000)随机数
        }

        System.out.println("排序前");
        //System.out.println(Arrays.toString(arr));

        Date date1 = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr1 = format.format(date1);
        System.out.println("排序前时间是" + dateStr1);

        bubbleSort(arr);
        System.out.println("排序后");
        Date date2 = new Date();
        //System.out.println(Arrays.toString(arr));
        String dateStr2 = format.format(date2);
        System.out.println("排序后时间是" + dateStr2);
    }

    public static void bubbleSort(int[] arr) {
        //临时变量
        int temp = 0;
        //标记是否改变
        boolean flag = false;
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    //如果前面数大于后面数，交换
                    flag = true;
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
           /* System.out.println("第" + (i + 1) + "趟排序后的数组：");
            System.out.println(Arrays.toString(arr));*/

            if (!flag) {//该趟排序没有交换
                break;
            } else {
                flag = false;//刷新标记，下一趟排序比较判断
            }
        }
    }
}
