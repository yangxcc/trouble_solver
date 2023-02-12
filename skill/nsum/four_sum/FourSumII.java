package skill.nsum.four_sum;

import java.util.HashMap;

/**
 * leetcode 454 middle 四数相加2
 * 
 * 给你四个整数数组 nums1、nums2、nums3 和 nums4 ，数组长度都是 n ，请你计算有多少个元组 (i, j, k, l) 能满足：
 * 0 <= i, j, k, l < n
 * nums1[i] + nums2[j] + nums3[k] + nums4[l] == 0
 * 
 * 
 * 题目中的结果集是由下标组成的，因此不存在去重的问题
 */
public class FourSumII {
    public int fourSumCount(int[] nums1, int[] nums2, int[] nums3, int[] nums4) {
        HashMap<Integer, Integer> memo = new HashMap<>();
        int ans = 0;
        for (int i = 0; i < nums1.length; i++) {
            for (int j = 0; j < nums2.length; j++) {
                memo.put(nums1[i] + nums2[j], memo.getOrDefault(nums1[i] + nums2[j], 0) + 1);
            }
        }

        // 将四数之和转换成了两部分的二数之和
        for (int k = 0; k < nums3.length; k++) {
            for (int l = 0; l < nums4.length; l++) {
                ans += memo.getOrDefault(-nums3[k] - nums4[l], 0);
            }
        }

        return ans;
    }
}
