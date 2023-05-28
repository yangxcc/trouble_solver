package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1049
 * 
 * 问题描述：给定一个int型整数x,将x的二进制表示中第i位和第j位的值互换。0≤i,j≤31
 * 注意: x的二进制表示的最右边为第0位。
 * 
 * 输入描述：在一行中输入三个整数，x,i,j, 整数之间用一个空格分隔
 * 
 * 输出描述：在一行中输出互换后的结果
 * 
 */
public class Problem40 {

    /**
     * 我的笨思路，得到二进制字符串，然后交换i,j的位置，然后再还原出来，这个里面有一些坑
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int i = in.nextInt();
        int j = in.nextInt();

        char[] chs = Integer.toString(x, 2).toCharArray();
        /**
         * 坑1：位置是从右开始的
         */
        char[] bits = new char[32];
        int idx = 31;
        for (int p = chs.length - 1; p >= 0; p--) {
            bits[idx--] = chs[p];
        }

        /**
         * 坑2：位置是从右开始的，交换i，j的位置
         */
        char tmp = bits[bits.length - 1 - i];
        bits[bits.length - 1 - i] = bits[bits.length - 1 - j];
        bits[bits.length - 1 - j] = tmp;

        /**
         * 坑3：因为这里确定了肯定是32位的整数，所以无论怎么交换，都不会越界
         */
        int ans = 0;
        for (int k = bits.length - 1; k >= 0; k--) {
            if (bits[k] == '1') {
                int len = bits.length - k  - 1;
                ans += Math.pow(2, len);
            }
        }

        
        /**
         * 坑4：-38通过Integer.toString(x, 2).toCharArray()之后的数组为：[-, 1, 0, 0, 1, 1, 0]
         */
        if (chs[0] == '-') {
            System.out.println(-ans);
            
        } else {
            System.out.println(ans);
        }

        in.close();
    }


    /**
     * 一个好的思路
     */
    private static void method2() {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int i = in.nextInt();
        int j = in.nextInt();

        int a = (x >> i) & 1;  // 得到x的i位置上的位
        int b = (x >> j) & 1;  // 得到x的j位置上的位

        x ^= ((a ^ b) << i);
        x ^= ((a ^ b) << j);

        System.out.println(x);
        in.close();
    }
}
