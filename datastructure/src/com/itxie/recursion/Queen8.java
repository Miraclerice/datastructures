package com.itxie.recursion;

public class Queen8 {
    //定义max表示共有多少个皇后
    private int max = 8;
    //定义数组保存皇后放置位置结果
    private int[] array = new int[max];

    private static int count = 0;

    public static void main(String[] args) {
        Queen8 queen = new Queen8();
        queen.check(0);
        System.out.println("一共有"+count+"次解法~~~");
    }

    //放置第n个皇后
    private void check(int n){
        if(n == max){//皇后放好了
            print();
            return;
        }
        //依次放入皇后，看是否冲突
        for(int i = 0; i < max; i++){
            //先把当前这个皇后n，放到改行的第一列
            array[n] = i;
            //判断当放置第n个皇后到第i列时，看是否冲突
            if(judge(n)){
                //不冲突，接着放n+1个皇后，即开始递归
                check(n+1);
            }
        }
    }

    //查看当我们放置ng皇后，就去检测该皇后是否和前面已经摆放的皇后冲突
    private boolean judge(int n) {
        for (int i = 0; i < n; i++) {
            if(array[i] == array[n] || Math.abs(n-i) == Math.abs(array[n] - array[i])){
                return false;
            }
        }
        return true;
    }

    //将皇后位置输出
    private void print() {
        count++;
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
