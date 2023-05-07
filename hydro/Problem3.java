/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-07 21:15:09
 * @LastEditTime: 2023-05-07 21:48:57
 */
package hydro;

import java.util.Scanner;

import hashmap.is_anagram.ValidAnagram;

/**
 * 题目来源：http://101.43.147.120/p/P1003
 * 题目描述：Tazi和kozi是两兄弟，妈妈给了他们一大袋糖，每块糖都有属于自己的重量。
 * 
 * 现在他们想要将这些糖分成两堆。
 * 分糖的任务当然落到了大哥Tazi的身上，然而kozi要求必须两个人获得的糖的总重量“相等”（根据kozi的逻辑），要不然就会哭的。
 * 非常不幸的是，kozi还非常小，并且他只会先将两个数转成二进制再进行加法，而且总会忘记进位。
 * 比如5 + 4 = 1
 * 7 + 9 = 14
 * 50 + 10 = 56
 * 
 * 现在Tazi非常贪婪，他想要尽可能使自己得到的糖的总重量最大，且不让kozi哭。
 * 
 * 输入描述：输入的第一行是一个整数 N(2≤N≤15 )，表示有袋中多少块糖。
 * 第二行包含N个用空格分开的整数 1≤Weight_i≤10^6)，表示第i块糖的重量。
 * 输出描述：如果能让kozi不哭，输出Tazi所能获得的糖的总重量，否则输出“NO”。
 */
public class Problem3 {
    /**
     * 这道题目的重点就在于需要知道两个二进制数相加不进位，实际上等同于异或运算
     * 因为异或运算就是相同的为0，不同的为1，同样，1+1=0在这道题里面是成立的
     * 
     * 所以知道了上面这个就好办了，kozi不哭那就是kozi认为最后分出来的两堆是相等的
     * A^B=0，拆开来看，只要是所有元素异或的结果是0，那么kozi肯定不会哭，记录下最小的值min，
     * 最后的结果就是sum-min
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int koziSum = 0, taziSum = 0, minVal = 1000001;
        for (int i = 0; i < n; i++) {
            int val = in.nextInt();
            taziSum += val;
            koziSum ^= val;
            minVal = Math.min(minVal, val);
        }

        if (koziSum != 0) {
            System.out.println("NO");
        } else {
            System.out.println(taziSum - minVal);
        }
    }
}

/**
 * 通过这道题，我联想到了将数组分成等和的两部分（应该是能用dp），分成等和的K部分（回溯穷举）
 */
