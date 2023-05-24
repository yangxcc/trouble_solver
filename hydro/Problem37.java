package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1039
 * 
 * 问题描述：有个神奇楼梯，每次可以传送不超过 k 个楼梯，而每次爬楼花费的体力为 max(0,目标楼梯高度−当前楼梯高度) .
 * 现在塔子哥想知道求从第一个楼梯到最后一个楼梯的最小花费是多少。
 * 
 * 输入描述：第一行输入n,k，代表楼梯的阶数和最多能传送跨过多少个楼梯。
 * 第二行n个数，代表每座楼梯的高度
 * 
 * 输出描述：最小花费
 * 
 */
public class Problem37 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int[] arr = new int[n];
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            dp[i] = Integer.MAX_VALUE;
        }
        dp[0] = 0; // 一定不要忘了最开始在原点花费的体力是0

        for (int i = 1; i < n; i++) {
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    dp[i] = Math.min(dp[i], dp[j] + Math.max(0, arr[i] - arr[j]));
                }
            }
        }

        System.out.println(dp[n - 1]);

        in.close();
    }
}
