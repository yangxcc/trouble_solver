/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-09 16:26:07
 * @LastEditTime: 2023-02-11 20:13:29
 */
package hashmap.sum_of_multi_numbers.two_sum;

import java.util.HashMap;

public class TwoSum {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> helper = new HashMap<>();
        int[] ans = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (helper.containsKey(nums[i])) {
                return new int[] { i, helper.get(nums[i]) };
            }
            helper.put(target - nums[i], i);
        }

        // for (int i = 0; i < nums.length; i++) {
        // if (helper.containsKey(nums[i])) {
        // ans[0] = i;
        // ans[1] = helper.get(nums[i]);
        // }
        // }

        return ans;
    }
}