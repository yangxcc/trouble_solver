/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-08 16:32:52
 * @LastEditTime: 2023-02-14 20:04:21
 */
package hashmap.is_anagram;

import java.util.HashMap;;

/**
 * leetcode 242 simple 有效的字母异位词
 * 
 * 给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
 * 注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
 */
public class ValidAnagram {
    public boolean isAnagram(String s, String t) {
        HashMap<Character, Integer> count = new HashMap<>();
        for (char ch : s.toCharArray()) {
            count.put(ch, count.getOrDefault(ch, 0) + 1);
        }

        for (char ch : t.toCharArray()) {
            if (count.containsKey(ch)) {
                count.put(ch, count.get(ch) - 1);
                if (count.get(ch) == 0) {
                    count.remove(ch);
                }
            } else {
                return false;
            }
        }

        return count.size() == 0;
    }
}