package dp.数位dp;

import java.util.Arrays;

/**
 * leetcode 233 hard 数字1的个数
 * 给你一个整数n，计算所有小于等于n的非负整数中数字1的出现个数
 */
public class Leetcode233 {
    char[] chs;
    int[][] memo;
    public int countDigitOne(int n) {
        chs = Integer.toString(n).toCharArray();
        memo = new int[chs.length][chs.length];
        for (int i = 0; i < chs.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        return dp(0, 0, true, false);
    }

    // 从i开始填写数字，能够构造出的包含数字1的数字的数量
    // 已经出现了cnt个1了，前面填了多少个1了
    private int dp(int i, int cnt, boolean limit, boolean isNum) {
        if (i == chs.length) {
            return cnt;
        }

        if (!limit && isNum && memo[i][cnt] != -1) {
            return memo[i][cnt];
        }

        int ans = 0;
        if (!isNum) { // 跳过
            ans = dp(i + 1, cnt,false, false);
        }

        int up = limit ? chs[i] - '0' : 9;
        int down = isNum ? 0: 1;
        for (int d = down; d <= up; d++) {
            ans += dp(i + 1, cnt + (d == 1 ? 1 : 0), limit && d == up, true);
        }

        if (!limit && isNum) {
            memo[i][cnt] = ans;
        }

        return ans;
    }
}
