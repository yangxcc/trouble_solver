/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-06 17:11:35
 * @LastEditTime: 2023-02-05 17:07:32
 */
package backtracking.combination;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 39 middle 组合总数
 * 
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * 
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * 
 * 无重复元素，但是每一个元素都可以被重复选择
 */
public class Combination4 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        Arrays.sort(candidates);
        backtrack(candidates, 0, target, new ArrayList<>());
        return ans;
    }

    public void backtrack(int[] candidates, int idx, int target, List<Integer> path) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }

        if (target < 0) {
            return;
        }

        for (int i = idx; i < candidates.length; i++) {
            // if (target - candidates[i] < 0) {
            //     break;
            // }
            path.add(candidates[i]);
            target -= candidates[i];

            // 不能这样写，因为这样是先做了选择，才跳出循环的，选择没有被撤销，可以在循环外写，也可以在最开始循环的时候写
            // if (target < 0) {
            //     break;
            // }

            backtrack(candidates, i, target, path);

            path.remove(path.size() - 1);
            target += candidates[i];
        }
    }
}