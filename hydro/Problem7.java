/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-08 19:29:16
 * @LastEditTime: 2023-05-08 19:52:45
 */
package hydro;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 问题来源：http://101.43.147.120/p/P1007
 * 问题描述：现在塔子哥有个字符串str ，请你找出该字符串中不含重复字符的最长子串的长度。
 * 输入描述：输入一个字符串str
 * 输出描述：输出不含重复字符的最长子串的长度
 */
public class Problem7 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();

        HashMap<Character, Integer> memo = new HashMap<>();
        int left = 0, right = 0;
        int ans = 0;
        while (right < str.length()) {
            char c = str.charAt(right);
            right++;

            memo.put(c, memo.getOrDefault(c, 0) + 1);
            while (right - left > memo.size()) {
                char d = str.charAt(left);
                left++;

                if (memo.get(d) == 1) {
                    memo.remove(d);
                } else {
                    // > 1
                    memo.put(d, memo.get(d) - 1);
                }
            }

            ans = Math.max(ans, right - left);
        }

        System.out.println(ans);
    }
}
