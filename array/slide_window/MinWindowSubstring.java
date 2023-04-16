/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-04 08:53:46
 * @LastEditTime: 2023-04-16 13:11:00
 */
package array.slide_window;

import java.util.HashMap;

/**
 * leetcode 76 hard 最小覆盖字串
 * 
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 */
public class MinWindowSubstring {
    public static String minWindow(String s, String t) {
        HashMap<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        int left = 0, right = 0, valid = 0;
        int start = 0;
        int minLen = Integer.MAX_VALUE;

        for (char ch : t.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        while (right < s.length()) {
            char ch = s.charAt(right);
            right++;
            if (need.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if (need.get(ch).equals(window.get(ch))) {
                    valid++;
                }
            }
            System.out.println(left);

            // 缩减窗口
            while (valid == need.size()) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }

                char d = s.charAt(left);
                left++;
                if (need.containsKey(d)) {
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.getOrDefault(d, 0) - 1);
                }
            }
        }

        System.out.println(start);
        System.out.println(minLen);
        System.out.println(need);

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }

    public static void main(String[] args) {
        String s = "aab";
        String t = "aab";
        String str = minWindow(s, t);
        System.out.println(str);
    }
}

/**
 * 给你一个字符串 s 、一个字符串 t 。
 * 返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
 */
class day0416 {
    public String minWindow(String s, String t) {
        HashMap<Character, Integer> window = new HashMap<>();
        HashMap<Character, Integer> need = new HashMap<>();
        for (char ch : t.toCharArray()) {
            need.put(ch, need.getOrDefault(ch, 0) + 1);
        }

        int left = 0, right = 0;
        int valid = 0;
        int minLen = Integer.MAX_VALUE, start = 0;

        while (right < s.length()) {
            char ch = s.charAt(right);
            right++;

            if (need.containsKey(ch)) {
                window.put(ch, window.getOrDefault(ch, 0) + 1);
                if (window.get(ch).equals(need.get(ch))) {
                    valid++;
                }
            }

            while (valid == need.size()) {
                if (right - left < minLen) {
                    minLen = right - left;
                    start = left;
                }
                char d = s.charAt(left);
                left++;

                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }

        return minLen == Integer.MAX_VALUE ? "" : s.substring(start, start + minLen);
    }
}