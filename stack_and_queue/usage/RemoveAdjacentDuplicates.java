package stack_and_queue.usage;

import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 1047 simple 删除字符串中的所有相邻重复项
 * 
 * 给出由小写字母组成的字符串 S，重复项删除操作会选择两个相邻且相同的字母，并删除它们。
 * 在 S 上反复执行重复项删除操作，直到无法继续删除。
 * 在完成所有重复项删除操作后返回最终的字符串。答案保证唯一。
 * 
 * 输入："abbaca"
 * 输出："ca"
 */
public class RemoveAdjacentDuplicates {
    public String removeDuplicates(String s) {
        char[] chs = s.toCharArray();
        // 使用栈
        LinkedList<Character> stack = new LinkedList<>();
        for (char ch : chs) {
            if (stack.isEmpty()) {
                stack.addLast(ch);
            } else {
                if (stack.getLast() == ch) {
                    stack.removeLast();
                } else {
                    stack.addLast(ch);
                }
            }
        }

        StringBuilder ans = new StringBuilder();
        while (!stack.isEmpty()) {
            ans.append(stack.removeFirst());
        }

        return ans.toString();
    }
}
