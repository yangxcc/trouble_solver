/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-14 12:32:46
 * @LastEditTime: 2023-05-14 12:36:53
 */
package hydro;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Scanner;

/**
 * 题目来源：http://101.43.147.120/p/P1013
 * 题目描述：塔子哥拿到了两个栈，现在需要将两个栈里面的元素合并为一个n排列。(也就是1~n各出现一次)
 * 现在塔子哥允许有两个操作:
 * 1.选择其中一个栈并弹出栈顶。但是被弹出的元素必须要是两个栈之间的最小值
 * 2.将其中一个栈的栈顶元素弹出，之后放入另外一个栈的栈顶。
 * 
 * 求能够完成任务最小操作次数
 * 
 * 输入描述：第一行为n, sz1, sz2，其中n即为要组成的n排列大小，sz1，sz2为两个栈的大小，其中0<n<1000,sz1+sz2=n
 * 第二行sz1个整数d(0<d<=n)，表示第一个栈从栈底到栈顶的sz_1个元素
 * 第三行sz2 个整数d(0<d<=n)，表示第二个栈从栈底到栈顶的sz_2个元素
 * 
 * 输出描述：能够完成任务最小操作次数
 * 
 * AC
 */
public class Problem12 {
    public static void main(String args[]) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), sz1 = in.nextInt(), sz2 = in.nextInt();
        in.nextLine();
        Deque<Integer> stack1 = new ArrayDeque<>();
        Deque<Integer> stack2 = new ArrayDeque<>();
        HashSet<Integer> memo1 = new HashSet<>();
        HashSet<Integer> memo2 = new HashSet<>();

        for (int i = 0; i < sz1; i++) {
            int val = in.nextInt();
            stack1.addFirst(val);
            memo1.add(val);
        }
        in.nextLine();
        for (int j = 0; j < sz2; j++) {
            int val = in.nextInt();
            stack2.addFirst(val);
            memo2.add(val);
        }

        // 快速找到i在哪个栈中
        int idx = 1;
        int ans = 0;
        while (idx <= n) {
            if (memo1.contains(idx)) {
                while (stack1.getFirst() != idx) {
                    ans++;
                    int top = stack1.removeFirst();
                    stack2.addFirst(top);
                    memo1.remove(top);
                    memo2.add(top);
                }
                stack1.removeFirst();
                ans++;
            } else {
                while (stack2.getFirst() != idx) {
                    ans++;
                    int top = stack2.removeFirst();
                    stack1.addFirst(top);
                    memo2.remove(top);
                    memo1.add(top);
                }
                stack2.removeFirst();
                ans++;
            }
            idx++;
        }

        System.out.println(ans);
    }
}
