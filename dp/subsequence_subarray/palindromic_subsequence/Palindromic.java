/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-10 12:31:16
 * @LastEditTime: 2023-02-22 20:12:51
 */
package dp.subsequence_subarray.palindromic_subsequence;

/**
 * leetcode 647 middle 回文子串
 * 
 * 给你一个字符串 s ，请你统计并返回这个字符串中 回文子串 的数目。
 * 回文字符串 是正着读和倒过来读一样的字符串。
 * 子字符串 是字符串中的由连续字符组成的一个序列。
 * 具有不同开始位置或结束位置的子串，即使是由相同的字符组成，也会被视作不同的子串。
 */
public class Palindromic {
    public int countSubstrings(String s) {
        int m = s.length();
        // dp[i][j]表示的是s在[j,i]这个区间内是否为回文串
        boolean[][] dp = new boolean[m][m];
        for (int i = 0; i < m; i++) {
            dp[i][i] = true;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 0; j < i; j++) {
                if (s.charAt(i) != s.charAt(j)) {
                    dp[i][j] = false;
                } else {
                    if (i - j < 3) {
                        // 小于等于3个元素的时候
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i - 1][j + 1];
                    }
                }
            }
        }

        int ans = 0;
        for (int i = 0; i < m; i++) {
            for (boolean x : dp[i]) {
                if (x) {
                    ans++;
                }
            }
        }

        return ans;
    }
}
