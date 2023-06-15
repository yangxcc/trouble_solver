package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1247
 * 
 * 问题描述：游戏中有 n 种作物，每种作物都有自己的特点，比如生长周期、种子成本、作物收益等。塔子哥需要根据这些信息，
 * 合理地安排自己的种植计划，以达到最大化利润的目标。塔子哥只有一块土地，也就是说每个时间只能由一个作物在生长。
 * 他需要在游戏开始时购买种子，然后种植在土地上。种子的买入价格为 ai，作物卖出价格 bi。一个种子只会产出一个作物，
 * 种子可以重复购买。第 i 种作物从种植到作物成熟采摘需要ti天时间，种植和采摘、卖出等操作不耗时间，成熟之前作物没有价值。
 * 如果塔子哥想要更换作物，他需要先把当前作物采摘卖出，然后再购买新的种子。
 * 游戏内总时长为 m 天，也就是说塔子哥只有 m 天的时间来经营自己的农场。
 * 塔子哥初始的钱足够多，不用担心资金不足。塔子哥想知道，在这样的条件下，他最多能赚多少钱。
 * 
 * 输入描述：第一行两个整数 n ， m(1≤n,m≤1000) 表示作物种类数和游戏时长；
 * 第二行 n 个正整数，表示每种作物的成熟时长 ti(1≤ti≤m)；
 * 第三行 n 个正整数，表示每种作物的种子价格 ​ai(1≤ai≤100000)；
 * 第四行 n 个正整数，表示每种作物的卖出价格 bi(ai≤bi≤100000) 。
 * 
 * 输出描述：输出一个整数来表示塔子哥最多能赚的钱。
 */
public class Problem58 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] time = new int[n];
        int[] price = new int[n];
        int[] sell = new int[n];
        for (int i = 0; i < n; i++) {
            time[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            price[i] = in.nextInt();
        }

        for (int i = 0; i < n; i++) {
            sell[i] = in.nextInt();
        }

        // dp[i]表示的是第i天的最大获利
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j < n; j++) {
                if (i >= time[j]) {
                    dp[i] = Math.max(dp[i], sell[j] - price[j] + dp[i - time[j]]);
                }
            }
        }
        
        System.out.println(dp[n]);
        in.close();
    }
}
