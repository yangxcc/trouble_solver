package backtracking.sub.subset;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 90 middle 子集2
 * 
 * 给你一个整数数组 nums ，其中可能包含重复元素，请你返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。返回的解集中，子集可以按 任意顺序 排列。
 */
public class Subset2 {
    List<List<Integer>> ans = new ArrayList<>();
    
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        backtrack(nums, 0, new ArrayList<>());
        return ans;
    }

    public void backtrack(int[] nums, int idx, List<Integer> path) {
        ans.add(new ArrayList<>(path));

        for (int i = idx; i < nums.length; i++) {
            if (i > idx && nums[i] == nums[i - 1]) {
                continue;
            }
            path.add(nums[i]);

            backtrack(nums, i + 1, path);

            path.remove(path.size() - 1);
        }
    }
}
