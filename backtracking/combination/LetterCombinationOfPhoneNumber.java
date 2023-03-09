/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-06 19:55:56
 * @LastEditTime: 2023-03-09 09:49:50
 */
package backtracking.combination;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * leetcode 17 middle 电话号码的字母组合
 * 
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
public class LetterCombinationOfPhoneNumber {
    List<String> ans = new ArrayList<>();
    
    public List<String> letterCombinations(String digits) {
        String[] num2Str = new String[] {
                "",
                "",
                "abc",
                "def",
                "ghi",
                "jkl",
                "mno",
                "pqrs",
                "tuv",
                "wxyz"
        };

        backtrack(digits, num2Str, 0, new StringBuilder());

        return ans;
    }

    // 不知道哪里出错，Java不能做切片真是麻烦死了
    public void backtrack(String digits, String[] num2Str, int idx, StringBuilder path) {
        if (digits.length() == path.length()) {
            ans.add(path.toString());
            return;
        }

        String chars = num2Str[digits.charAt(idx) - '0'];
        for (int i = 0; i < chars.length(); i++) {
            path.append(chars.charAt(i));

            backtrack(digits, num2Str, idx + 1, path);

            // 在Java中这样不行，具体原因未知，大概是因为引用变了，前后两个path不一样了？
            // path = new StringBuilder(path.substring(0, path.length() - 1));
            path.deleteCharAt(idx);
        }
    }
}

/**
 * leetcode 17 middle 电话号码的字母组合
 * 
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 */
class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input = in.nextLine();

        backtrack(input, 0, new StringBuilder());

        for (String path : ans) {
            System.out.println(path);
        }
    }

    static List<String> ans = new ArrayList<>();
    static String[] record = new String[] {
            "",
            "",
            "abc",
            "def",
            "ghi",
            "jkl",
            "mno",
            "pqrs",
            "tuv",
            "wxyz"
    };

    public static void backtrack(String input, int idx, StringBuilder path) {
        if (path.length() == input.length()) {
            ans.add(path.toString());
            return;
        }

        String chs = record[input.charAt(idx) - '0'];
        for (int i = 0; i < chs.length(); i++) {
            path.append(chs.charAt(i));

            backtrack(input, idx + 1, path);

            path.deleteCharAt(idx);
        }
    }
}