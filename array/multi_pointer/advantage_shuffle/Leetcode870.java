/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-07 20:13:05
 * @LastEditTime: 2023-01-07 21:50:53
 */
package array.multi_pointer.advantage_shuffle;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

import subsequence_subarray.maximum_subarray.maxSubarray;

/**
 * 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i] 的索引 i 的数目来描述。
 * 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 */
public class Leetcode870{
    /**
     * 这种方法不行，因为下面这种做法不能够排除元素，即可能会重复选择
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] advantageCount(int[] nums1, int[] nums2) {
        int n = nums1.length;
        int[] res = new int[n];
        int minVal = Integer.MAX_VALUE, flag = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            // 从nums1中找出一个最接近nums2[i]且大于这个数的值，如果没有比这个数大的，就选最小的
            for (int j = 0; j < n; j++) {
                minVal = Math.min(minVal, nums1[j]);
                if (nums1[j] > nums2[i] && nums1[j] < flag) {
                    flag = nums1[j];
                }
            }

            if (flag == Integer.MAX_VALUE) {
                res[i] = minVal;
            } else {
                res[i] = flag;
            }
            minVal = Integer.MAX_VALUE;
            flag = Integer.MAX_VALUE;
        }

        return res;
    }


    public int[] advantageCount2(int[] nums1, int[] nums2) {
        int[] res = new int[nums1.length];
        Arrays.sort(nums1);

        // 降序排列
        PriorityQueue<int[]> q = new PriorityQueue<>(
            (int[] pair1, int[] pair2) -> {
                return pair2[1] - pair1[2];
            }
        );

        for (int i = 0; i < nums2.length; i++) {
            q.add(new int[]{i, nums2[i]});
        }

        int left = 0, right = nums1.length - 1;
        while (!q.isEmpty()) {
            int[] pairs = q.poll();
            int idx = pairs[0], val = pairs[1];
            if (nums1[right] <= val) {
                res[idx] = nums1[left];
                left++;
            } else {
                res[idx] = nums1[right];
                right--;
            }
        }

        return res;
    }
}