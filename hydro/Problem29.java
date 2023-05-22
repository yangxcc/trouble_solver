/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-22 14:21:39
 * @LastEditTime: 2023-05-22 15:23:31
 */
package hydro;

import java.util.Scanner;

/**
 * 
 * 问题来源：https://codefun2000.com/p/P1031
 * 
 * 问题描述：塔子哥的公司准备在年会上开展抽奖活动。他们购买了若干个奖品，每个奖品都有一个价格，用一个正整数数组表示。
 * 在抽奖环节，公司准备设置一等奖、二等奖和三等奖，每个等级设置一个奖品，并将所有奖品分成三份大礼包。
 * 公司希望尽可能地减小一等奖和三等奖之间的价格差异，同时满足一等奖总价格大于二等奖总价格，二等奖总价格大于三等奖总价格。
 * 为了实现这一目标，公司需要找到一种合适的分配方案。具体来说，假设一等奖总价格为 x，二等奖总价格为 y，三等奖总价格为 z，
 * 则 x>y>z>0。同时，假设奖品的总数量为 n，用正整数数组 array 表示每个奖品的价格。
 * 现在的问题是塔子哥他们不知道如何分配奖品，才能使得一等奖和三等奖之间的价格差最小，你能帮帮他们吗？
 * 
 * 输入描述：第一行：正整数 n ，表示奖品的数量，取值范围 [3,16)
 * 第二行：一个正整数数组 array ，每个元素表示奖品的价格，取值范围 [1,1000]
 * 
 * 输出描述：一个非负整数，表示一等奖和三等奖的差值，没有方案返回 0
 * 
 */
public class Problem29 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        dfs(0, arr, 0, 0, 0);
        if (ans == Integer.MAX_VALUE) {
            ans = 0;
        }

        System.out.println(ans);
        in.close();
    }

    static int ans = Integer.MAX_VALUE;
    private static void dfs(int idx, int[] arr, int sum1, int sum2, int sum3) {
        if (idx == arr.length) {
            if (sum1 > sum2 && sum2 > sum3 && sum3 > 0) {
                ans = Math.min(ans, sum1 - sum3);
            }

            return;
        }

        dfs(idx + 1, arr, sum1 + arr[idx], sum2, sum3);
        dfs(idx + 1, arr, sum1, sum2 + arr[idx], sum3);
        dfs(idx + 1, arr, sum1, sum2, sum3 + arr[idx]);
    }
}
