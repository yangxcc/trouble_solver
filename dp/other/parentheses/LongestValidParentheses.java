/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-03-01 09:49:45
 * @LastEditTime: 2023-03-01 10:04:40
 */
package other.parentheses;

/**
 * leetcode 32 hard 最长有效括号
 * 
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 */
public class LongestValidParentheses {
    public int longestValidParentheses(String s) {
        int n = s.length();
        int[] dp = new int[n];
        dp[0] = 0;
        int ans = 0;

        for (int i = 1; i < n; i++) {
            if (s.charAt(i) == ')') {
                int pre = i - dp[i - 1] - 1;
                if (pre >= 0 && s.charAt(pre) == '(') {
                    dp[i] = dp[i - 1] + 2;
                    if (pre > 0) {
                        dp[i] += dp[pre - 1];
                    }
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }
}