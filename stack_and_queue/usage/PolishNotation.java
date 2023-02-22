/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-16 16:31:50
 * @LastEditTime: 2023-02-15 16:27:08
 */
package stack_and_queue.usage;

import java.util.LinkedList;

/**
 * leetcode 150 middle 逆波兰表达式求值
 * 
 */
public class PolishNotation {
    public int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();

        for (String t : tokens) {
            if (isDigit(t)) {
                stack.addLast(Integer.parseInt(t));
            } else {
                int a = stack.removeLast();
                int b = stack.removeLast();
                switch (t) {
                    case "+":
                        stack.addLast(a + b);
                        break;
                    case "-":
                        stack.addLast(b - a);
                        break;    
                    case "*":
                        stack.addLast(a * b);
                        break;
                    case "/":
                        stack.addLast(b / a);
                        break;
                }
            }
        }

        return stack.pop();
    }


    private boolean isDigit(String x) {
        if (x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")) {
            return false;
        }

        return true;
    }
}
