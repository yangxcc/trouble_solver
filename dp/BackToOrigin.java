package dp;

/**
 * 圆环上有10个点，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法。
 */
public class BackToOrigin {
    // 0-9十个点，走n步，能够回到原点的走法
    public int process(int k) {
        // dp[i][j]表示的是走i步，回到j点总共有多少种走法
        int[][] dp = new int[k + 1][10];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < 10; j++) {
                dp[i][j] = dp[i - 1][(j - 1 + 10) % 10] + dp[i - 1][(j + 1) % 10];
            }
        }
        return dp[k][0];
    }

    // 走k步，共有n个点
    public int process(int k, int n) {
        if (n == 0) {
            return 1;
        }
        if (n == 2) {
            if (k % 2  == 0) {
                return k;
            } else {
                return 0;
            }
        }

        int[][] dp = new int[k + 1][n];
        dp[0][0] = 1;
        for (int i = 1; i <= k; i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = dp[i - 1][(j - 1 + n) % n] + dp[i - 1][(j + 1) % n];
            }
        }
        return dp[k][0];
    }
}
