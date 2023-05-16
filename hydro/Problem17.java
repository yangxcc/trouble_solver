/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-16 16:20:36
 * @LastEditTime: 2023-05-16 16:37:06
 */
package hydro;

import java.util.HashSet;
import java.util.Scanner;

/**
 * 问题来源：http://101.43.147.120/p/P1018
 * 问题描述：能否找到数组中的任意一个数，它能由数组中另外两个数的和得到,即对于数组中每个数a_i，能不能找到另外的两个数 
 * a_j和a_k，使得 a_i = a_j + a_k (j,k可以相等)。
 * 输入描述：第一行输入一个正整数 n，代表数组长度
 * 第二行输入 n 个正整数a_i，代表数组的每个元素。 1 < n < 10^5，所有a_i的总和不超过10^5
 * 输出描述：输出 n 行，每行输出一个字符串，第 i 行代表查询第 i 个数的答案。
 * 如果第 i 个元素可以表示为两个元素之和，请输出 "Yes" ,否则输出 "No" 。
 */
public class Problem17 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        in.nextLine();
        HashSet<Integer> hs = new HashSet<>();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            hs.add(arr[i]);
        }
        
        boolean[] sum = new boolean[100000];
        for (Integer x : hs) {
            for (Integer y : hs) {
                sum[x + y] = true;
            }
        }

        for (int ele : arr) {
            if (sum[ele]) {
                System.out.println("Yes");
            } else {
                System.out.println("No");
            }
        }
    }
}
