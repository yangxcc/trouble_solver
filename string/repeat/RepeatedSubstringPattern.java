/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-14 22:12:25
 * @LastEditTime: 2023-03-02 21:19:26
 */
package string.repeat;

/**
 * leetcode 459 simple 重复的子字符串
 * 给定一个非空的字符串 s ，检查是否可以通过由它的一个子串重复多次构成。
 */
public class RepeatedSubstringPattern {
    public boolean repeatedSubstringPattern(String s) {
        // 只有一个字母，没办法有重复的子串
        if (s.length() <= 1) {
            return false;
        }

        for (int i = 0; i < s.length() / 2; i++) {
            String sub = s.substring(0, i+1);
            if (s.length() % sub.length() != 0) {
                continue;
            }

            for (int j = sub.length(); j <= s.length(); j += sub.length()) {
                if (j == s.length()) {
                    return true;
                }
                if (!sub.equals(s.substring(j, j+sub.length()))) {
                    break;
                }
            }
        }

        return false;
    }
}
