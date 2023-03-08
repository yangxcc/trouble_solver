package edit_distance;

import java.util.Scanner;

/**
 * leetcode 72 hard 编辑距离
 * 
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 *  插入一个字符
 *  删除一个字符
 *  替换一个字符
 */
public class EditDistance{
    public int minDistance(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        memo = new int[m][n];

        return dp(word1, word2, 0, 0);
    }

    int[][] memo;
    // dp函数的含义是求出word1[i...]和word2[j...]的编辑距离
    public int dp(String word1, String word2, int i, int j) {
        if (j >= word2.length()) {
            // 相当于word2是一个空字符串
            return word1.length() - i;
        }
        if (i >= word1.length()) {
            return word2.length() - j;
        }

        if (memo[i][j] != 0) {
            return memo[i][j];
        }

        if (word1.charAt(i) == word2.charAt(j)) {
            memo[i][j] = dp(word1, word2, i + 1, j + 1);
            return memo[i][j];
        } else {
            // 删除 dp[i+1, j]
            // 替换 dp[i+1, j+1]
            // 添加 dp[i, j+1]
            memo[i][j] = min(dp(word1, word2, i + 1, j), dp(word1, word2, i + 1, j + 1), dp(word1, word2, i, j + 1)) + 1;
            return memo[i][j];
        }
    }

    private int min(int a, int b, int c) {
        return Math.min(a, Math.min(b, c));
    }

    public int minDistanceDpArray(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // dp[i][j]表示的是word1[0..i]和word2[0..j]的最小编辑距离
        int[][] dp = new int[m + 1][n + 1];
        // 第0行，dp[0][i]，word1为空
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }
        // 第0列，dp[i][0],word2为空
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 程序是从后往前走的，可以看最开始的输入
                    dp[i][j] = min(dp[i - 1][j], dp[i][j - 1], dp[i - 1][j - 1]) + 1;
                }
            }
        }

        return dp[m][n];
    }
}

/**
 * leetcode 72 hard 编辑距离
 * 
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 *  插入一个字符
 *  删除一个字符
 *  替换一个字符
 */
// class Main
class day0306 {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            String word1 = in.nextLine();
            String word2 = in.nextLine();

            int num = solve(word1, word2);
            System.out.println(num);
        }
    }
    public static int solve(String word1, String word2) {
        int m = word1.length(), n = word2.length();
        // dp[i][j]表示的是word1[...i]和word2[..j]的编辑距离
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            dp[0][i] = i;
        }

        for (int j = 0; j <= m; j++) {
            dp[j][0] = j;
        }

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1]));
                }
            }
        }

        return dp[m][n];
    }
} 