/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-08 21:25:10
 * @LastEditTime: 2023-05-08 21:52:47
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：http://101.43.147.120/p/P1008
 * 问题描述：时针每小时转动30°，时针和分针从整点开始转动到重合所需要的时间 t=s/(v1-v2)，
 * 其中 s 为时针与分针之间的夹角度数， v1,v2分别为分针和时针的转动速度（度/分）。
 * 输入描述：介于0 ~ 23之间的整数
 * 输出描述：介于 0:00 ~ 23:59 之间的时刻，精度控制到分钟（四舍五入）。例如： 2:11
 * 
 * 
 * 这道题不看输出用例真是想不到，发现了一个知识盲点，整型和浮点型的切换不熟练
 */
public class Problem8 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        double s = (t * 30) % 360;
        double v1 = 6, v2 = 0.5;
        double x = v1 - v2;
        if (Math.round(s / x) == 60) {
            System.out.printf("%d:%s", t + 1 == 24 ? 0 : t + 1, "00");
        } else if (Math.round(s / x) == 5) {
            System.out.printf("%d:%s", t, "05");
        } else if (Math.round(s / x) == 0) {
            System.out.printf("%d:%s", t, "00");
        } else {
            System.out.printf("%d:%d", t, Math.round(s / x));
        }
    }

    /**
     * 看到一个题解，做法很优雅
     * 
     * @param t
     * @return
     */
    private String process(int t) {
        if (t == 11) {
            return "12:00";
        }

        if (t == 23) {
            return "0:00";
        }

        int minutes = (int) ((t % 12) * 30.0 / 5.5 + 0.5); // 强转类型的时候会向下取整，所以这里加上0.5！！！很妙，或者是使用Math.round函数
        if (minutes < 10) {
            return t + ":0" + minutes;
        }

        return t + ":" + minutes;
    }
}
