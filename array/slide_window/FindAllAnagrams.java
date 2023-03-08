/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-02 11:15:40
 * @LastEditTime: 2023-03-01 15:40:21
 */
package array.slide_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode 438 middle 找到字符串中所有字母异位词
 * 
 * 不清楚为啥AC不了，测试用例通过61/63 Go可以AC
 * FIX：包装类的比较，Integer中[-128,127]之间数据的比较是使用缓存，能直接使用==，但是在这个范围外的数据，会使用new/valueof关键字来实例化
 * ==是比较地址的，所以不能使用==，最好是使用compareTo方法
 * 
 * 参考链接：https://www.nowcoder.com/discuss/353148149285986304
 */
public class FindAllAnagrams {
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length()) {
            return new ArrayList<>();
        }

        List<Integer> ans = new ArrayList<>();
        HashMap<Character, Integer> need = new HashMap<>(), window = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0) + 1);
        }

        int left = 0, right = 0, valid = 0;
        while (right < s.length()) {
            char c = s.charAt(right);
            // 将元素放入窗口
            if (need.containsKey(c)) {
                window.put(c, window.getOrDefault(c, 0) + 1);
                if (need.get(c).equals(window.get(c))) {
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
                    if (need.get(d).equals(window.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return ans;
    }
}
