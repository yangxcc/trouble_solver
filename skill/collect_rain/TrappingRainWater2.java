/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-16 15:00:24
 * @LastEditTime: 2023-02-16 15:10:53
 */
package skill.collect_rain;

/**
 * 这个题其实就是接雨水问题，只不过这道题里面的柱子是没有宽度的
 * 这是让求哪两个杆之间的水是最多的，不是一共能成多少水
 *
 * leetcode 11 middle 盛最多水的容器
 * 
 * 给定一个长度为 n 的整数数组 height 。有 n 条垂线，第 i 条线的两个端点是 (i, 0) 和 (i, height[i]) 。
 * 找出其中的两条线，使得它们与 x 轴共同构成的容器可以容纳最多的水。
 * 返回容器可以储存的最大水量。
 * 说明：你不能倾斜容器
 */

public class TrappingRainWater2 {
    public int maxArea(int[] height) {
        int left = 0, right = height.length - 1;
        int ans = 0;

        // 两根柱子之间能盛水的最大容量至于两根柱子之间的距离和柱子的高度有关系
        while (left < right) {
            ans = Math.max(ans, Math.min(height[left], height[right]) * (right - left));
            // 下一步肯定是缩小距离，所以一定要提高下界
            if (height[left] < height[right]) {
                left++;
            } else {
                right--;
            }
        }

        return ans;
    }
}
