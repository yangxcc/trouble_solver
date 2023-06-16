/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-16 21:33:36
 * @LastEditTime: 2023-06-16 21:58:57
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1248
 * 
 * 问题描述：塔子哥不想删除太多的字符，也不想保留太长的串。所以，他想知道，在保证删除一个前缀和一个后缀的情况下（前缀和后缀都可以为空），
 * 即保留一个原串的连续子串(可以为空)，他需要最小化以下代价：
 * 被删除的字符 1 的个数；
 * 剩下的子串的字符 0 的个数。
 * 
 * 输入描述：一个长度为n的01字符串
 * 
 * 输出描述：一个整数，表示最小的操作代价。
 * 
 * 不好想，做不出来
 */
public class Problem59 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int n = str.length();
        int countOf0 = 0;
        for (int i = 0; i < n; i++) {
            if (str.charAt(i) == '0') {
                countOf0++;
            }
        }
        // left[i]表示的是删除[0, i]这一段的最大贡献
        int[] left = new int[n + 1];
        int contribute = 0;
        for (int i = 1; i <= n; i++) {
            if (str.charAt(i - 1) == '0') {
                contribute++;
            } else {
                contribute--;
            }

            left[i] = Math.max(left[i - 1], contribute);
        }

        int[] right = new int[n + 1];
        contribute = 0;
        for (int i = n - 1; i >= 0; i--) {
            if (str.charAt(i) == '0') {
                contribute++;
            } else {
                contribute--;
            }
            right[i] = Math.max(right[i + 1], contribute);
        }

        int ans = countOf0;
        for (int i = 0; i <= n; i++) {
            ans = Math.min(ans, countOf0 - left[i] - right[i]);
        }

        System.out.println(ans);
        in.close();
    }
}