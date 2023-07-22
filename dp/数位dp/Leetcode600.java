package dp.数位dp;

/**
 * leetcode 600 hard 不含连续1的非负整数
 * 给定一个正整数 n ，请你统计在 [0, n] 范围的非负整数中，有多少个整数的二进制表示中不存在 连续的 1 。
 */
public class Leetcode600 {
    char[] chs;
    int[][] memo;
    public int findIntegers(int n) {
        chs = Integer.toBinaryString(n).toCharArray();  // 这里是转成二进制字符串
        memo = new int[chs.length][2];
        for (int i = 0; i < chs.length; i++) {
            memo[i][0] = -1;
            memo[i][1] = -1;
        }

        return dp(0, false, true);
    }

    // 因为这个是在二进制字符串中操作，所以不需要isNum，也就是说没有前导0的影响
    // pre指的是当前位置前一个位置是否为1
    private int dp(int i, boolean pre, boolean limit) {
        if (i == chs.length) {
            return 1;
        }

        int idx = pre ? 1 : 0;
        if (!limit && memo[i][idx] != -1) {
            return memo[i][idx];
        }

        int ans = 0;
        int up = limit ? chs[i] - '0' : 1; // 只有0和1两种选择
        for (int d = 0; d <= up; d++) {
            if (pre && d == 1) {
                continue;
            }
            ans += dp(i + 1, d == 1, limit && d == up);
        }

        if (!limit) {
            memo[i][idx] = ans;
        }

        return ans;
    }
}
