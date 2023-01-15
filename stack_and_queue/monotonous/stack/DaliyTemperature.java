/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 16:54:50
 * @LastEditTime: 2023-01-15 17:04:05
 */
package stack_and_queue.monotonous.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 793 middle 每日温度
 * 
 * 给定一个整数数组 temperatures ，表示每天的温度，返回一个数组 answer ，
 * 其中 answer[i] 是指对于第 i 天，下一个更高温度出现在几天后。如果气温在这之后都不会升高，请在该位置用 0 来代替。
 */
public class DaliyTemperature {
    public int[] dailyTemperatures(int[] temperatures) {
        int n = temperatures.length;
        int[] ans = new int[n];
        Deque<Pair> stack = new LinkedList<>();

        for (int i = n-1; i >= 0; i--) {
            Pair p = new Pair(i, temperatures[i]);
            while (!stack.isEmpty() && stack.peekFirst().temperature <= temperatures[i]) {
                stack.pop();
            }

            ans[i] = stack.isEmpty() ? 0 : stack.peekFirst().idx - i;

            stack.push(p);
        }

        return ans;
    }

    public int[] dailyTemperaturesWithoutPair(int[] temperatures) {
        int n = temperatures.length;
        int[] res = new int[n];  // 注意里面存的是索引
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[stack.peekLast()] <= temperatures[i]) {
                stack.pollLast();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peekLast() - i;
            stack.addLast(i);
        }
        return res;
    }
}

class Pair {
    int idx;
    int temperature;

    public Pair(int idx, int temperature) {
        this.idx = idx;
        this.temperature = temperature;
    }
}
