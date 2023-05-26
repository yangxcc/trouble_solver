package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1045
 * 
 * 问题描述：塔子哥登山时每一步可以选择向上爬一个台阶或者多个台阶，如果登山时选择的台阶不同，则为一种爬山方案。
 * 塔子哥想知道，华光林的每座山各有多少种不同的爬山方案(输出结果对 1e9+7 取模)。
 * 
 * 输入描述：第一行，三个整数 N 、 P 和 K 分别代表山的个数 N ，塔子哥一次最高能爬的高度 P 以及塔子哥一次最多能跨越的台阶数 K 。
 * (1≤N≤10，1≤P≤1,000，1≤K≤1,000 )，接下来 N 行，每行的第一个整数M_i表示第 i 座山一共有 M_i 个台阶，接下来有 M_i 个整数，
 * 分别表示每个台阶的高度 H_j，( 1 ≤ M_i ≤ 10,000,1≤ H_j ≤1,000)。
 * 
 * 输出描述：输出 N 行，每行一个整数，表示第 i 座山塔子哥可以选择的登山方案数目。
 * 
 * 爬楼梯 leetcode 70，这道题不应该不会做的
 */
public class Problem39 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int p = in.nextInt();
        int k = in.nextInt();

        in.nextLine();
        while (n > 0) {
            String[] nums = in.nextLine().split(" ");
            int[] arr = new int[Integer.parseInt(nums[0])];
            int maxVal = 0;
            for (int i = 1; i < nums.length; i++) {
                arr[i - 1] = Integer.parseInt(nums[i - 1]);
                maxVal = Math.max(maxVal, arr[i - 1]);
            }

            if (maxVal > p) {
                System.out.println(0);
            } else {
                System.out.println(process(arr, p, k));
            }

            n--;
        }
        in.close();
    }

    private static int process(int[] arr, int p, int k) {
        int mod = 1000000007;
        int n = arr.length;
        // dp[i]表示登上第i个位置有多少种方案
        int[] dp = new int[n + 1];
        dp[0] = 1;

        for (int i = 1; i <= n; i++) {
            int sum = 0;
            for (int j = i + 1; j <= n && j <= i + k; j++) {
                sum += arr[j - 1];
                if (sum > p) {
                    break;
                }
                dp[j] = (dp[j] + dp[i]) % mod;
            }
        }

        return dp[n];
    }
}
