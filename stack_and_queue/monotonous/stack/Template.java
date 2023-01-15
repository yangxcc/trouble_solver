/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-15 11:00:15
 * @LastEditTime: 2023-01-15 11:20:18
 */
package stack_and_queue.monotonous.stack;

import java.util.Deque;
import java.util.LinkedList;

/**
 * 单调栈的模板
 * 
 * 单调栈能够解决的问题很有限，⽐如「下⼀个更⼤元素」，「上⼀个更⼩元素」等。
 */
public class Template {
    /**
     * 输⼊⼀个数组 nums，请你返回⼀个等⻓的结果数组，结果数组中对应索引存储着下⼀个更⼤元素，
     * 如果没有更⼤的元素，就存 -1。
     * @param nums
     * @return
     */
    public int[] MonotonousStackTemplate(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        Deque<Integer> stack = new LinkedList<>();

        /**
         * 单调栈实现过程的注意点：
         * 1. 倒序遍历数组，因为栈的性质是先进后出，倒着入栈，出栈的时候是正着的，保证了是最近的一个大的数
         * 2. 实现过程中可以按照大小个的排序来想，保证栈中元素始终是从小到大的（栈顶到栈底）
         * 3. 对于单调栈算法时间复杂度的分析，乍一看有一个for，一个while很唬人，但实际上他的时间复杂度是O(n)
         *    因为从整体上看，数组中共有n个元素，这些元素的操作是每个元素都入栈，元素最多再执行一次出栈，出战入栈都是O(1)的，
         *    所以整体时间复杂度为O(n)
         */
        for (int i = n-1; i >= 0; i--) {
            while(!stack.isEmpty() && stack.peekFirst() <= nums[i]) {
                stack.pop();
            }

            ans[i] = stack.isEmpty() ? -1 : stack.peek();

            stack.push(nums[i]);
        }

        return ans;
    }
}
