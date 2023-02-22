/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-22 14:37:34
 * @LastEditTime: 2023-02-22 11:15:37
 */
package greedy.interval_cross;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.print.DocFlavor.CHAR_ARRAY;

/**
 * leetcode 763 middle 划分字母区间
 * 
 * 给你一个字符串 s 。我们要把这个字符串划分为尽可能多的片段，同一字母最多出现在一个片段中。
 * 注意，划分结果需要满足：将所有划分结果按顺序连接，得到的字符串仍然是 s 。
 * 返回一个表示每个字符串片段的长度的列表。
 * 
 * 输入：s = "ababcbacadefegdehijhklij"
 * 输出：[9,7,8]
 * 解释：
 * 划分结果为 "ababcbaca"、"defegde"、"hijhklij" 。
 * 每个字母最多出现在一个片段中。
 * 像 "ababcbacadefegde", "hijhklij" 这样的划分是错误的，因为划分的片段数较少。 
 */
public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        // 用来记录每个字母最远出现的位置
        int[] record = new int[26];
        for (int i = 0; i < s.length(); i++) {
            record[s.charAt(i) - 'a'] = i;
        }

        int left = 0, right = 0; // right用来记录已经访问过的字符的最远距离
        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            right = Math.max(right, record[s.charAt(i) - 'a']);
            if (right == i) {
                ans.add(right - left + 1);
                left = right + 1;
            }
        }

        return ans;
    }
}
