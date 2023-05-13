/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-13 11:03:22
 * @LastEditTime: 2023-05-13 11:06:21
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：
 * 问题描述：现在塔子哥拿到了一个数组arr，每次操作可以将数组arr中的一个数+1，求用 至多d 次操作将这个数组变为一个 
 * G阶数组 后的最大值为多少，如果不能变成合法的G阶数组 则输出 −1
 * 输入描述：第一行n,G,d，分别表示数组arr的长度，G的大小以及操作次数
 * 第二行表示n个数，表示数组元素，以空格隔开
 * 1≤k,n,d≤100000 , G≤n， −10^9≤arr_i ≤10^9
 * 输出描述：最大值或-1
 */
public class Problem10 {

    /**
     * 通过用例 8/9，剩下一个是RUNTIMEERROR，不知道是什么样的用例
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), G = in.nextInt(), d = in.nextInt();
        in.nextLine();
        int diff = 0;
        int maxVal = Integer.MIN_VALUE;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            if (i - G >= 0) {
                diff += Math.abs(arr[i] - arr[i - G]);
            }
            maxVal = Math.max(maxVal, arr[i]);
        }

        if (diff > d) {
            System.out.println(-1);
        } else {
            d -= diff;  // 现在相当于是G阶数组了 
            //找到最大值
            if (d == 0) {
                System.out.println(maxVal);
            } else {
                // 从头遍历
                int ans = maxVal;
                for (int i = 0; i < G; i++) {
                    // 从i到最后，一共有多少个
                    int count = (n - i - 1) / G;
                    int need = (maxVal - arr[i]) * count;
                    if (d <= need) {
                        continue;
                    } else {
                        d -= need;
                        ans = Math.max(ans, maxVal + d / count);
                    }
                }

                System.out.println(ans);
            }
        }

    }
}
