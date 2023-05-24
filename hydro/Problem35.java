/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-24 21:58:56
 * @LastEditTime: 2023-05-24 22:02:26
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1037
 * 
 * 问题描述：塔子哥和兔子哥两兄弟被分配到不同的赛道，分别为赛道 X 和赛道 Y，X 和 Y 赛道均设有多个回合，
 * 但回合数不一定一致，领导预先为每个回合设置了奖金，如果通过则获得该奖金，否则不获得，但是允许选手继续通往下个关卡
 * 回合间是独立的，即他们之间是否通过无任何关系，X和Y赛道完全独立，两兄弟闯关也完全独立
 * 闯关结束后，分别对两兄弟手中的奖金按照其获得的顺序进行一一匹配，如果两兄弟奖金完全一样且奖金是连续关卡获得的，
 * 则获得奖金都可带回家，否则无法获得两兄弟俩想问问，他们加起来最多可获得多少份奖金?
 * 
 * 输入描述：为了方便对比，官方对奖金进行了编号。第一行为字符串，每个字符代表赛道 X 下每个关卡提供的礼物编号
 * 第二行为字符串，每个字符代表赛道 Y 下每个关卡提供的礼物编号，礼物的编号范围为字母 a ~ z 和数字0 ~ 9
 * 赛道关卡数为 1 ~ 100 范围内的整数
 * 
 * 输出描述：获得的奖金数
 * 
 * 
 * 题目的意思其实就是让我们求 连续的最长公共子序列
 * 
 */
public class Problem35 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s1 = in.nextLine();
        String s2 = in.nextLine();
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        int ans = 0;
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                }
                ans = Math.max(ans, dp[i][j]);
            }
        }

        System.out.println(ans * 2);
        in.close();
    }
}
