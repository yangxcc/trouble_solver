/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 11:43:45
 * @LastEditTime: 2023-01-15 16:49:04
 */
package stack_and_queue.monotonous.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * leetcode 503 middle 下一个更大的元素II
 * 
 * 给定一个循环数组 nums （ nums[nums.length - 1] 的下一个元素是 nums[0] ），返回 nums 中每个元素的 下一个更大元素 。
 * 数字 x 的 下一个更大的元素 是按数组遍历顺序，这个数字之后的第一个比它更大的数，
 * 这意味着你应该循环地搜索它的下一个更大的数。如果不存在，则输出 -1 。
 */
public class NextGreaterElement2 {
    public int[] nextGreaterElements(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new LinkedList<>();
        
        // 相当于弄了两遍数组入栈
        for (int i = 2*n-1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peek() <= nums[i % n]) {
                stack.pop();
            }

            ans[i % n] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(nums[i % n]);
        }

        return ans;
    }
}
