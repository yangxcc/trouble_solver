/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-10 16:44:00
 * @LastEditTime: 2023-03-06 21:13:02
 */
package skill;

/**
 * leetcode 654 simple 错误的集合
 * 
 * 集合 s 包含从 1 到 n 的整数。不幸的是，因为数据错误，
 * 导致集合里面某一个数字复制了成了集合里面的另外一个数字的值，导致集合 丢失了一个数字 并且 有一个数字重复 。
 * 
 * 找到这个数组中重复和缺失的元素
 */
public class FindRepeatAndMissElement {
    /**
     * 常规做法，通过一个辅助数据结构HashMap或者数组来解决问题，时间复杂度O(n)，空间复杂度O(n)
     * @param nums
     * @return
     */
    public int[] findErrorNums(int[] nums) {
        int n = nums.length;
        int[] helper = new int[n + 1];
        int[] ans = new int[2];

        for (int i = 0; i < n; i++) {
            helper[nums[i]]++;
        }

        for (int i = 0; i < n; i++) {
            if (helper[i] == 0) {
                ans[0] = i;
            } else if (helper[i] != 1) {
                ans[1] = i;
            }
        }

        return ans;
    }

    /**
     * 用数组元素去映射索引，如果nums[nums[idx]] < 0则证明nums[idx]是重复的元素，如果不小于0，则将其置为负数
     * 很重要的一个思路，元素和索引之间的映射，比如leetcode 442 middle 数组中的重复， leetcode 448 simple 找到所有数组中消失的数字
     * @param nums，注意nums中的数是从1开始的
     * @return
     */
    public int[] findErrorNums2(int[] nums) {
        int n = nums.length;
        int dup = -1;
        for (int i = 0; i < n; i++) {
            int index = Math.abs(nums[i]) - 1;
            
            if (nums[index] < 0) {
                dup = Math.abs(nums[i]);
            } else {
                nums[index] *= -1;
            }
        }

        int miss = -1;
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                miss = i + 1;
                break;
            }
        }

        return new int[]{dup, miss};
    }

    /**
     * 如果仅仅是找1-n之间的一个重复2次的元素，我们通过异或的方式就能够得到
     * @param nums
     * @return
     */
    public int findRepeat(int[] nums) {
        int n = nums.length;
        int dup = 0;
        // 从外面先把n异或上
        dup ^= n;
        for (int i = 1; i <= n; i++) {
            dup ^= (i ^ nums[i - 1]);
        }

        return dup;
    }

    /**
     * 如果数组1-n仅仅缺失了一个元素，那么我们还可以通过等差数列的和与数组和的差值来计算出来
     */
}
