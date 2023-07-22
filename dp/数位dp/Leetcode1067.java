package dp.数位dp;

import java.util.Arrays;

/**
 * 会员题目
 * leetcode 1067 hard 范围内的数字计数
 * 给定一个在 0 到 9 之间的整数 d，和两个正整数 low 和 high 分别作为上下界。
 * 返回 d 在 low 和 high 之间的整数中出现的次数，包括边界 low 和 high。
 *
 * return dp[high] - dp[low - 1]
 */
public class Leetcode1067 {
    static int[][] memo4Low;
    static int[][] memo4High;
    static int k;
    public static int countDigitOne(int _k, int low, int high) {
        k = _k;
        char[] chs4Low = Integer.toString(low - 1).toCharArray();
        char[] chs4High = Integer.toString(high).toCharArray();
        memo4Low = new int[chs4Low.length][chs4Low.length];
        memo4High = new int[chs4High.length][chs4High.length];
        for (int i = 0; i < chs4Low.length; i++) {
            Arrays.fill(memo4Low[i], -1);
        }
        for (int i = 0; i < chs4High.length; i++) {
            Arrays.fill(memo4High[i], -1);
        }

        return dp(0, 0, true, false, chs4High, memo4High) -
                dp(0, 0, true, false, chs4Low, memo4Low);
    }

    // 从i开始填写数字，能够构造出的包含数字1的数字的数量
    // 已经出现了cnt个1了，前面填了多少个1了
    private static int dp(int i, int cnt, boolean limit, boolean isNum, char[] chs, int[][] memo) {
        if (i == chs.length) {
            return cnt;
        }

        if (!limit && isNum && memo[i][cnt] != -1) {
            return memo[i][cnt];
        }

        int ans = 0;
        if (!isNum) { // 跳过
            ans = dp(i + 1, cnt,false, false, chs, memo);
        }

        int up = limit ? chs[i] - '0' : 9;
        int down = isNum ? 0: 1;
        for (int d = down; d <= up; d++) {
            ans += dp(i + 1, cnt + (d == k ? 1 : 0), limit && d == up, true, chs, memo);
        }

        if (!limit && isNum) {
            memo[i][cnt] = ans;
        }

        return ans;
    }

    public static void main(String[] args) {
        System.out.println(countDigitOne(3, 100, 250));
    }
}
