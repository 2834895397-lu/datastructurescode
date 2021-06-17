package com.atguigu.recursion;

/**
 * @Author jacklu
 * @Date 11:58:55 2021/03/23
 */
public class MiGong {
    public static void main(String[] args) {
        //先创建一个二位数组, 模拟迷宫
        //地图
        int[][] map = new int[8][7];
        //使用1表示墙
        //上下全部置为1
        for (int i = 0; i < 7; i++) {
            map[0][i] = 1;
            map[7][i] = 1;
        }
        //左右全部置为1
        for (int i = 0; i < 8; i++) {
            map[i][0] = 1;
            map[i][6] = 1;
        }
        //设置挡板
        map[3][1] = 1;
        map[3][2] = 1;

        //输出地图
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }


        System.out.println("路线图如下:");

        //使用递归给小球找路
        setWay(map, 1, 1);

        //输出地图
        for (int[] ints : map) {
            for (int anInt : ints) {
                System.out.printf("%d\t", anInt);
            }
            System.out.println();
        }
    }

    /**
     * 使用递归来给小球找路
     *
     * @param map 地图
     * @param i   从哪个位置开始找
     * @param j   从哪个位置开始找
     *            如果小球能够到达map[6][5]位置, 则说明找到通路
     *            约定: 相当于map[i][j]为0表示没有走过, 当为1表示为墙, 为2的时候表示走过该点, 3表示已经走过该点,
     *            但是走不通
     *            在走迷宫的时候, 需要顶一个策略(方法): 下 -> 右 -> 上 -> 左, 如果该点走不通, 在回溯
     */
    public static Boolean setWay(int[][] map, int i, int j) {
        if (map[6][5] == 2) {
            return true;
        } else {
            if (map[i][j] == 0) {//如果当前这个点还没有走过
                //按照策略走
                map[i][j] = 2;//假定该点是可以走通的
                if (setWay(map, i + 1, j)) {//往下走
                    return true;
                } else if (setWay(map, i, j + 1)) {//往右走
                    return true;
                } else if (setWay(map, i - 1, j)) {//往上走
                    return true;
                } else if (setWay(map, i, j - 1)) {//往左走
                    return true;
                } else {//说明该点走不通, 是死路
                    map[i][j] = 3;
                    return false;
                }
            } else {//如果map[i][j] != 0, 可能是1,2,3
                return false;

            }
        }
    }
}
