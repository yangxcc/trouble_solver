/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-02 10:59:48
 * @LastEditTime: 2023-03-08 22:27:37
 */
package array.slide_window;

import java.util.HashMap;

/**
 * leetcode 3 middle 无重复字符的最长子串
 * 
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 */
public class LongestSubstring{
    public int lengthOfLongestSubstring(String s) {
        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0, maxLen = 0;

        while (right < s.length()) {
            char c = s.charAt(right);
            right++;
            window.put(c, window.getOrDefault(c, 0)+1);

            while (window.get(c) > 1) {
                char l = s.charAt(left);
                window.put(l, window.get(l)-1);
                left++;
            }

            // 两种更新窗口的写法都对
            // while (window.size() < right - left) {
            //     char l = s.charAt(left);
            //     window.put(l, window.get(l)-1);
            //     if (window.get(l) == 0) {
            //         window.remove(l);
            //     }
            //     left++;
            // }

            maxLen = Math.max(maxLen, right-left);
        }
        return maxLen;
    }
}