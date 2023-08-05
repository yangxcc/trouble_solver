package string;

import java.util.Collections;
import java.util.Deque;
import java.util.LinkedList;

public class DecodeString {
    public static String decodeString(String s) {
        Deque<String> stack = new LinkedList<>();
        int cur = 0;
        while (cur < s.length()) {
            char ch = s.charAt(cur);
            if (Character.isDigit(ch)) {
                // 可能存在多个位
                StringBuilder num = new StringBuilder();
                while (cur < s.length() && Character.isDigit(s.charAt(cur))) {
                    num.append(s.charAt(cur));
                    cur++;
                }
                stack.addLast(num.toString());
            } else if (Character.isLetter(ch) || ch == '[') {
                stack.addLast(String.valueOf(ch));
                cur++;
            } else {
                // 是右括号
                // 需要一直出栈，直到碰到左括号
//                StringBuilder sb = new StringBuilder();
//                while (!"[".equals(stack.getLast())) {
//                    sb.append(stack.removeLast());
//                }
//                sb.reverse();
                // 这里不能用字符串，需要用链表，因为把字符串反转之后，再次用到的时候还会反转，需要保证整体反转，比如jk和q，应该是qjk，而不是qkj
                LinkedList<String> sb = new LinkedList<>();
                while (!"[".equals(stack.getLast())) {
                    sb.addLast(stack.removeLast());
                }
                Collections.reverse(sb);
                StringBuilder str = new StringBuilder();
                for (String ele : sb) {
                    str.append(ele);
                }

                // 左括号出栈
                stack.removeLast();
                // 数字出栈
                int count = Integer.parseInt(stack.removeLast());
                StringBuilder tmp = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    tmp.append(str);
                }
                stack.addLast(tmp.toString());
                cur++; // 越过]
            }
        }
        // 此时stack中应该就是结果了，将stack转成string
        StringBuilder ans = new StringBuilder();
        for (String ele : stack) {
            ans.append(ele);
        }
        return ans.toString();
    }

    public static void main(String[] args) {
        String s = "3[z]2[2[y]pq4[2[jk]e1[f]]]ef";
        String ans = decodeString(s);
        System.out.println(ans);
    }
}