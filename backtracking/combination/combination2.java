package backtracking.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 40 middle 组合总数2
 * 
 * 给定一个候选人编号的集合 candidates 和一个目标数 target ，找出 candidates 中所有可以使数字和为 target 的组合。
 * candidates 中的每个数字在每个组合中只能使用 一次 。
 * 注意：解集不能包含重复的组合。
 * 
 * 有重复元素，不可重复选择
 */
public class Combination2 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, target, 0, new ArrayList<>());
        return ans;
    }

    public void backtrack(int[] candidates, int target, int idx, List<Integer> path) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            // 这里不能是0，得是idx，注意区分排列
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }

            path.add(candidates[i]);
            target -= candidates[i];

            // 每个元素只选择一次
            backtrack(candidates, target, i + 1, path);

            path.remove(path.size() - 1);
            target += candidates[i];
        }
    }
}
