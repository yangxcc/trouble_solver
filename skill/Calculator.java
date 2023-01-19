/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-18 15:54:13
 * @LastEditTime: 2023-01-19 10:57:30
 */
package skill;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 计算器的实现
 * leetcode 224 hard 基本计算器
 * leetcode 227 middle 基本计算器II
 * leetcode 772 hard 基本计算器III
 */
public class Calculator {
    public int calculate(String s) {
        Deque<Character> stack = new LinkedList<>();
        for (char ch : s.toCharArray()) {
            stack.addLast(ch);
        }
        
        return calculateHelper(stack);
    }

    public int calculateHelper(Deque<Character> stack) {
        char sign = '+';
        int num = 0;
        Deque<Integer> numStack = new LinkedList<>();

        while (stack.size() > 0) {
            char ch = stack.pollFirst();
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            if (ch == '(') {
                // 这里不能直接返回，括号相当于迭代，计算出一个括号里面的值，后续加入到栈中
                num = calculateHelper(stack);
            }
            
            if ((!Character.isDigit(ch) && ch != ' ') || stack.size() == 0) {
                int pre;
                switch (sign) {
                    case '+':
                        numStack.addLast(num);
                        break;
                    case '-':
                        numStack.addLast(-num);
                        break;
                    case '*':
                        pre = numStack.pollLast();
                        numStack.addLast(pre*num);
                        break;
                    case '/':
                        pre = numStack.pollLast();
                        numStack.addLast(pre/num);
                        break; 
                }
                sign = ch;
                num = 0;
            }

            if (ch == ')') {
                break;
            }
        }

        return sum(numStack);
    }

    public int sum(Deque<Integer> numStack) {
        int sum = 0;
        while (!numStack.isEmpty()) {
            sum += numStack.pop();
        }

        return sum;
    }
}