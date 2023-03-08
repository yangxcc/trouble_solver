/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-10 11:56:38
 * @LastEditTime: 2023-02-22 19:53:32
 */
package subsequence_subarray.different_subsequence;

/**
 * leetcode 115/剑指offerII-097 hard 不同的子序列
 * 
 * 给定一个字符串 s 和一个字符串 t ，计算在 s 的子序列中 t 出现的个数。
 * 字符串的一个 子序列 是指，通过删除一些（也可以不删除）字符且不干扰剩余字符相对位置所组成的新字符串
 * （例如，"ACE" 是 "ABCDE" 的一个子序列，而 "AEC" 不是）
 * 题目数据保证答案符合 32 位带符号整数范围。
 */
public class DifferentSubsequence{
    public int numDistinct(String s, String t) {
        int m = s.length(); 
        int n = t.length();

        // dp[i][j]表示的是t[0..j]在s[0..i]中出现的次数
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            dp[i][0] = 1; // 空字符串出现一次
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + dp[i - 1][j]; // 即使相等，也可能删除
                } else {
                    dp[i][j] = dp[i - 1][j];
                }
            }
        }
        return dp[m][n];
    }

    // dp(i, j)表示的是t[j..]在s[i..]中出现的次数
    public int dp(String s, String t, int i, int j) {
        if (j == t.length()) {
            return 1;
        }

        if (i == s.length()) {
            return 0;
        }

        if (s.charAt(i) == t.charAt(j)) {
            return dp(s, t, i + 1, j + 1) + dp(s, t, i + 1, j);
        } else {
            return dp(s, t, i + 1, j);
        }
    }
}