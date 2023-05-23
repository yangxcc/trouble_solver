package hydro;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1035
 * 
 * 问题描述：传统的跳跳棋棋盘呈直线状，共有 N 格，起始位置和结束位置都在棋盘之外。
 * 每一格都对应一个不同的积分值，而每次跳跃都必须跳过一个或多个格子，
 * 而且不允许跳到相邻的格子上，也不可以回跳，游戏的目标是通过跳跃来获取最高的积分。
 * 
 * 输入描述：第一行为整数 N ,表示跳棋格数 (1≤N≤100000)
 * 第二行为每一格代表的分数 (1≤M≤1000)
 * 
 * 输出描述：能获得的最高积分
 * 
 * 打家劫舍问题
 */
public class Problem33 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        // dp[i]表示的是[0, i]能够获得的最大积分
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        // base case一定要写对
        dp[0] = arr[0];
        dp[1] = Math.max(dp[0], arr[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i - 1], dp[i - 2] + arr[i]);
        }

        System.out.println(dp[n - 1]);
        in.close();
    }

    private int method2(int[] arr) {
        int n = arr.length;
        // dp[i][0]表示第i间屋子没被偷
        // dp[i][1]表示第i间屋子被偷了
        int[][] dp = new int[n][2];
        dp[0][0] = 0;
        dp[0][1] = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = dp[i - 1][0] + arr[i];
        }

        return Math.max(dp[n - 1][0], dp[n - 1][1]);
    }
}
