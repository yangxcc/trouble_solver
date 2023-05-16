package hydro;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 问题描述：http://101.43.147.120/p/P1019
 * 
 * 问题描述：在坐标的x轴上有n条线段，第i条线段拥有w_i的价值。请问选出若干条互不重叠的线段的最大价值是多少？
 * 
 * 输入描述：第一行一个整数n≤2000，代表线段的条数。
 * 第二行n个整数，代表每条线段中点的横坐标。
 * 第三行n个整数，代表每条线段的半径长度（即整条线段长度的一半）。
 * 第四行n个整数，代表每条线段的价值
 * 
 * 输出描述：最大价值是？
 */
public class Problem18 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        String[] mid = in.nextLine().split(" ");
        String[] len = in.nextLine().split(" ");
        String[] values = in.nextLine().split(" ");

        int[][] xd = new int[n][3];
        for (int i = 0; i < n; i++) {
            int tmpMid = Integer.parseInt(mid[i]);
            int tmpLen = Integer.parseInt(len[i]);
            int tmpVal = Integer.parseInt(values[i]);
            xd[i] = new int[]{tmpMid - tmpLen, tmpMid + tmpLen, tmpVal};
        }

        Arrays.sort(xd, (a, b) -> {
            return a[0] - b[0];
        });

        // dp[i]表示的是以xd[i]结尾的，最大价值是多少
        int[] dp = new int[n];
        dp[0] = xd[0][2];
        int ans = dp[0];

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                // 判断xd[j]和xd[i]是否重合
                if (xd[j][1] <= xd[i][0]) {
                    // 不重合有两种选择，选j和不选j
                    dp[i] = Math.max(dp[j] + xd[i][2], dp[i]);
                } else {
                    // 重合了，只能选自己
                    dp[i] = Math.max(dp[i], xd[i][2]);
                }
            }
            ans = Math.max(dp[i], ans);
        }

        System.out.println(ans);
    }
}
