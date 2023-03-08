package backtracking.permutations;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * leetcode 47 全排列2 middle
 * 
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 * 
 * 包含重复数字，需要去重
 * 递归树上的同一层中，如果前面已经访问了数值相同的节点，后面就不要再访问了
 * 
 */
public class Permutation2 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        int n = nums.length;
        // 排序
        Arrays.sort(nums);

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

            // 这里是在同一层去重，一定要有!visited[i-1]，因为如果没有这个条件的话，也会把不同层的给去掉
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i-1]) {
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