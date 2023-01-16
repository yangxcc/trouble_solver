/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-16 09:35:23
 * @LastEditTime: 2023-01-16 09:40:30
 */
package stack_and_queue.monotonous.queue;

public class SlidingWindowMaximum {
     /**
     * leetcode 239 hard 滑动窗口的最大值
     * 
     * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
     * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] ans = new int[n-k+1];
        Template monotonousQueue = new Template();
        for (int i = 0; i < nums.length; i++) {
            if (i < k-1) {
                monotonousQueue.push(nums[i]);
            } else {
                monotonousQueue.push(nums[i]);
                ans[i+1-k] = monotonousQueue.max();
                monotonousQueue.pop(nums[i+1-k]);
            }
        }

        return ans;
    }   
}
