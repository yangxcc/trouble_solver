package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1038
 * 
 * 问题描述：塔子哥有四种木块，每种木块分别有x1, x2, x3, x4个，每个木块有一个材料阈值，
 * 只有材料阈值不低于 d 的木块才可以用，每四种木块各一个可以组装成一个木凳，求最多可以组装成多少个木凳。
 * 
 * 输入描述：第一行四个整数，表示x1, x2, x3, x4，第二行一个整数，表示d
 * 接下来四行，每行 xi 个整数，表示每种木块的材料阈值
 * 
 * 输出描述：输出可以组装的木凳个数
 * 
 * 其实说白了就是找x1, x2, x3, x4中比d大的数的最小值
 */
public class Problem36 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int count = 0;
        int x1 = in.nextInt();
        int x2 = in.nextInt();
        int x3 = in.nextInt();
        int x4 = in.nextInt();
        count = Math.min(Math.min(x1, x2), Math.min(x3, x4));

        int d = in.nextInt();
        int i = 0;
        in.nextLine();
        while (i < 4) {
            String[] eles = in.nextLine().split(" ");
            int tmpCount = 0;
            for (String ele : eles) {
                int x = Integer.parseInt(ele);
                if (x >= d) {
                    tmpCount++;
                }
            }
            count = Math.min(tmpCount, count);

            i++;
        }

        System.out.println(count);
        in.close();
    }
}
