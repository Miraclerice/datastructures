package com.itxie.sparsearray;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SparseArray {
    public static void main(String[] args) {
        //创建一个原始的二维数组11*11
        //0:表示没有1棋子，1表示黑子，2表示篮子
        int[][] chessArr1 = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        chessArr1[4][5] = 2;
        //输出原始二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr1) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        //将二维数组转稀疏数组的思想
        //1.先遍历二维数组得到非零数据的个数
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }

        //2.创建对应的稀疏数组
        int[][] sparseArr = new int[sum + 1][3];
        //给稀疏数组赋值
        sparseArr[0][0] = chessArr1.length;
        sparseArr[0][1] = chessArr1[0].length;
        sparseArr[0][2] = sum;

        //遍历二维数组，将非0的值放到sparseArr中
        int count = 0;//count用于记录是第几个非0数据
        for (int i = 0; i < chessArr1.length; i++) {
            for (int j = 0; j < chessArr1[0].length; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }

        //输出稀疏数组形式
        System.out.println();
        System.out.println("得到稀疏数组为：");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArr[i][0], sparseArr[i][1], sparseArr[i][2]);
        }
        System.out.println();

        //将稀疏数组恢复成原始的二维数组
        /*
        1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组，比如上面的 chessArr2 = int[11][11]
        2. 在读取稀疏数组后几行的数据，并赋给 原始的二维数组 即可.
         */
        //1.先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int[][] chessArr2 = new int[sparseArr[0][0]][sparseArr[0][1]];

        //2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可.
        for (int i = 1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }

        //输出恢复后的二维数组
        System.out.println();
        System.out.println("恢复后的二维数组：");

        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }


        //将稀疏数组输出到磁盘上map.data
        File file = new File("map.data");
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            for (int[] row : sparseArr) {
                for (int data : row) {
                    bw.write(data + "\t");
                }
                bw.write("\n");
                bw.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //从硬盘读取稀疏数组，并进行恢复
        //获取硬盘中稀疏数组的行数，并将其存储到一个list中
        File src = new File("map.data");
        BufferedReader br = null;
        List<Integer> list = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(src));
            String line;
            while ((line = br.readLine()) != null) {
                String[] str = line.split("\t");
                for (int i = 0; i < str.length; i++) {
                    list.add(Integer.parseInt(str[i]));
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }


        //从硬盘读取稀疏数组到内存种的方法二：直接对之前的list进行操作
        //创建稀疏数组
        int sparseArr2[][] = new int[list.get(2) + 1][3];
        int j = 0;
        for (int i = 0; i < list.size(); i = i + 3) {
            sparseArr2[j][0] = list.get(i);
            sparseArr2[j][1] = list.get(i + 1);
            sparseArr2[j][2] = list.get(i + 2);
            j++;
        }

        System.out.println("-----------------从硬盘种读取的稀疏数组---------------------");
        for (int[] row2 : sparseArr2) {
            for (int data : row2) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}

