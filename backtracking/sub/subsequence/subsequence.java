/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-13 09:57:52
 * @LastEditTime: 2023-03-09 11:03:07
 */
package backtracking.sub.subsequence;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * leetcode 491 middle 递增的子序列
 * 
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素。
 * 你可以按 任意顺序 返回答案。
 * 
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 */
public class Subsequence {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> findSubsequences(int[] nums) {
        backtrack(nums, 0, new ArrayList<>());
        return ans;
    }

    public void backtrack(int[] nums, int idx, List<Integer> path) {
        if (path.size() > 1) {
            ans.add(new ArrayList<>(path));
            // 这里不要return，后面有可能还会添加元素
        }

        HashSet<Integer> visited = new HashSet<>();
        for (int i = idx; i < nums.length; i++) {
            // 在本层已经访问过了这个数
            if (visited.contains(nums[i])) {
                continue;
            }
            if (path.size() == 0 || nums[i] >= path.get(path.size() - 1)) {
                visited.add(nums[i]);
                path.add(nums[i]);

                backtrack(nums, i + 1, path);

                path.remove(path.size() - 1);
                // 这里没必要在把nums[i]删掉，因为hashset的管理范围是一层，进入到下一次层之后会重新实例化
            }
        }
    }
}
/**
 * leetcode 491 middle 递增的子序列
 * 
 * 给你一个整数数组 nums ，找出并返回所有该数组中不同的递增子序列，递增子序列中 至少有两个元素。
 * 你可以按 任意顺序 返回答案。
 * 
 * 数组中可能含有重复元素，如出现两个整数相等，也可以视作递增序列的一种特殊情况。
 */
class Main {
    public static void main(String[] args) {
        
    }

    static List<List<Integer>> ans = new ArrayList<>();
    public static void backtrack(int[] nums, int idx, List<Integer> path) {
        if (path.size() >= 2) {
            ans.add(new ArrayList<>(path));
            // return;
        }
        
        HashSet<Integer> memo = new HashSet<>();
        for (int i = idx; i < nums.length; i++) {
            if (memo.contains(nums[i])) {
                continue;
            }
            if (path.size() == 0 || nums[i] >= path.get(path.size() - 1)) {
                path.add(nums[i]);
                memo.add(nums[i]);

                backtrack(nums, i + 1, path);

                path.remove(path.size() - 1);
            }
        }

    }
}