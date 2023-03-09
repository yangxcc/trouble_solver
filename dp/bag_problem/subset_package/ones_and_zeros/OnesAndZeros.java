/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-06 20:43:38
 * @LastEditTime: 2023-03-09 14:07:55
 */
package bag_problem.subset_package.ones_and_zeros;

/**
 * leetcode 474 middle 一和零
 * 
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 */
public class OnesAndZeros {
    public int findMaxForm(String[] strs, int m, int n) {
        // dp[k][i][j] 表示的是前k个元素 最多i个0 j个1的最长子集
        int[][] dp = new int[m + 1][n + 1];
        dp[0][0] = 0;

        for (String str : strs) {
            int[] nums = count(str);
            // 要不要把这个str加进去
            for (int i = m; i >= 0; i--) {
                for (int j = n; j >= 0; j--) {
                    if (i - nums[0] < 0 || j - nums[1] < 0) {
                        continue;
                    }
                    dp[i][j] = Math.max(dp[i - nums[0]][j - nums[1]] + 1, dp[i][j]);
                }
            }
        }

        return dp[m][n];
    }

    // 统计str中1和0的个数
    private int[] count(String str) {
        int countOf0 = 0;
        int countOf1 = 0;
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '0') {
                countOf0++;
            } else {
                countOf1++;
            }
        }

        return new int[]{countOf0, countOf1};
    }
}
/**
 * leetcode 474 middle 一和零
 * 
 * 给你一个二进制字符串数组 strs 和两个整数 m 和 n 。
 * 请你找出并返回 strs 的最大子集的长度，该子集中 最多 有 m 个 0 和 n 个 1 。
 * 如果 x 的所有元素也是 y 的元素，集合 x 是集合 y 的 子集 。
 */
class Main {
    public int process(String[] strs, int m, int n) {
        int k = strs.length;
        // dp[k][i][j] 前k个字符，最多有i个0，j个1的最大子集长度
        int[][][] dp = new int[k + 1][m + 1][n + 1];
        // dp[0][][] = 0
        for (int i = 1; i <= k; i++) {
            int[] count = count(strs[i - 1]);
            for (int p = 1; p <= m; p++) {
                if (p - count[0] < 0) {
                    continue;
                }
                for (int q = 1; q <= n; q++) {
                    if (q - count[1] < 0) {
                        continue;
                    }
                    dp[i][p][q] = Math.max(dp[i - 1][p - count[0]][q - count[1]] + 1, dp[i - 1][p][q]);
                }
            }
        }

        return dp[k][m][n];
    }

    private int[] count(String str) {
        int countOf0 = 0;
        int countOf1 = 0;

        for (char ch : str.toCharArray()) {
            if (ch == '0') {
                countOf0++;
            } else {
                countOf1++;
            }
        }

         return new int[]{countOf0, countOf1};
    }
}