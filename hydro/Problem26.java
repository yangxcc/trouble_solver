/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-20 15:08:13
 * @LastEditTime: 2023-05-20 15:45:15
 */
package hydro;

import java.util.Arrays;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1028
 * 
 * 问题描述：该程序需要在字符串中查找第k个最小的Ascii码字母。该程序接受一个由n个大小写字母组成的字符串及一个整数k（k≥1 ）作为输入，
 * 按照Ascii码值从小到大的排序规则查找字符串中第k个最小Ascii码值的字母。如果k大于字符串长度，
 * 则输出最大Ascii值的字母所在字符串的位置索引（字符串的第一个字符位置索引为0），
 * 如果有重复的字母，则输出字母的最小位置索引。现在，塔子哥的程序被广泛应用于各种排序任务中。
 * 
 * 输入描述：kjahah
 *          4
 * 
 * 输出描述：3
 */
public class Problem26 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        int k = in.nextInt();

        Pair[] pairs = new Pair[str.length()];
        for (int i = 0; i < str.length(); i++) {
            pairs[i] = new Pair(str.charAt(i), i);
        }

        Arrays.sort(pairs, (p1, p2) -> {
            return p1.val - p2.val;
        });

        int index = -1;
        if (k > str.length()) {
            index = str.length() - 1;
        } else {
            index = k - 1;
        }

        while (index > 0 && pairs[index].val == pairs[index - 1].val) {
            index--;
        }
        System.out.println(pairs[index].idx);
        in.close();
    }
}

class Pair {
    char val;
    int idx;

    public Pair(char _val, int _idx) {
        this.val = _val;
        this.idx = _idx;
    }
}