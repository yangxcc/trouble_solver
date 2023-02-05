/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-12 16:48:00
 * @LastEditTime: 2023-02-05 15:32:13
 */
package backtracking.permutations;

import java.util.List;
import java.util.ArrayList;

/**
 * leetcode 46 middle 全排列
 * 
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 * 
 * 不含重复数字（无需去重）
 */
public class Permutation1 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        int n = nums.length;
        backtrack(nums, new ArrayList<>(), new boolean[n]);
        return ans;
    }

    public void backtrack(int[] nums, List<Integer> path, boolean[] visited) {
        if (path.size() == nums.length) {
            ans.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) {
                continue;
            }
            path.add(nums[i]);
            visited[i] = true;

            backtrack(nums, path, visited);

            path.remove(path.size() - 1);
            visited[i] = false;
        }
    }
}
