/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-14 21:46:50
 * @LastEditTime: 2023-01-14 22:08:52
 */
package stack_and_queue.parentheses;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 1541 middle 平衡括号字符串的最少插入次数
 * 
 * 给你一个括号字符串 s ，它只包含字符 '(' 和 ')' 。一个括号字符串被称为平衡的当它满足：
 * 任何左括号 '(' 必须对应两个连续的右括号 '))' 。
 * 左括号 '(' 必须在对应的连续两个右括号 '))' 之前。
 * 比方说 "())"， "())(())))" 和 "(())())))" 都是平衡的， ")()"， "()))" 和 "(()))" 都是不平衡的。
 * 你可以在任意位置插入字符 '(' 和 ')' 使字符串平衡。
 * 请你返回让 s 平衡的最少插入次数。
 */
public class BalanceParentheses {
    public int minInsertions(String s) {
        int rightCount = 0;
        int res = 0;

        // 不要从后往前遍历，对于这种case无法解决 s = "))())("
        for (int i = 0; i < s.length()-1; i++) {
            char c = s.charAt(i);
            if (c == '(') {
                // 一个左括号需要2个右括号来匹配
                rightCount += 2;
                if (rightCount % 2 == 1) {
                    // 需要右括号的个数是奇数，多了奇数个右括号/少了奇数个右括号
                    res++; // 加上一个右括号
                    rightCount--; // 上面加上了一个右括号，那么这里需要有括号的个数就减少一个
                }
            } else {
                rightCount--;
                if (rightCount == -1) {
                    // 多出来一个右括号，那么需要一个左括号和一个右括号
                    res++;
                    rightCount = 1;
                }
            }
        }

        return res + rightCount;
    }
}
