/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-17 22:11:45
 * @LastEditTime: 2023-05-17 22:15:37
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1022
 * 问题描述：塔子哥拿到了一个 n 行 n 列的矩阵，他每次操作可以将矩阵中的某个位置+ 1 。
 * 塔子哥想知道，自己最少操作多少次之后，可以使得矩阵变成合法矩阵?
 * 合法矩阵的定义:当一个矩阵顺时针旋转0度、90度、180度、 270度时， 所得到的矩阵是相同的。
 * 输入描述：第一行输入一个正整数 n ,代表矩阵的行数和列数。接下来的 n 行，每行输入 n 个正整数，用来表示矩阵的元素。
 * 1≤n≤100 , 1≤ a_i,j ≤10^9
 * 输出描述：输出一个整数表示最少操作次数。
 * 
 * 需要注意数值范围
 */
public class Problem20 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[][] arr = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                arr[i][j] = in.nextInt();
            }
        }

        long ans = 0;
        for (int i = 0; i < n / 2; i++) {
            for (int j = i; j < n - 1 - i; j++) {
                ans += maxBetweent4Num(arr[i][j], arr[j][n - 1 - i], 
                arr[n - 1 - i][n - 1 - j], arr[n - 1 - j][i]);
            }
        }

        System.out.println(ans);

        in.close();
    }

    private static long maxBetweent4Num(int a, int b, int c, int d) {
        int max = Math.max(Math.max(a, b), Math.max(c, d));
        long ans = 0;
        ans += (max - a);
        ans += (max - b);
        ans += (max - c);
        ans += (max - d);

        return ans;
    }
}
