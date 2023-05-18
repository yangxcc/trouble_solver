/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-18 21:47:40
 * @LastEditTime: 2023-05-18 21:49:33
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1023
 * 
 * 问题描述：老者告诉他，每次操作可以删除一个数 arr_i，同时删除所有等于arr_i+1 或者arr_i−1的数。
 * 操作后被删除的数无法再被选中。每次操作可以获得被删除数的分数，现在他想知道通过多次操作，他最多能够获得多少分。
 * 
 * 输入描述：第一行一个正整数 n ,表示数组 arr 的长度。
 * 接下来行包含 n 个正整数,分别为数组 arr 中的 n 个数
 * n≤100000,1≤arr_i≤100000
 * 
 * 输出描述：输出可以获得的最高分。
 * 
 * 
 * 打家劫舍问题
 * 
 */
public class Problem21 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        int[] cnt = new int[100001];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            cnt[arr[i]]++;
        }

        // dp[i][0]表示的是不偷第i个位置
        int[][] dp = new int[100001][2];
        for (int i = 1; i <= 100000; i++) {
            dp[i][0] = Math.max(dp[i - 1][1], dp[i - 1][0]);
            dp[i][1] = i * cnt[i] + dp[i - 1][0];
        }
        in.close();
    }
}
