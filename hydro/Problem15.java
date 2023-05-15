package hydro;

import java.util.Scanner;

/**
 * 问题来源：http://101.43.147.120/p/P1016
 * 
 * 问题描述：字符串中有些字符是问号 ? ，可以代替左括号 ( 或者右括号 ) 。塔子不知道该如何处理这些问号。
 * 
 * 于是他决定询问你，给定的字符串可以代表多少种不同的合法括号序列？
 * 
 * 输入描述：一个仅包含( 、) 和 ? 的字符串，长度不超过 2000
 * 
 * 输出描述：合法序列的数量。由于数量可能过大，请对 10e9+7取模
 */
public class Problem15 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int n = str.length();
        // dp[i][j]表示的是前i个位置，左括号比右括号多j个的种类数
        int[][] dp = new int[n + 1][n + 2];
        dp[0][0] = 1;
        int mod = (int) (1e9+7);
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= i; j++) {
                if ((str.charAt(i - 1) == '(' || str.charAt(i - 1) == '?') && j > 0) {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j - 1]) % mod;
                }

                if (str.charAt(i - 1) == ')' || str.charAt(i - 1) == '?') {
                    dp[i][j] = (dp[i][j] + dp[i - 1][j + 1]) % mod;
                }
            }
        }
        System.out.println(dp[n][0]);
    }
}
