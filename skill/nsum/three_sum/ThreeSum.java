/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-09 16:41:47
 * @LastEditTime: 2023-03-07 13:56:00
 */
package skill.nsum.three_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * leetcode 15 middle 三数之和
 * 
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 */
public class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length <= 2) {
            return new ArrayList<>();
        }
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);

        for (int k = nums.length - 1; k >= 2; k--) {
            if (k + 1 < nums.length && nums[k] == nums[k + 1]) {  // 这里得是if
                continue;
            }
            for (int i = 0, j = k - 1; i < j;) {
                if (nums[i] + nums[j] == -nums[k]) {
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    res.add(temp);
                    while (i < j && nums[i] == nums[i + 1]) {
                        i++;
                    }
                    while (i < j && nums[j] == nums[j - 1]) {
                        j--;
                    }
                    i++;
                    j--;
                } else if (nums[i] + nums[j] < -nums[k]) {
                    i++;
                } else {
                    j--;
                }
            }
        }
        return res;
    }
}


/**
 * leetcode 15 middle 三数之和
 * 
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 
 * 满足 i != j、i != k 且 j != k ，
 * 同时还满足 nums[i] + nums[j] + nums[k] == 0 。请你返回所有和为 0 且不重复的三元组。
 * 注意：答案中不可以包含重复的三元组。
 */
class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        for (List<Integer> tuple : process(arr)) {
            System.out.println(tuple.toString());
        }

    }

    public static List<List<Integer>> process(int[] arr) {
        Arrays.sort(arr);

        int n = arr.length;
        List<List<Integer>> ans = new ArrayList<>();

        for (int i = n - 1; i >= 2; i--) {
            if (i + 1 < n && arr[i] == arr[i + 1]) {
                continue;
            }
            int left = 0, right = i - 1;
            while (left < right) {
                if (arr[left] + arr[right] == -arr[i]) {
                    List<Integer> tmp = new ArrayList<>();
                    tmp.add(arr[i]);
                    tmp.add(arr[left]);
                    tmp.add(arr[right]);
                    ans.add(new ArrayList<>(tmp));

                    while (left < right && arr[left] == arr[left + 1]) {
                        left++;
                    }

                    while (left < right && arr[right] == arr[right] - 1) {
                        right--;
                    }

                    left++;
                    right--;
                } else if (arr[left] + arr[right] < -arr[i]) {
                    left++;
                } else {
                    right--;
                }
            }
        }

        return new ArrayList<>();
    }
}