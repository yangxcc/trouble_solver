package array;

/**
 * 题目：
 *  数组中占比超过一半的元素称之为主要元素。给定一个整数数组，找到它的主要元素。若没有，返回-1。
 *
 *  示例 1：
 *  输入：[1,2,5,9,5,9,5,5,5]
 *  输出：5
 *
 *  示例 2：
 *  输入：[3,2]
 *  输出：-1
 *
 *  示例 3：
 *  输入：[2,2,1,1,1,2,2]
 *  输出：2
 *  说明：
 *  你有办法在时间复杂度为 O(N)，空间复杂度为 O(1) 内完成吗？
 *
 * 思路：
 * 使用投票算法，主要元素的对立面就是非主要元素，把它们看成一撮就可以了，就是肉搏法。
 */
public class NumberOverHalf {
    public static void main(String[] args) {
        int[] nums1 = {1,2,5,9,5,9,5,5,5};
        System.out.println(majorityElement(nums1));
    }

    // 其实如果不考虑时间复杂度的影响的话，因为题目中输入的数组是能够保证一定存在超过n/2的元素的
    // 所以可以直接通过排序，然后拿到nums[n/2]位置上的数
    private static int majorityElement(int[] nums) {
        int target = nums[0];
        int times = 1;
        for (int i = 1; i < nums.length; i++) {
            if (target != nums[i]) {
                times--;
            } else {
                times++;
            }

            if (times == 0) {
                // 说明前面的都已经抵消完了
                target = nums[i];
                times = 1;
            }
        }

        // 避免没有超过一半的元素
        int count = 0;
        for (int num : nums) {
            if (target == num) {
                count++;
            }

            if (count >= nums.length / 2 + 1) {
                return target;
            }
        }
        return -1;
    }
}
