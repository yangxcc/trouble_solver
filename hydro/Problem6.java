/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-08 18:49:51
 * @LastEditTime: 2023-05-08 19:14:56
 */
package hydro;

import java.util.Scanner;

/**
 * 题目来源：http://101.43.147.120/p/P1006
 * 题目描述：塔子哥拿到了一个长度为n的数组，他有一种操作:
 * 将数组里的每个数和 x 取min，(x自己选定)。塔子哥想知道，使得数组的总和不超过tot的情况下,
 * x最大可以是多少？
 * 如果数组的总和原本就已经小于等于tot了，直接输出-1.
 * 
 * 输入描述：输入第一行包含两个整数n，tot(1≤n,tot≤100000).
 * 第二行包含n个整数，代表数组的每个元素(1≤a_i≤100000)。
 * 
 * 输出描述：输出为一个整数，代表x最大可以取到多少，如果不需要进行操作则输出-1。
 * 
 * 
 * 题意翻译一下就是确定一个x，如果x比数组元素小，那么就用x替换掉这个元素，反之不替换
 * 找到能使数组和不超过tot的情况下的最大的x
 * 
 * 想到了二分，而且是一遍AC了，哈哈哈哈哈哈
 */
public class Problem6 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), tot = in.nextInt();
        in.nextLine();
        int[] arr = new int[n];
        int sum = 0, minVal = 100001, maxVal = 0;
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            arr[i] = val;
            minVal = Math.min(minVal, val);
            maxVal = Math.max(maxVal, val);
            sum += val;
        }

        if (sum <= tot) {
            System.out.println(-1);
        } else {
            int left = minVal, right = maxVal - 1;
            while (left <= right) {
                int mid = left + (right - left) / 2;
                // 找右边界
                if (repalce(arr, mid) == tot) {
                    left = mid + 1;
                } else if (repalce(arr, mid) < tot) {
                    left = mid + 1;
                } else if (repalce(arr, mid) > tot) {
                    right = mid - 1;
                }
            }

            System.out.println(right);
        }
    }

    private static int repalce(int[] arr, int min) {
        int sum = 0;
        for (int x : arr) {
            sum += x < min ? x : min;
        }
        return sum;
    }
}