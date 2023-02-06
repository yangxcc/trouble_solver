/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-06 19:55:56
 * @LastEditTime: 2023-02-06 10:53:18
 */
package backtracking.combination;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

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

        for (int i = 0; i < digits.length(); i++) {
            backtrack(digits, num2Str, i, new StringBuilder());
        }

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

            backtrack(digits, num2Str, i + 1, path);

            // 这两种方法都不行
            // path = new StringBuilder(path.substring(0, path.length() - 1));
            path.deleteCharAt(idx);
        }
    }
}
