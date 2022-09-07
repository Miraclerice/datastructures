package com.itxie.sort;

import java.util.Arrays;

/**
 * 快速排序
 *
 * @author 小谢同学
 * @version 1.0
 * @date 2022/09/07  10:07:33
 **/


public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {-9, 78, 0, 23, -567, 70,-1, 100 ,653};
        int[] arr = new int[8000000];
        for (int i = 0; i < 8000000; i++) {
            //[0,80000)随机数
            arr[i] = (int) (Math.random() * 8000000);
        }
        long l1 = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        long l2 = System.currentTimeMillis();
        System.out.println(l2 - l1);
        //System.out.println(Arrays.toString(arr));
    }

    public static void quickSort(int[] arr, int left, int right) {
        int leftPointer = left;
        int rightPointer = right;
        //pivot 中轴值
        int pivot = arr[(left + right) / 2];
        //临时变量交换数值
        int temp = 0;
        //while循环的目的是让比pivot小的值放到左边，大的值放到右边
        while (leftPointer < rightPointer) {
            //在pivot的左边一直找，找到大于等于pivot值，才退出
            while (arr[leftPointer] < pivot) {
                leftPointer += 1;
            }
            //在pivot的右边一直找，找到小于等于pivot值，才退出
            while (arr[rightPointer] > pivot) {
                rightPointer -= 1;
            }
            //如果leftPointer >= rightPointer说明pivot左右两边值，左边值小于等于pivot，右边值大于等于pivot
            if (leftPointer >= rightPointer) {
                break;
            }
            //交换
            temp = arr[leftPointer];
            arr[leftPointer] = arr[rightPointer];
            arr[rightPointer] = temp;

            //如果交换完后，发现arr[leftPointer]==pivot ,rightPointer指针前移
            if (arr[leftPointer] == pivot) {
                rightPointer--;
            }

            //如果交换完后，发现arr[rightPointer]==pivot ,leftPointer指针后移
            if (arr[rightPointer] == pivot) {
                leftPointer++;
            }
        }

        //如果leftPointer==rightPointer，必须rightPointer--，leftPointer++，否则栈溢出
        if (leftPointer == rightPointer) {
            leftPointer++;
            rightPointer--;
        }

        //向左递归
        if (left < rightPointer) {
            quickSort(arr, left, rightPointer);
        }

        //向右递归
        if (right > leftPointer) {
            quickSort(arr, leftPointer, right);
        }
    }
}
