/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-16 22:00:26
 * @LastEditTime: 2023-06-16 22:32:25
 */
package hydro;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1267
 * 
 * 问题描述：城市为了缓解交通拥堵，实行了限行规则，每天都有一些车牌号的最后一位数字被禁止上路
 * 塔子哥不想因为限行而迟到或者请假，因为他的工作很重要，涉及到很多国际贸易的合同和谈判。
 * 所以他想买几辆车，让他每天都有车可以用。
 * 假设他不能换车牌号，也不能选择其他交通方式，而且他的工作时间是固定的，问他至少需要买多少辆车？
 * 如果没有办法做到，就输出 −1 。
 * 
 * 输入描述：输入一共有 7 行，表示周一至周日的限行情况。
 * 输入每一行，第 i 行的第一个数字 ci 表示当天限行数字个数，随后输入 ci 个互不相同的数字，第 j 个数字为 aij，表示限行数字。
 * 对于所有的数据，0≤ci≤10,0≤a_ij≤9 。
 * 
 * 输出描述：输出为一个整数，表示塔子哥需要的最少车辆数或塔子哥不能保证每天都至少有一辆车可以出行。
 * 
 */
public class Problem60 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int[] arr = new int[7];
        for (int i = 0; i < 7; i++) {
            int n = in.nextInt();
            for (int j = 0; j < n; j++) {
                int val = in.nextInt();
                // 比如针对限行尾号0 1 2 3 4 5 6 7, arr[i]= 00 1111 1111
                arr[i] |= 1 << val;
            }            
        }

        int ans = 0;
        // 枚举每一个值
        for (int i = 0; i <= 1 << 10; i++) {
            boolean flag = true;
            for (int schma : arr) {
                // 比如101和001,i中第k位的1表示买了车牌号尾号是k的车，如果符合下面的条件，说明所有的车子在某一天都无法驶出
                if ((schma | i) == schma) {
                    flag = false;
                    break;
                }
            }

            if (flag) {
                int count = 0; // 买车的个数
                for (int j = 0; j < 10; j++) {
                    // i中有多少个1
                    if (((i >> j) & 1) == 1) {
                        count++;
                    }
                }

                ans = Math.min(ans, count);
            }
        }

        if (ans == 10) {
            System.out.println(-1);
        } else {
            System.out.println(ans);
        }
        in.close();
    }
}
