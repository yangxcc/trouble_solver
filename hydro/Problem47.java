/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-10 13:49:43
 * @LastEditTime: 2023-06-10 14:47:18
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1090
 * 
 * 问题描述：塔子哥是一位购物狂人，他经常光顾提瓦特商店。最近，提瓦特商店推出了一项促销活动，有N件商品打折销售。
 * 每个商品都有原价和折扣价，而且不同的商品的折扣力度也不同。塔子哥听说了这个促销活动后非常兴奋，
 * 他计划购买尽可能多的商品，同时也希望尽量少地花钱。他掏出了自己的钱包，发现他手头有X元的现金和Y张折扣券。
 * 于是塔子哥找到了你，希望你能帮助他计算出在这种情况下他可以购买的最多商品数量以及花费的最少钱数。
 * 
 * 输入描述：第一行三个整数，以空格分开，分别表示N,X,Y。接下来N行，每行两个整数，以空格分开，表示一个的原价和折扣价。
 * 1≤N≤100，1≤X≤5000，1≤Y≤50，每个商品原价和折扣价均介于[1,50]之间。
 * 
 * 输出描述：一行，两个整数，以空格分开。第一个数字表示最多买几个商品，第二个数字表示在满足商品尽量多的前提下所花费的最少的钱数。
 */
public class Problem47 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), x = in.nextInt(), y = in.nextInt();
        int[][] prices = new int[n][2];
        in.nextLine();
        for (int i = 0; i < n; i++) {
            prices[i][0] = in.nextInt();
            prices[i][1] = in.nextInt();
        }

        // dp[i][j][k] 选购前i件商品，在有j元钱，和k张优惠券的前提下能够达到的最多商品数
        int[][][] dp = new int[n + 1][x + 1][y + 1];
        int ans = 0;
        int cost = 0;
        for (int i = 1; i <= n; i++) {
            for (int k = 0; k <= y; k++) {
                for (int j = 1; j <= x; j++) {
                    // 不用优惠券
                    if (j >= prices[i - 1][0]) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - prices[i - 1][0]][k] + 1);
                    }

                    // 使用优惠券
                    if (j >= prices[i - 1][1] && k > 0) {
                        dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j - prices[i - 1][1]][k - 1] + 1);
                    }

                    // 不选择这个物品
                    dp[i][j][k] = Math.max(dp[i][j][k], dp[i - 1][j][k]);

                    // 有可能件数一样，但是钱数不一样
                    if (ans < dp[i][j][k]) {
                        ans = dp[i][j][k];
                        cost = j;
                    } else if (ans == dp[i][j][k]) {
                        cost = Math.min(cost, j);
                    }
                }
            }
        }

        System.out.printf("%d %d", ans, cost);
        in.close();
    }
}
