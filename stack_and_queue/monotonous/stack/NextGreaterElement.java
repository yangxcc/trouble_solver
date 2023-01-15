/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 10:03:29
 * @LastEditTime: 2023-01-15 11:36:11
 */
package stack_and_queue.monotonous.stack;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * leetcode 496 simple 下一个更大的元素I
 * 
 * nums1 中数字 x 的 下一个更大元素 是指 x 在 nums2 中对应位置 右侧 的 第一个 比 x 大的元素。
 * 给你两个 没有重复元素 的数组 nums1 和 nums2 ，下标从 0 开始计数，其中nums1 是 nums2 的子集。
 * 对于每个 0 <= i < nums1.length ，找出满足 nums1[i] == nums2[j] 的下标 j ，并且在 nums2 确定 nums2[j] 的 下一个更大元素。
 * 如果不存在下一个更大元素，那么本次查询的答案是 -1 。
 * 返回一个长度为 nums1.length 的数组 ans 作为答案，满足 ans[i] 是如上所述的 下一个更大元素 。
 */
public class NextGreaterElement {
    // 暴力算法，时间复杂度O(n^2)，外加一个hashmap
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // 给nums2维护一个单调栈，得到nextGreaterElement的数组，然后根据nums1中的下标得到ans
        int n = nums1.length, m = nums2.length;
        int[] ans = new int[n], nextGreater = new int[m];
        Deque<Integer> stack = new LinkedList<>();
        // 记录nums1中的元素及其下标，题目中有要求：nums1和nums2中所有整数 互不相同，所以能直接用哈希表来记录
        HashMap<Integer, Integer> memo = new HashMap<>();

        // for (int i = 0; i < m; i++) {
        //     memo.put(nums2[i], i);
        // }

        for (int i = m-1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peekFirst() <= nums2[i]) {
                stack.pop();
            }

            nextGreater[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(nums2[i]);
            memo.put(nums2[i], i);
        }

        for (int i = 0; i < n; i++) {
            ans[i] = nextGreater[memo.get(nums1[i])];
        }

        return ans;
    }
}