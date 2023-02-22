/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-16 18:43:20
 * @LastEditTime: 2023-02-15 16:50:05
 */
package stack_and_queue.usage;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode 239 hard 滑动窗口最大值
 * 
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 
 * 返回 滑动窗口中的最大值 。
 */
public class WindowMax {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n - k + 1];
        int idx = 0;

        MonotonousQueue q = new MonotonousQueue();

        for (int i = 0; i < k - 1; i++) {
            q.push(nums[i]);
        }

        for (int i = k - 1; i < n; i++) {
            if (i > k - 1) {
                q.pop(nums[i - k]);
            }
            q.push(nums[i]);
            ans[idx++] = q.max();
        }

        return ans;
    }
}

class MonotonousQueue {
    private Deque<Integer> queue;

    public MonotonousQueue() {
        queue = new ArrayDeque<>();
    }

    public void push(int x) {
        while (!queue.isEmpty() && queue.getFirst() < x) {
            queue.removeFirst();
        }
        queue.addFirst(x);
    }

    public int max() {
        return queue.getLast();
    }

    public void pop(int x) {
        if (queue.getLast() == x) {
            queue.removeLast();
        }
    }
}