/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-02 17:27:06
 * @LastEditTime: 2023-02-07 11:17:48
 */
package subsequence_subarray.minimum_ascii;

/**
 * leetcode 712 middle 两个字符串的最小ASCII删除和
 * 
 * 给定两个字符串s1 和 s2，返回 使两个字符串相等所需删除字符的 ASCII 值的最小和 。
 */
public class MinimumDeleteSum {
    public int minimumDeleteSum(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        int[][] dp = new int[m + 1][n + 1];
        
        for (int i = 1; i <= n; i++) {
            // dp[0][i] += s2.charAt(i - 1);
            // 太傻了，dp[0][i] += s2.charAt(i - 1); ---》dp[0][i] = dp[0][i] + s2.charAt(i - 1);
            dp[0][i] = dp[0][i - 1] + s2.charAt(i - 1);
        }

        for (int i = 1; i <= m; i++) {
            // dp[i][0] += s1.charAt(i - 1);
            dp[i][0] = dp[i - 1][0] + s1.charAt(i - 1);
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j] + s1.charAt(i - 1), dp[i][j - 1] + s2.charAt(j - 1));
                }
            }
        }

        return dp[m][n];
    }
}
