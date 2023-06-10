/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-10 12:13:46
 * @LastEditTime: 2023-06-10 12:57:49
 */
package hydro;

import java.util.Scanner;

/**
 * 题目来源：https://codefun2000.com/p/P1087
 * 
 * 题目描述：在游戏中，敌人的位置将被一个二维坐标(x,y)所描述。塔子哥有一个全屏技能，该技能能一次性将若干敌人一次性捕获。
 * 捕获的敌人之间的横坐标的最大差值不能大于A，纵坐标的最大差值不能大于B。
 * 
 * 输入描述：第一行三个整数N,A,B，表示共有N个敌人，塔子哥的全屏技能的参数A和参数B。
 * 接下来N行，每行两个数字x,y，描述一个敌人所在的坐标1≤N≤500, 1≤A,B≤1000，1≤x,y≤1000
 * 
 * 输出描述：一行,一个整数表示塔子哥使用技能单次所可以捕获的最多数量。
 * 
 * 这道题目是二维数组前缀和，没想到，没做出来
 * 对于一维和二维前缀和的写法都得继续加强训练，有些忘了
 */
public class Problem44 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[][] grid = new int[1001][1001];
        int n = in.nextInt(), a = in.nextInt(), b = in.nextInt();
        in.nextLine();
        while (n > 0) {
            int x = in.nextInt();
            int y = in.nextInt();
            grid[x][y] = 1;

            n--;
        }

        int[][] preSum = new int[1002][1002];
        for (int i = 1; i <= 1001; i++) {
            for (int j = 1; j <= 1001; j++) {
                preSum[i][j] = preSum[i - 1][j] + preSum[i][j - 1] - preSum[i - 1][j - 1] + grid[i - 1][j - 1];
            }
        }

        // 求某个位置[i,j]到[i+a, j+b]的范围内有多少个敌人
        int count = 0;
        for (int i = a + 1; i <= 1001; i++) {
            for (int j = b + 1; j <= 1001; j++) {
                count = Math.max(count, preSum[i][j] - preSum[i - a - 1][j] - preSum[i][j - b - 1] + preSum[i - a - 1][j - b - 1]);
            }
        }
        System.out.println(count);

        in.close();
    }
}
