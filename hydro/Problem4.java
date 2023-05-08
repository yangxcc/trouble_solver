package hydro;

import java.util.Scanner;

/**
 * 题目来源：http://101.43.147.120/p/P1004
 * 题目描述：给定一个大小为n的数组，请问存在多少种方案的子序列使得该子序列的和是原数组元素总和的一半。
 * 输入描述：
 * 4
 * 1 2 3 4
 * 输出描述：2
 * 
 * 数据范围 1≤n≤200 数组元素大于0且其总和不超过 1e5 , 保证方案总数不超过int的最大值。
 * 
 * 
 * 此外，针对这个题目，又发现我得一个问题：读题不充分，看到会的赶紧就回溯去写了，根本没有考虑到，如果和是奇数的话，
 * 根本就不能分成两个部分
 */
public class Problem4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int sum = 0;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            sum += val;
            arr[i] = val;
        }

        if (sum % 2 != 0) {
            System.out.println(0);
            return;
        }

        int target = sum / 2;
        int[][] dp = new int[n + 1][target + 1];
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (arr[i - 1] > j) {
                    // 只能是不选这个数字了
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - arr[i - 1]];
                }
            }
        }

        System.out.println(dp[n][target]);
    }

    /**
     * 最开始使用的是回溯，但我发现，在笔试题中，除非是没有办法了，在一般情况下还是不要用回溯了，基本上都会超时
     * 
     * 通过这里的回溯，我还需要再去看看回溯—组合那里是怎么写的
     */
    static int ans = 0;

    private static void backtrack(int[] arr, int idx, int target) {
        if (target == 0) {
            ans++;
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = idx; i < arr.length; i++) {
            // 貌似去不去重无所谓
            if (i > idx && arr[i] == arr[i - 1]) {
                continue;
            }

            target -= arr[i];

            backtrack(arr, i + 1, target);

            target += arr[i];
        }
    }
}