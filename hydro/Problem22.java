/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-18 22:34:20
 * @LastEditTime: 2023-05-18 22:37:54
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1024
 * 问题描述：想知道是否存在一种操作方式，可以将这个字符串转换为全 0 串或全 1 串。
 * 他每次可以选择两个连续的下标，并对该下标的元素对 1 做异或操作（ 0 变 1 ， 1 变 0 ）。
 * 
 * 输入描述：输入一个只包含字符0或者1的字符串 1≤∣s∣≤1000000)
 * 
 * 输出描述：如果可以转化为全0串或者全1串，输出"yes",否则输出"no"(不含引号)
 * 
 * 
 * 只要是有偶数个0，那么就能变成全1串
 * 只要是有偶数个1，那么就能变成全0串
 */
public class Problem22 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int count0 = 0;
        for (char ch : str.toCharArray()) {
            if (ch == '0') {
                count0++;
            }
        }
        int count1 = str.length() - count0;
        if (count0 % 2 == 0 || count1 % 2 == 0) {
            System.out.println("yes");
        } else {
            System.out.println("no");
        }

        in.close();
    }
}
