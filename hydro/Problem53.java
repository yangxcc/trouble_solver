/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-11 15:05:52
 * @LastEditTime: 2023-06-11 15:33:51
 */
package hydro;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 问题来源：
 * 
 * 问题描述：塔子哥开的玩具店生意越来越好，每天都有很多客人前来选购玩具。有一天，他接到了一个大单，客户想购买 n 个玩具，
 * 并且要求打包成多个玩具袋。塔子哥精心为客户挑选了 n 个玩具，并且将它们编号为 1,2,…,n。
 * 然而，塔子哥发现这个订购单还有一个要求：每个玩具袋最多只能装 m 个玩具，并且同一个玩具袋里装的玩具编号必须是连续的。
 * 玩具袋的成本与容积成线性关系。
 * 为了解决这个问题，他决定采用样本中点估计的方法来计算玩具袋的容积。具体来说，
 * 如果一个玩具袋中装的最大的玩具容积是 u，最小的是 v，那么这个玩具袋的成本就是k×floor((u+v)/2)+s，
 * 其中k是玩具袋中装入玩具的个数s是一个常数， floor(x) 是下取整函数，比如 floor(3.8)=3 ，floor(2)=2
 * 客户并没有规定玩具袋的数量，但是希望玩具袋的成本越小越好，毕竟买玩具就很贵了。请求出塔子哥打包这 n 个玩具所用的最小花费。
 * 
 * 输入描述：第一行三个正整数 n,m,s 。意义如题面所示
 * 第二行 n 个正整数a1,a2,...,an，表示每个玩具的体积。
 * 对于全部数据, 1≤n≤10^4, 1≤m≤10^3，m≤n，1≤ai,s≤10^4。
 * 
 * 输出描述：输出一个整数，表示打包这 n 个玩具玩具袋的最小成本。
 */
public class Problem53 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt(), s = in.nextInt();
        in.nextLine();
        int[] cost = Arrays.stream(in.nextLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // dp[i]表示的是装好i个玩具的最小成本
        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 自己开始一个袋子
            dp[i] = dp[i - 1] + cost[i - 1] + s;
            int maxVal = cost[i - 1], minVal = cost[i - 1];
            for (int j = i; j >= Math.max(i - m + 1, 1); j--) {
                // 第i个玩具可以和前面的玩具放到同一个袋子中，也可以自己开始一个袋子
                maxVal = Math.max(maxVal, cost[j - 1]);
                minVal = Math.min(minVal, cost[j - 1]);

                dp[i] = Math.min(dp[i], dp[j - 1] + (i - j + 1) * ((maxVal + minVal) / 2) + s);
            }
        }
        System.out.println(dp[n]);
        in.close();
    }
}
