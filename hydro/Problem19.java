/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-17 21:12:42
 * @LastEditTime: 2023-05-17 21:17:41
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1020
 * 问题描述：塔子哥买了一个长为 a ,宽为 b的大饼,一共有 n 个人来一起吃这张大饼，要求每个人必须获得相同面积的大饼。
 * 现在塔子哥被要求每一切只能平行于一块大饼的一边(任意一边)，并且必须把这块大饼切成两块。那么我们要切成 n 块饼的话，
 * 塔子哥必须切 n−1 次。
 * 塔子哥患有重度强迫症，他一定要求 n 块大饼的长边与短边的比值的最大值最小。你能帮塔子哥计算出这个比值吗
 * 
 * 输入描述：一行三个整数 a,b,n分别表示大饼的长，宽以及人数
 * 输出描述：一个浮点数，保留 6 位小数，表示切分后大饼的长边与短边的比值
 */
public class Problem19 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        double a = in.nextDouble();
        double b = in.nextDouble();
        int n = in.nextInt();

        System.out.printf("%.6f", dfs(a, b, n));
        in.close();;
    }

    /**
     * 将长、宽为x的大饼分成k份，长和短的最大比值
     * 这里需要注意的是，长为x的矩形分成k份，这k个举行的最小值是x / k，同理宽
     * 因此，每次划分出的小块的长肯定是x / k的整数倍
     * @param x
     * @param y
     * @param k
     * @return
     */
    private static double dfs(double x, double y, int k) {
        double dx = Math.max(x, y);
        double dy = Math.min(x, y);
        
        if (k == 1) {
            return dx / dy;
        }

        double minX = dx / k;
        double minY = dy / k;
        double ans = 1e9;
        for (int i = 1; i <= k / 2; i++) {
            // 按照行来划分
            ans = Math.min(ans, Math.max(dfs(minX * i, dy, i), dfs(x - minX * i, dy, k - i)));

            ans = Math.min(ans, Math.max(dfs(dx, minY * i, i), dfs(dx, dy - minY * i, k - i)));
        }

        return ans;
    }
}
