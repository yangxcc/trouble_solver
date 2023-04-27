package daily;

import java.util.Arrays;

/**
 * leetcode 1048 middle 最长字符串链
 * 
 * 终于做出来了，但不是一次AC KEEP MOVING
 * 
 * 给出一个单词数组 words ，其中每个单词都由小写英文字母组成。
 * 
 * 如果我们可以 不改变其他字符的顺序 ，在 wordA 的任何地方添加 恰好一个 字母使其变成 wordB ，那么我们认为 wordA 是 wordB 的 前身 。
 * 
 * 例如，"abc" 是 "abac" 的 前身 ，而 "cba" 不是 "bcad" 的 前身
 * 词链是单词 [word_1, word_2, ..., word_k] 组成的序列，k >= 1，其中 word1 是 word2 的前身，word2 是 word3 的前身，依此类推。一个单词通常是 k == 1 的 单词链 。
 * 
 * 从给定单词列表 words 中选择单词组成词链，返回 词链的 最长可能长度 。
 */
public class LongestStringChain {
    public int longestStrChain(String[] words) {
        // 按照字符串长度排序
        Arrays.sort(words, (w1, w2) -> {
            return Integer.compare(w1.length(), w2.length());
        });
        int ans = 1, n = words.length;
        // dp[i]表示的是以i结尾的字符串，组成最长可能长度的词链
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (isValid(words[j], words[i])) {
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }   

    /**
     * s1是不是s2的前身
     * @param s1
     * @param s2
     * @return
     */
    private boolean isValid(String s1, String s2) {
        int m = s1.length(), n = s2.length();
        // 这里最开始写错了，写成了
        // if (m >= n) {
        //     return false;
        // }
        // 但实际上，a也不是abc的前身
        if (n - m != 1) {
            return false;
        }

        int[][] dp = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[m][n] == m;
    }
}
