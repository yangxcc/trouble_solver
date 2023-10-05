package dp.edit_distance;
 
/**
 * leetcode 392 simple 判断子序列
 * 给定字符串 s 和 t ，判断 s 是否为 t 的子序列。
 * 字符串的一个子序列是原始字符串删除一些（也可以不删除）字符而不改变剩余字符相对位置形成的新字符串。
 * （例如，"ace"是"abcde"的一个子序列，而"aec"不是）。
 */
public class IsSubsequence {
    public boolean isSubsequence(String s, String t) {
        // 这个其实常见的思路是使用dp来做
        int m = s.length();
        int n = t.length();

        // dp[i][j]表示的是s[0..i]和t[0..j]的最长公共子序列的长度
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    // 相当于t中删除了一个字符
//                    dp[i][j] = dp[i][j - 1];
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n] == n;
    }

    // 可以看出，使用动态规划，时间复杂度为O(m*n)，且空间复杂度为O(m*n)

    // 对于这道题目，可以使用贪心的思路，来进行优化
    public boolean isSubsequence2(String s, String t) {
        int m = s.length(), n = t.length();
        int i = 0, j = 0;
        while (i < m && j < n) {
            // 无论是否匹配成功，j指针都会往前走
            if (s.charAt(i) == t.charAt(j)) {
                i++;
            }
            j++;
        }

        return i == m; // 判断i是否走到了头
    }
}
