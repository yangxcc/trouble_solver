package hydro;
/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-10 21:20:10
 * @LastEditTime: 2023-06-10 22:29:23
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;

/**
  * 问题来源：https://codefun2000.com/p/P1140
  *
  * 问题描述：魔法训练室里有n个神奇的杯子，有着不同的大，假设第i个杯子已满，向其倒水，多余的水会正正好好流向第i+1个杯子
  * (如果i=n时没有下一个杯子，不会有杯子接住此时多余的水而溢出到魔法训练室的水池）。
  * 这些杯子有着初始固定的水量，每次练习后杯子里的水都会还原到最初状态。
  * 每次练习时，魔法黑板会告诉塔子哥需要将哪一人杯子倒满水。因为每个杯子的材质和形状有所不同所以对其释放倒水魔法需要消耗的魔法值不同。
  * 塔子哥想尽可能多练习，所以需要最小化每次消耗的魔法值的总量。
  *
  * 输入描述：第一行一个整数 n ，表示杯子数量。
  * 第二行 n 个整数1,2,...xn，依次表示第 i 个杯子能容纳水的量（单位为毫升）。
  * 第三行 n 个整数1,2,...yn，依次表示第 i 个杯子初始有的水量（单位为毫升）。
  * 第四行 n 个整数1,2,...zn，依次表示对第 i 个杯子每添加1毫升水需要消耗的法力值。
  * 第五行一个整数 m ，表示练习的数量。
  * 第六行 m 个整数，1,2,...qm，依次表示第 i 次练习时需要将第 qi个杯子倒满。
  * (每次练习过后，杯子里的水量都会还原为初始状态，不会影响到下一次练习）
  * 1≤n,m≤3000，1≤yi≤x_x≤10^9 ，1≤zi≤300，1≤qi≤n
  * 
  * 输出描述：输出第一行 m 个数，依次表示每次训练时需要消耗的最小法力值。如果杯子初始本身就是满的，则需要消耗的法力值为 0
  * 
  */
public class Problem49 {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[] capacity = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] initial = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] cost = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        
        // 没有前缀和会部分超时
        long[] preSum = new long[n + 1];
        preSum[0] = 0l;
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + (long)(capacity[i - 1] - initial[i - 1]);
        }
        
        // dp[i]表示的是灌满第i个杯子需要的法力值
        long[] dp = new long[n];

        for (int i = 0; i < n; i++) {
            // 直接将水倒入第i个杯子中的消耗
            dp[i] = (long)(cost[i] * (long)(capacity[i] - initial[i]));
            // 从前面的杯子开始算起
            for (int j = 0; j < i; j++) {
                if (cost[j] >= cost[i]) {
                    continue;
                }
                // 从符合条件的j+1到i一共需要多少水
                // 不知道为什么这样也能AC
                /**
                 * long need = 0;
                 * for (int k = j; k <= i; k++) {
                 *    need += (long)(capacity[k] - initial[k]);
                 * }
                 * dp[i] = Math.min(dp[i], need * cost[j]);
                 */
                
                // 某一区间内的和，前缀和
                // long need = 0;
                // for (int k = j + 1; k <= i; k++) {
                //     need += (long)(capacity[k] - initial[k]);
                // }

                dp[i] = Math.min(dp[i], dp[j] + (preSum[i + 1] - preSum[j + 1]) * cost[j]);
            }
        }

        int m = Integer.parseInt(in.readLine());
        int[] idx = Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        for (int i = 0; i < m; i++) {
            System.out.printf("%d ", dp[idx[i] - 1]);
        }
        
        in.close();
    }
}
