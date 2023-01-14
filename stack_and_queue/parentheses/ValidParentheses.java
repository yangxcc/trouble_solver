/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-16 15:15:30
 * @LastEditTime: 2023-01-14 21:43:46
 */
package stack_and_queue.parentheses;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 20 simple 有效的括号
 * 
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 *  左括号必须用相同类型的右括号闭合。
 *  左括号必须以正确的顺序闭合。
 *  每个右括号都有一个对应的相同类型的左括号。
 */
public class ValidParentheses {
    public boolean isValid(String s) {
        // 现在Java中的stack都是用Deque来代替了，因为stack本质上还是vector，每个方法上都有synchronized关键字
        Deque<Character> stack = new LinkedList<Character>();
        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else {
                if (stack.size() == 0 || stack.pop() != want(c)) {
                    return false;
                }
            }
        }
        return stack.size() == 0;
    }

    public char want(char c) {
        if (c == ')') {
            return '(';
        } else if (c == ']') {
            return '[';
        } else if (c == '}') {
            return '{';
        }
        return ' ';
    }
}
