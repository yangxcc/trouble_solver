/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-02 10:59:48
 * @LastEditTime: 2023-01-02 11:12:16
 */
package array.slide_window;

import java.util.HashMap;

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

            maxLen = Math.max(maxLen, right-left);
        }
        return maxLen;
    }
}