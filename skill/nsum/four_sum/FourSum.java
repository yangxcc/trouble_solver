/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-09 20:05:27
 * @LastEditTime: 2023-02-12 10:30:02
 */
package skill.nsum.four_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;;

/**
 * leetcode 18  middle 四数之和
 * 
 * 给你一个由 n 个整数组成的数组 nums ，和一个目标值 target 。
 * 请你找出并返回满足下述全部条件且不重复的四元组 [nums[a], nums[b], nums[c], nums[d]] 
 * （若两个四元组元素一一对应，则认为两个四元组重复）：
 *  0 <= a, b, c, d < n
 *  a、b、c 和 d 互不相同
 *  nums[a] + nums[b] + nums[c] + nums[d] == target
 * 
 * 你可以按 任意顺序 返回答案 。
 */
public class FourSum {
    /**
     * 按照三数之和的思路，四数之和也就是多了一层循环
     */
    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        int n = nums.length;

        for (int i = 0; i < n - 3; i++) {
            // 右侧至少剩下3个数
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }

            for (int j = i + 1; j < n - 2; j++) {
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                
                int left = j + 1;  // 第三个数
                int right = n - 1; // 第四个数

                while (left < right) {
                    long sum = (long) nums[left] + nums[right] + nums[i] + nums[j];  // 不要忘记(long)，而且不能是(long) (nums[left] + nums[right] + nums[i] + nums[j])

                    if (sum == target) {
                        List<Integer> tmp = new ArrayList<>();
                        tmp.add(nums[i]);
                        tmp.add(nums[j]);
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
            }
        }
        return ans;
    }
}
