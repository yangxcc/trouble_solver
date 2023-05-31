/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-31 20:36:20
 * @LastEditTime: 2023-05-31 20:48:25
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1139
 * 
 * 问题描述：塔子哥是一个热爱收藏的年轻人，他喜欢收集各种有趣的物品，例如邮票、硬币、瓶盖等等。
 * 他的收藏品越来越多，于是他决定为自己在收藏架上建了一排 n 个收藏夹，分别编号为 1,2,3…n。
 * 这样，他就可以更好地组织和展示自己的收藏品了。塔子哥有些时候会突发奇想改变某一个收藏美里的内容，
 * 例如从中拿入、拿出一些藏品，这些的操作会改变塔子哥对这个收藏夹的欣赏程度，我们记编号为 i 的收藏夹，
 * 塔子哥对其的欣赏程度为ai。塔子哥在休息时间经常会欣赏连续编号的收藏夹，例如编号为 L,L+1,L+2,...,R−1,R 的这些收藏夹，
 * 他能从中获得的满足感为这些收藏失的欣赏程度之和，即 [L, R]对a_i求和
 * 塔子哥想在欣赏之前提前估算自己能得到的满足感，想知道如果他选择编号区间为 [L,R] 的收藏夹，
 * 能给他带来的满足感是多少。但是塔子哥不想自己计算，所以他想你帮他计算一下，然后告诉他。
 * 
 * 输入描述：第一行两个整数 n 和 m ，表示塔子哥的收藏夹数量和塔子哥的操作数量。
 * 初始时刻收藏夹都是空的，也即 a_i=0，i∈[1,n]
 * 第二行 m 个整数 op_1, op_2,…,op_m。
 * 第三行 m 个整数 x_1, x_2,…,x_m。
 * 第四行 m 个整数 y_1, y_2,…,y_m。
 * 这些共同表示了 m 次操作，对于第 i 次操作， op_i =0 时表示为一次收藏夹更新操作，
 * 会将 x_i位 置的收藏夹欣赏程度更新为 y_i，op_i = 1时表示为一次查询操作，表示如果塔子哥欣赏编号在区间
 * [x_i, y_i] 的收藏夹，能获得的满足感是多少
 * 对于所有的数据， 1≤n,m≤50000,op_i∈[0,1] ，当 op_i=0 时，1≤x_i≤n,0≤y_i≤10000 ；
 * 当op_i =1 时， 1≤x_i≤y_i ≤n ，保证至少有一次 op_i =1 的操作。
 * 
 * 输出描述：对每一个 op_i = 1的操作，输出一个数表示对应答案。空格隔开所有答案。
 */
public class Problem43 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        int[] arr = new int[n];
        in.nextLine();
        String[] ops = in.nextLine().split(" ");
        String[] pos = in.nextLine().split(" ");
        String[] vals = in.nextLine().split(" ");

        for (int i = 0; i < m; i++) {
            int leftPos = Integer.parseInt(pos[i]);
            int val = Integer.parseInt(vals[i]);
            if (ops[i].equals("0")) {
                // 更新
                arr[leftPos - 1] = val;
            } else {
                long tmpSum = 0;
                for (int j = leftPos - 1; j <= val - 1; j++) {
                    tmpSum += arr[j];
                }
                System.out.print(tmpSum + " ");
            }
        }

        in.close();
    }
}
