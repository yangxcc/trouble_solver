package skill;

import java.util.*;

/**
 * 前缀、中缀、后缀表达式求值
 */
public class ExpressionToVal {
    /**
     * 后缀表达式求值，最基本的了，后缀表达式又称逆波兰表达式
     * leetcode 150 middle 逆波兰表达式求值 https://leetcode.cn/problems/evaluate-reverse-polish-notation/
     *
     * @param tokens ["2","1","+","3","*"]
     * @return 后缀表达式的值
     * <p>
     * NOTE：前缀表达式的求值思路和后缀表达式一样，只是后缀表达式是从左往右，而前缀表达式的计算是从右往左，里面都是num1-num2
     */
    public static int suffixCalculate(String[] tokens) {
        Deque<Integer> stack = new LinkedList<>();
        // 数字入栈，符号出栈
        for (String token : tokens) {
            if (!isOp(token)) {
                stack.addLast(Integer.parseInt(token));
            } else {
                // 是符号，出栈两个元素
                int num1 = stack.removeLast();
                int num2 = stack.removeLast();
                switch (token) {
                    case "+":
                        stack.offer(num1 + num2);
                        break;
                    case "-":
                        stack.offer(num2 - num1);
                        break;
                    case "*":
                        stack.offer(num1 * num2);
                        break;
                    case "/":
                        stack.offer(num2 / num1);
                        break;
                }
            }
        }

        return stack.removeLast();
    }


    /**
     * 中缀表达式求值，例如5/(1−4)，中缀表达式就是我们日常使用到的
     * <p>
     * 思路是：将中缀表达式转换成后缀表达式，然后求值
     */
    public static int infixCalculate(String infix) {
        List<String> suffix = infixToSuffix(infix);
        System.out.println(Arrays.toString(suffix.toArray(new String[0])));
        return suffixCalculate(suffix.toArray(new String[0]));
    }

    // 给定的是一个中缀字符串
    public static List<String> infixToSuffix(String infix) {
        Deque<String> symbolStack = new ArrayDeque<>();   // 符号栈
        Deque<String> tempStack = new ArrayDeque<>();     // 中间过程栈
        for (int i = 0; i < infix.length(); ) {
            char ch = infix.charAt(i);
            if (Character.isDigit(ch)) {
                StringBuilder sb = new StringBuilder();
                while (i < infix.length() && Character.isDigit(infix.charAt(i))) {
                    sb.append(ch);
                    i++;
                }
                tempStack.addLast(sb.toString());
            } else if (ch == '(') {
                symbolStack.addLast(String.valueOf(ch));
                i++;
            } else if (ch == ')') {
                while (!symbolStack.peekLast().equals("(")) {
                    tempStack.addLast(symbolStack.removeLast());
                }
                symbolStack.removeLast();
                i++;
            } else {
                // 不是空格，那就是加减乘除了
                while (!symbolStack.isEmpty() && priority(String.valueOf(ch)) <= priority(symbolStack.peekLast())) {
                    tempStack.addLast(symbolStack.pollLast());
                }
                symbolStack.addLast(String.valueOf(ch));
                i++;
            }
        }

        List<String> ans = new ArrayList<>();
        while (!symbolStack.isEmpty()) {
            tempStack.addLast(symbolStack.pollLast());
        }
        while (!tempStack.isEmpty()) {
            String s = tempStack.removeFirst();
            ans.add(s);
        }
        return ans;
    }

    public static boolean isOp(String token) {
        return "+".equals(token) || "-".equals(token) || "*".equals(token) || "/".equals(token);
    }

    // 定义一个方法比较优先级
    public static int priority(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = 1;
                break;
            case "-":
                result = 1;
                break;
            case "*":
                result = 2;
                break;
            case "/":
                result = 2;
                break;
        }
        return result;
    }


    public static void main(String[] args) {
//        String s = "1+((2+3)*4)-5";
//        System.out.println(Arrays.toString(infixToSuffix(s).toArray(new String[0])));
//        System.out.println(suffixCalculate(infixToSuffix(s).toArray(new String[0])));

        String s1 = "(F&F|V|!V&!F&!(F|F&V))";
        System.out.println(Arrays.toString(convert(s1).toArray(new Character[0])));
        System.out.println(calculate2(convert(s1)));
    }


    // 将中缀表达式转化成后缀表达式，给定的是一个中缀数组
    public static List<String> parseSuffixExpressionList(List<String> list) {
        Stack<String> s1 = new Stack<>();  // 符号栈
        List<String> s2 = new ArrayList<>();  // 存放中间结果
        for (String item : list) {
            if (item.matches("\\d+")) {   // 是一个数字
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();  // 把（ 也弹出去，别忘了！！！
            } else {            // 操作符,需要比较优先级
                while (s1.size() != 0 && priority(s1.peek()) >= priority(item)) {    // s1栈顶运算符的优先级大于等于当前运算符
                    s2.add(s1.pop());
                }
                s1.push(item);
            }
        }
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;    // 不需要再逆序输出了，因为是存放在list中了，桉顺序输出即可
    }


    // boolean类型的
    private static List<Character> convert(String infix) {
        Deque<Character> opStack = new LinkedList<>();
        Deque<Character> tmpStack = new LinkedList<>();

        for (char ch : infix.toCharArray()) {
            // 去掉空格
            if (ch == ' ') {
                continue;
            }
            if (ch == 'V' || ch == 'F') {
                tmpStack.addLast(ch);
            } else if (ch == '(') {
                opStack.addLast(ch);
            } else if (ch == ')') {
                while (!opStack.isEmpty() && opStack.getLast() != '(') {
                    tmpStack.addLast(opStack.removeLast());
                }
                opStack.removeLast();
            } else {
                // 其他的一些运算符了
                while (!opStack.isEmpty() && priority2(ch) <= priority2(opStack.getLast())) {
                    tmpStack.addLast(opStack.removeLast());
                }
                opStack.addLast(ch);
            }
        }

        while (!opStack.isEmpty()) {
            tmpStack.addLast(opStack.removeLast());
        }
        List<Character> ans = new ArrayList<>();
        while (!tmpStack.isEmpty()) {
            ans.add(tmpStack.removeFirst());
        }
        return ans;
    }

    private static int priority2(char op) {
        if ('!' == op) {
            return 3;
        }
        if ('&' == op) {
            return 2;
        }
        if ('|' == op) {
            return 1;
        }
        return -1;
    }

    private static int calculate2(List<Character> suffix) {
        Deque<Integer> stack = new LinkedList<>();
        for (char ch : suffix) {
            if (ch == 'V' || ch == 'F') {
                stack.addLast(convertBool2Int(ch));
            } else if (ch == '!') {
                // 只弹出一个数
                Integer num = stack.removeLast();
                if (num.equals(1)) {
                    stack.addLast(0);
                } else {
                    stack.addLast(1);
                }
            } else {
                // 弹出两个数
                Integer num1 = stack.removeLast();
                Integer num2 = stack.removeLast();
                if (ch == '|') {
                    stack.addLast(num1 | num2);
                } else if (ch == '&') {
                    stack.addLast(num1 & num2);
                }
            }
        }

        return stack.pop();
    }

    private static int convertBool2Int(Character ch) {
        return ch == 'V' ? 1 : 0;
    }
}
