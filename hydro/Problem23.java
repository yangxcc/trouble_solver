/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-19 21:20:46
 * @LastEditTime: 2023-05-19 21:34:01
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源；https://codefun2000.com/p/P1025
 * 问题描述：现在塔子哥有火药枪若干， 以及数量有限的火药，每种火药枪的威力不尽相同，且在每次开火之前都需要一定时间填充火药， 
 * 请你帮助塔子哥在给定的时间结束之前或者火药存量耗尽之前给予敌人最大的伤害。
 * 
 * 限制:
 * 火药枪每次开火的威力一样；
 * 火药剩余量不小于火药枪的消耗量，该火药枪才能开火；
 * 填充火药之外的时间忽略不计；
 * 不同种火药枪可以同时开火。
 * 
 * 输入描述：第一行，整数 N，M，T，N 表示火药枪种类个数， M 表示火药数量， T 表示攻城时间，1≤N,M,T≤1000
 * 接下来 N 行，每一行三个整数 A,B,C。分别表示火药枪的威力，火药枪每次攻击消耗的火药量，火药枪每次攻击填充火药的时间，0≤A,B,C≤100000。
 * 
 * 输出描述：输出在给定的时间结束之前或者火药存量耗尽之前给予敌人最大的伤害。
 * 
 * 这是一个经典的多重背包问题，我们可以通过攻城时间得到每种火枪最多的选择个数
 * 也就是说，问题就变成了N个物品，最多能选X个，怎么在容量为M的前提下，达到最大的价值
 */
public class Problem23 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int N = in.nextInt(), M = in.nextInt(), T = in.nextInt();
        int[] power = new int[N];
        int[] cost = new int[N];
        int[] time = new int[N];
        for (int i = 0; i < N; i++) {
            power[i] = in.nextInt();
            cost[i] = in.nextInt();
            time[i] = in.nextInt();
        }

        // dp[i][j]表示的是前i件物品，在容量为j的情况下，能够到达的最大价值
        int[][] dp = new int[N + 1][M + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 0; k <= T / time[i - 1]; k++) {
                    if (j - k * cost[i - 1] >= 0) {
                        dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - k * cost[i - 1]] + k * power[i - 1]);
                    } 
                }
            }
        }

        System.out.println(dp[N][M]);
        in.close();
    }
}
