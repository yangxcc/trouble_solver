/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-02 11:15:40
 * @LastEditTime: 2023-01-02 12:07:52
 */
package array.slide_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 不清楚为啥AC不了，测试用例通过61/63
 * Go可以AC
 */
public class FindAllAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        HashMap<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0)+1);
        }

        int left = 0, right = 0, valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            // 将元素放入窗口
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0)+1);
                if (need.get(c) == window.get(c)) {
                    valid++;
                }
            }
            right++;

            while (right - left >= p.length()) {
                if (valid == need.size()) {
                    ans.add(left);
                }

                char d = s.charAt(left);
                left++;

                if (need.containsKey(d)) {
                    if (need.get(d) == window.get(d)) {
                        valid--;
                    }
                    window.put(d, window.get(d)-1);
                }
            }
        }
        return ans;
    }
}
