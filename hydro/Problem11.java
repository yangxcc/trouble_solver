package hydro;

import java.util.Scanner;

/**
 * 问题来源：http://101.43.147.120/p/P1012
 * 问题描述：“哈哈数”定义为:233∗10^i. 例如:233,2330,23300等
 * 现在塔子哥想玩一玩这种数。他想知道,你是否可以使用最少的 "233数"组成x ，
 * 无法做到时输出-1，总共t组输入(0<=x<=1e14，1<=t<=100)
 * 输入描述：
 * 4
 * 15347
 * 233
 * 4660
 * 6991

 * 输出描述：
 * -1
 * 1
 * 2
 * -1
 */

 /**
  * 最开始代码都写对了，但是通过用例是0，问题就出在来没有想到数据范围越界的问题！！！！
  */
public class Problem11 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        while (n > 0) {
            Long num = in.nextLong();
            if (num % 233 != 0) {
                System.out.println(-1);
            } else {
                Long count = num / 233;
                int ans = 0;
                while (count != 0) {
                    ans += count % 10;
                    count /= 10;
                }

                System.out.println(ans);
            }
            n--;
        }
    }
}
