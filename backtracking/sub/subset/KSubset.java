/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-06 13:49:23
 * @LastEditTime: 2023-05-07 22:27:46
 */
package backtracking.sub.subset;

import java.util.Arrays;
import java.util.Scanner;

/**
 * leetcode 698 middle 划分为k个相等的子集
 * 
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 */
public class KSubset {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;
        // 可以想象成有k个桶
        // 用来记录数组中的元素是否被访问过了
        boolean[] visited = new boolean[n];

        return backtrack(nums, k, target, 0, visited);
    }


    public boolean backtrack(int[] nums, int bucketIdx, int target, int bucketCapcity, boolean[] visited) {
        if (bucketIdx == 0) {
            // 剩下桶的个数为0
            return true;
        }

        if (bucketCapcity == target) {
            return backtrack(nums, bucketIdx - 1, target, 0, visited);
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[nums[i]]) {
                continue;
            }
            // 题目中说了所有元素都大于0
            if (bucketCapcity + nums[i] > target) {
                return false;
            }

            bucketCapcity += nums[i];
            visited[i] = true;

            if (backtrack(nums, bucketIdx, target, bucketCapcity, visited)) {
                return true;
            }

            bucketCapcity -= nums[i];
            visited[i] = false;
        }

        return false;
    }
}

/**
 * leetcode 698 middle 划分为k个相等的子集
 * 
 * 给定一个整数数组  nums 和一个正整数 k，找出是否有可能把这个数组分成 k 个非空子集，其总和都相等。
 * 
 * 这道题得先确定是以桶的视角还是数字的视角，下面的思路在数字的视角，对于一个数字arr[idx]是否放入buckets[i]中
 * 之所以将数组倒序排列，是因为先放大的，比先放小的 的递归次序少
 */
class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int k = in.nextInt();
        int[] arr = new int[n];
        int sum = 0;

        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
            sum += arr[i];
        }

        if (sum % k != 0) {
            System.out.println(false);
            return;
        }

        int[] buckets = new int[k]; // k个桶
        Arrays.sort(arr);
        for (int i = 0, j = arr.length - 1; i < j; i++, j--) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
        System.out.println(backtrack(arr, sum / k, 0, buckets));

    }

    // idx表示的是第idx个数，不是桶，从数的角度来看
    private static boolean backtrack(int[] arr, int target, int idx, int[] buckets) {
        if (idx == arr.length) {
            for (int i = 0; i < buckets.length; i++) {
                if (buckets[i] != target) {
                    return false;
                }
            }

            return true;
        }

        for (int i = 0; i < buckets.length; i++) {
            if (buckets[i] + arr[idx] > target) {
                continue;
            }

            buckets[i] += arr[idx];

            if (backtrack(arr, target, idx + 1, buckets)) {
                return true;
            }

            buckets[idx] -= arr[i];
        }

        return false;
    }
}

/**
 * 再来一遍😂
 */
class day0507 {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0, n = nums.length;
        for (int num : nums) {
            sum += num;
        }

        if (sum % k != 0) {
            return false;
        }

        int target = sum / k;

        // 想象现在有k个桶，我们需要用数组中的元素把这k个桶填满
        boolean[] visited = new boolean[n];

        return backtrack(nums, k, target, 0, visited);
    }

    private boolean backtrack(int[] nums, int restBuckets, int target, int curBucketCapcity, boolean[] visited) {
        if (restBuckets == 0) {
            return true;
        }

        if (curBucketCapcity == target) {
            return backtrack(nums, restBuckets - 1, target, 0, visited);
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }

            if (curBucketCapcity + nums[i] > target) {
                return false;
            }

            curBucketCapcity += nums[i];
            visited[i] = true;

            if (backtrack(nums, restBuckets, target, curBucketCapcity, visited)) {
                return true;
            }

            curBucketCapcity -= nums[i];
            visited[i] = false;
        }

        return false;
    }
}