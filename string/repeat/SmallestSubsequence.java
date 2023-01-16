/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-16 10:41:04
 * @LastEditTime: 2023-01-16 13:48:09
 */
package string.repeat;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * leetcode 316 middle 去除重复的字母
 * 
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小
 * （要求不能打乱其他字符的相对位置）。
 * 
 * case
 * 输入：s = "bcabc"
 * 输出："abc"
 * 
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 */
public class SmallestSubsequence {
    // 不符合要求：改变了字符的相对位置
    @Deprecated
    public String smallestSubsequenceWrong(String s) {
        ArrayList<Character> memo = new ArrayList<Character>();
        for (char c : s.toCharArray()) {
            if (!memo.contains(c)) {
                memo.add(c);
            }
        }
        // memo中保存的是已经去重过的字符串，接下来就是按照字典序排序
        Collections.sort(memo, new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                return o1 - o2;
            }
        });

        StringBuilder sb = new StringBuilder();
        for (char c : memo) {
            sb.append(c);
        }
        return sb.toString();
    }

    public static String smallestSubsequence(String s) {
        Deque<Character> stack = new LinkedList<>();
        HashMap<Character, Integer> record = new HashMap<>();

        for (char ch : s.toCharArray()) {
            record.put(ch, record.getOrDefault(ch, 0)+1);
        }

        for (char ch : s.toCharArray()) {
            if (stack.contains(ch)) {
                record.put(ch, record.get(ch)-1);
                continue;
            }

            // 如果仅仅这么写的话，其实是错的，bad case：输入cba，应该输出cba，但输出的却是a
            // while (!stack.isEmpty() && stack.peekFirst() > ch) {
            //     stack.pop();
            // }
            // 所以，在pop之前还需要进一步判断，stack中peekFirst的元素个数
            while (!stack.isEmpty() && stack.peekFirst() > ch) {
                if (record.get(stack.peekFirst()) == 1) {
                    break;
                }
                char c = stack.poll();
                record.put(c, record.get(c)-1);
            }

            stack.push(ch);
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        String s = "bbcaac";
        smallestSubsequence(s);
    }
}
