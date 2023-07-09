/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-12 10:44:32
 * @LastEditTime: 2023-07-09 17:31:40
 */
package skill.nsum;

import java.util.ArrayList;
import java.util.List;

/**
 * 不管是两数之和，还是三数之和，甚至是100数之和，都能够总结出一套模板来
 * 
 * 可以根据题目中给出的数值范围，判断n数之和是否越界
 */
public class Frame {
    /**
     * 使用这种方式使用Java会造成越界，但是不知道应该怎么修改，使用Go不会
     * @param nums   已经排好序的数组 Arrays.sort(nums)
     * @param n      n数之和，n==2,两数之和，n==3，三数之和...
     * @param target
     * @param start
     * @return
     */
    public List<List<Integer>> sum(int[] nums, int n, int target, int start) {
        int size = nums.length;
        if (n < 2 || size < n) {
            return new ArrayList<>();
        }

        List<List<Integer>> ans = new ArrayList<>();

        if (n == 2) {
            // 两数之和作为base case
            int left = start, right = size - 1;
            while (left < right) {
                int sum = nums[left] + nums[right];
                if (sum == target) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(nums[left]);
                    tmp.add(nums[right]);
                    ans.add(new ArrayList<>(tmp));

                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }

                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }
        } else {
            // n > 2，3数之和，或以上
            for (int i = start; i < size; i++) {
                // (n-1)sum
                List<List<Integer>> sub = sum(nums, n - 1, target - nums[i], i + 1);
                for (List<Integer> eachEle : sub) {
                    eachEle.add(nums[i]);
                    ans.add(new ArrayList<>(eachEle));
                }

                // 去重
                while (i < size - 1 && nums[i] == nums[i + 1]) {
                    i++;
                }
            }
        }

        return ans;
    }
}
