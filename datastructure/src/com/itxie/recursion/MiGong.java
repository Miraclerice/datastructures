package com.itxie.recursion;

public class MiGong {
    public static void main(String[] args) {
        //创建二维数组，模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下左右全部置1
        for (int i = 0; i < map[0].length; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        for (int i = 1; i < map.length - 1; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }

        //设置挡板，用1表示
        map[3][1] = 1;
        map[3][2] = 1;

        System.out.println("原地图情况~~~~");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }

        //使用递归回溯，小球找路
        //setWay(map,1,1);
        //setWay2(map,1,1);

        //小球走过，并标识过的
        System.out.println("新地图情况~~~~");
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                System.out.print(map[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //使用递归函数回溯来给小球招路
    //说明
    //1.map 表示地图
    //2.i,j表示地图从哪个位置开始找(1,1)
    //3.如果小球能到map[6][5]，则说明通路找到
    //4.约定：当map[i][j]为0表示该点没有走过  当为1表示墙 ；2表示通路可以走；3表示该点已经走过，但走不通
    //5.在走迷宫时，需要确定一个策略（方法） 下->右->上->左，如果该点走不通，再回溯

    /**
     * @param map 表示地图
     * @param i   表示从哪个位置开始找
     * @param j
     * @return 如果找到通路，返回true，否则返回false
     */
    public static boolean setWay1(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                //按照策略 下->右->上->左 走
                map[i][j] = 2;//假定该点是可以走通
                if (setWay1(map, i + 1, j)) {
                    //向下走
                    return true;
                } else if (setWay1(map, i, j + 1)) {
                    //向右走
                    return true;
                } else if (setWay1(map, i - 1, j)) {
                    //向上走
                    return true;
                } else if (setWay1(map, i, j - 1)) {
                    //向左走
                    return true;
                } else {
                    //说明该点走不通，死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j] != 0,可能1,2,3
                return false;
            }
        }
    }

    //改变策略
    public static boolean setWay2(int[][] map, int i, int j) {
        if (map[6][5] == 2) {//通路已经找到
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                //按照策略 上->右->下->左 走
                map[i][j] = 2;//假定该点是可以走通
                if (setWay2(map, i - 1, j)) {
                    //向上走
                    return true;
                } else if (setWay2(map, i, j + 1)) {
                    //向右走
                    return true;
                } else if (setWay2(map, i + 1, j)) {
                    //向下走
                    return true;
                } else if (setWay2(map, i, j - 1)) {
                    //向左走
                    return true;
                } else {
                    //说明该点走不通，死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j] != 0,可能1,2,3
                return false;
            }
        }
    }

    public static String setWayForShort(int[][] map, int i, int j, int count) {

        return null;
    }


}


