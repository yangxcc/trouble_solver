package dp.数位dp;

import java.util.Arrays;

/**
 * leetcode 902 hard 最大为N的数字组合
 * 给定一个按 非递减顺序 排列的数字数组 digits 。你可以用任意次数 digits[i] 来写的数字。
 * 例如，如果 digits = ['1','3','5']，我们可以写数字，如 '13', '551', 和 '1351315'。
 *
 * 返回 可以生成的小于或等于给定整数 n 的正整数的个数 。
 */
public class Leetcode902 {
    char[] chs;
    String[] digits;
    int[] memo;
    public int atMostNGivenDigitSet(String[] digits, int n) {
        this.digits = digits;
        this.chs = Integer.toString(n).toCharArray();
        this.memo = new int[chs.length];
        Arrays.fill(memo, -1);

        return dp(0, true);
    }

    private int dp(int i, boolean limit) {
        if (i == chs.length) {
            return 1;
        }

        if (!limit && memo[i] != -1) {
            return memo[i];
        }

        int ans = 0;
        int up = limit ? chs[i] - '0' : 9;
        for (String digit : digits) {
            int d = Integer.parseInt(digit);
            if (d < 0 || d > up) {
                continue;
            }
            ans += dp(i + 1, limit && d == up);
        }

        if (!limit) {
            memo[i] = ans;
        }

        return ans;
    }
}
