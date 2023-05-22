/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-22 16:20:09
 * @LastEditTime: 2023-05-22 16:25:28
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1032
 * 问题描述：商家提供两种不同的桃子礼包。第一种礼包包含 b 个桃子，售价为 a 元，第二种礼包包含 d 个桃子，
 * 售价为 c 元。
 * 每个礼包可以买任意次，但只能选择一种礼包购买。
 * 塔子哥需要购买至少 k 个桃子，他希望只购买礼包 1 的花费比只购买礼包 2 的花费要小。
 * 你能帮塔子哥求出这个 k 吗？
 * 
 * 输入描述：第一行输入一个正整数 t ，代表询问的次数。接下来的 t 行，每行输入四个正整数 a， b ，c ，d ，用空格隔开。
 * 1≤t≤10 ，1≤a,b,c,d≤10^9
 * 
 * 输出描述：对于每行询问，输出一个合法的正整数k，(0<k<2^31)，代表买桃子的数量。有多解时输出任意即可。
 * 如果无解则直接输出-1
 */
public class Problem30 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        while (t > 0) {
            int a = in.nextInt();
            int b = in.nextInt();
            int c = in.nextInt();
            int d = in.nextInt();

            // if (a / b >= c / d) {
            //     System.out.println(-1);
            // } else {
            //     System.out.println((int)b);
            // }
            // 单价礼包1贵，不一定没有k，比如 5 1 8 2，因为礼包是不能拆分的

            if (a < c) {
                System.out.println(b);
            } else if (a == c) {
                if (b > d) {
                    System.out.println(b);
                } else {
                    System.out.println(-1);
                }
            } else {
                if (b <= d) {
                    System.out.println(-1);
                } else {
                    // b > d，看看第二种方案买b个需要多少钱 ceil(b / d) * c
                    // 我最开始想的是看看买b * d个分别都需要多少钱，但是这样应该是超阈值了，只能AC 3/11，就差这一点
                    if ((b + d - 1 / d) * c > a ) {
                        System.out.println(b);
                    } else {
                        System.out.println(-1);
                    }
                }
            }
            t--;
        }

        in.close();
    }
}
