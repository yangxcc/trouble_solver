/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-13 15:37:27
 * @LastEditTime: 2023-03-14 19:07:19
 */
package string.reverse;

/**
 * leetcode 541 simple 反转字符串2
 * 
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 */
public class ReverseString2 {
    public String reverseStr(String s, int k) {
        char[] ch = s.toCharArray();
        for (int i = 0; i < ch.length; i += 2 * k) {
            if (i + k >= ch.length) {
                reverse(ch, i, ch.length - 1);
            } else {
                reverse(ch, i, i + k - 1);
            }
        }

        return new String(ch);
    }

    // 在这个区间内反转
    public void reverse(char[] ch, int left, int right) {
        for (int i = left, j = right; i < j; i++, j--) {
            char tmp = ch[i];
            ch[i] = ch[j];
            ch[j] = tmp;
        }
    }
}
