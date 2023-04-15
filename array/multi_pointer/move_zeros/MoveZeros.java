/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-31 21:24:08
 * @LastEditTime: 2023-04-15 16:55:20
 */
package array.multi_pointer.move_zeros;


/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
public class MoveZeros {
    public void moveZeroes(int[] nums) {
        if (nums.length <= 1) {
            return;
        }
        int slow = 0, fast = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        for (int i = slow; i <= nums.length-1; i++) {
            nums[i] = 0;
        }
    }
}


/**
 * 给定一个数组 nums，编写一个函数将所有 0 移动到数组的末尾，同时保持非零元素的相对顺序。
 * 请注意 ，必须在不复制数组的情况下原地对数组进行操作。
 */
class day0415 {
    public void moveZeroes(int[] nums) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        // slow及其之后需要设置为0
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }
}