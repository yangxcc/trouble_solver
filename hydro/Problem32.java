/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-23 20:50:36
 * @LastEditTime: 2023-05-23 21:03:08
 */
package hydro;

import java.util.Scanner;


/**
 * 问题来源：https://codefun2000.com/p/P1034
 * 问题描述：这里有 m 堆竹笋，第 i 堆竹笋有 n[i] 根竹笋。
 * 为了能够尝试到更多的竹笋种类，他决定以不同的速度吃掉这些竹笋，塔子哥可以决定她吃竹笋的速度 k (单位: 根/小时)，
 * 而每次吃掉的数量也取决于他对这些竹笋的喜好程度。他决定每小时选择一堆竹笋，从中吃掉 k 根，如果这堆竹笋少于 
 * k 根，他将吃掉这堆的所有竹笋，然后这一小时内不会再吃更多的竹笋。求塔子哥可以在 h 小时内吃掉所有竹笋的最小速度 k ( k 为整数) 。
 * 
 * 输入描述：输入第一行两个整数：竹笋堆数m、时间h，（ 1<=m<=h<=1000000 ）
 * 第二行n个整数，中间用空格分割，代表每一堆竹笋的个数
 * 
 * 输出描述：返回塔子哥可以在 h 小时内吃掉所有竹笋的最小速度 k ( k 为整数)
 * 
 * leetcode原题，珂珂吃香蕉
 */
public class Problem32 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int m = in.nextInt(), h = in.nextInt();
        in.nextLine();
        int[] arr = new int[m];
        int maxSpeed = 0;
        for (int i = 0; i < m; i++) {
            arr[i] = in.nextInt();
            maxSpeed = Math.max(maxSpeed, arr[i]);
        }

        int minSpeed = 1;
        while (minSpeed <= maxSpeed) {
            int mid = minSpeed + (maxSpeed - minSpeed) / 2;
            if (findH(arr, mid) == h) {
                maxSpeed = mid - 1;
            } else if (findH(arr, mid) < h) {
                // mid太大了
                maxSpeed = mid - 1;
            } else {
                minSpeed = mid + 1;
            }
        }

        System.out.println(minSpeed);

        in.close();
    }

    private static int findH(int[] arr, int speed) {
        int count = 0;
        for (int x : arr) {
            if (x <= speed) {
                count++;
            } else {
                while (x > speed) {
                    count++;
                    x -= speed;
                }
            }
        }

        return count;
    }
}
