/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-17 16:18:48
 * @LastEditTime: 2023-02-18 15:37:36
 */
package skill.jump_game;

/**
 * leetcode 55 middle 跳跃游戏
 * 
 * 给定一个非负整数数组 nums ，你最初位于数组的 第一个下标。
 * 数组中的每个元素代表你在该位置可以跳跃的最大长度。
 * 判断你是否能够到达最后一个下标。
 */
public class JumpGame1 {
    // 错误的写法，bad case [3,2,1,0,4]
    @Deprecated
    public boolean canJump(int[] nums) {
        int n = nums.length;
        boolean[] canReach = new boolean[n];
        
        for (int i = 0; i < nums.length; i++) {
            if (i + nums[i] >= n) {
                return true;
            }
            for (int j = i; j < i + nums[i]; j++) {
                canReach[j] = true;
            }
        }

        return canReach[n - 1];
    }

    public boolean canJumpRightWay(int[] nums) {
        int n = nums.length;
        int maxReach = 0;

        for (int i = 0; i < nums.length; i++) {
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= n - 1) {
                return true;
            }
        }

        return false;
    }
}
