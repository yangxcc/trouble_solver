/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-14 22:33:49
 * @LastEditTime: 2023-03-02 21:00:32
 */
package stack_and_queue.parentheses;

/**
 * leetcode 921 middle 使括号有效的最少添加
 * 
 * 只有满足下面几点之一，括号字符串才是有效的：
 * 它是一个空字符串，或者
 * 它可以被写成 AB （A 与 B 连接）, 其中 A 和 B 都是有效字符串，或者
 * 它可以被写作 (A)，其中 A 是有效字符串。
 * 给定一个括号字符串 s ，在每一次操作中，你都可以在字符串的任何位置插入一个括号
 * 例如，如果 s = "()))" ，你可以插入一个开始括号为 "(()))" 或结束括号为 "())))"
 * 。但是这样不是有效的啊！不要被这个例子误导，只有（）这样才是有效
 * 返回 为使结果字符串 s 有效而必须添加的最少括号数。
 * 
 */
public class MinimumAdd {
    // 双指针直接搞定，其实就是判断左括号和右括号的差值？
    // 非也，比如bad case ()))((，我们在统计左右括号个数的时候需要考虑到顺序
    public int wrong(String s) {
        int rightCount = 0, leftCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                leftCount++;
            } else {
                rightCount++;
            }
        }
        return Math.abs(rightCount - leftCount);

    }

    public int minAddToMakeValid(String s) {
        // 两个变量的含义分别是需要右括号的个数和需要左括号的个数
        int rightCount = 0, leftCount = 0;

        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                rightCount++;
            } else {
                rightCount--;
                if (rightCount == -1) {
                    leftCount++;
                    // 一定不要忘记把rightCount置为0
                    rightCount = 0;
                }
            }
        }
        return rightCount + leftCount;
    }
}
