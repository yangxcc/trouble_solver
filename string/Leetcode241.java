package string;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 241 middle 为运算表达式设置优先级
 * 
 * 给你一个由数字和运算符组成的字符串 expression ，按不同优先级组合数字和运算符，
 * 计算并返回所有可能组合的结果。你可以 按任意顺序 返回答案。
 * 
 * 生成的测试用例满足其对应输出值符合 32 位整数范围，不同结果的数量不超过 104 。
 */
public class Leetcode241 {
    /**
     * 针对每个运算符进行分治，假设一段表达式中只能够加入一对括号，能怎么加？
     * 1 + 2 * 3 - 4 / 5，加括号的方式为
     * (1) + (2 * 3 - 4 / 5)
     * (1 + 2) * (3- 4 / 5)
     * (1 + 2 * 3) - (4 / 5)
     * (1 + 2 * 3 - 4) / (5)
     * 可以看到括号的划分是以运算符分界的
     * 
     * @param expression 由数字和算符 '+'、'-' 和 '*' 组成。
     * @return 计算出表达式在不同划分情况下的不同值
     */
    public List<Integer> diffWaysToCompute(String expression) {
        List<Integer> ans = new ArrayList<>();
        // base case，这样不行，比如这个表达式只有一个符号
        // if (expression.length() == 1) {
        //     ans.add((int) expression.charAt(0));
        //     return ans;
        // }
        for (int i = 0; i < expression.length(); i++) {
            char ch = expression.charAt(i);
            if (ch == '+' || ch == '-' || ch == '*') {
                List<Integer> firstHalf = diffWaysToCompute(expression.substring(0, i));
                List<Integer> secondHalf = diffWaysToCompute(expression.substring(i + 1));

                for (int f : firstHalf) {
                    for (int s : secondHalf) {
                        switch (ch) {
                            case '+':
                                ans.add(f + s);
                                break;
                            case '-':
                                ans.add(f - s);
                                break;
                            case '*':
                                ans.add(f * s);
                                break;
                        }
                    }
                }
            }
        }

        if (ans.isEmpty()) {
            // 碰到了一个数字
            ans.add(Integer.parseInt(expression));
        }

        return ans;
    }
}
