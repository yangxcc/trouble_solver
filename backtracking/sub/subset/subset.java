package backtracking.sub.subset;

import java.util.ArrayList;
import java.util.List;

/**
 * 子集和组合问题是一样的
 * 
 * leetcode 78 middle 子集
 * 
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 */
public class Subset {
    List<List<Integer>> ans = new ArrayList<>();
    public List<List<Integer>> subsets(int[] nums) {

        return ans;
    }

    public void backtrack(int[] nums, int idx, List<Integer> path) {
        ans.add(new ArrayList<>(path));
        
        for (int i = idx; i < nums.length; i++) {
            path.add(nums[i]);

            backtrack(nums, i + 1, path);

            path.remove(path.size() - 1);
        }
    }
}
