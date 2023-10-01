/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-10 15:57:50
 * @LastEditTime: 2023-01-01 16:44:39
 */
package dp.subsequence_subarray.palindromic_subsequence;

/**
 * leetcode 5 middle 最长回文子串
 * https://leetcode.cn/problems/longest-palindromic-substring/description/
 */
public class LongestPalindromicSubstring {
    public String longestPalindrome(String s) {
        String ans = "";
        for (int i = 0; i < s.length(); i++) {
            String s1 = findPalindrome(s, i, i);     // 以(i,i)为中心
            String s2 = findPalindrome(s, i, i+1);   // 以(i,i+1)位为中心

            ans = s1.length() > ans.length() ? s1 : ans;
            ans = s2.length() > ans.length() ? s2 : ans;
        }
        return ans;
    }

    public String findPalindrome(String s, int left, int right) {
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
            left--;
            right++;
        }

        return s.substring(left+1, right);
    }

    private String longestPalindrome2(String s) {
        // 使用dp数组来解决这个问题
        int n = s.length();
        // dp[i][j]表示的是[i,j]
        boolean[][] dp = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }
        int maxLen = 0;
        int start = 0;
        // 一定要注意这里是从下往上遍历
        for (int i = n - 1; i >= 0; i--) {
            // 这里不是j=i+1，避免"a"这种case返回一个""
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 3) {
                        dp[i][j] = true;
                    } else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                } else {
                    dp[i][j] = false;
                }

                if (dp[i][j] && j - i + 1 > maxLen) {
                    maxLen = j - i + 1;
                    start = i;
                }
            }
        }
        return s.substring(start, start + maxLen);
    }
}
