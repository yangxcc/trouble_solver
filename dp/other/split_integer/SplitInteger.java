/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-30 22:03:51
 * @LastEditTime: 2023-03-09 14:40:34
 */
package other.split_integer;

/**
 * leetcode 343 middle 整数拆分
 * 
 * 给定一个正整数 n ，将其拆分为 k 个 正整数 的和（ k >= 2 ），并使这些整数的乘积最大化。
 * 返回 你可以获得的最大乘积 。
 * 
 * 输入: n = 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36。
 * 
 */
public class SplitInteger {
    public int integerBreak(int n) {
        int[] dp = new int[n + 1];
        // dp[1] = 1; 
        dp[2] = 1;

        for (int i = 3; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                // j * (i - j)表示的是拆分成两部分，j * dp[i - j]表示的是拆分成多部分
                dp[i] = Math.max(dp[i], Math.max(j * (i - j), j * dp[i - j]));
            }
        }

        return dp[n];
    }
}
