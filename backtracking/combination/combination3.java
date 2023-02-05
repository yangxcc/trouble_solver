/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-06 19:37:06
 * @LastEditTime: 2023-02-05 17:42:51
 */
package backtracking.combination;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 216 middle 组合总和3
 * 
 * 找出所有相加之和为 n 的 k 个数的组合，且满足下列条件：
 *  只使用数字1到9
 *  每个数字 最多使用一次 
 * 
 * 返回 所有可能的有效组合的列表 。该列表不能包含相同的组合两次，组合可以以任何顺序返回。 
 */
public class Combination3 {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combinationSum3(int k, int n) {
        backtrack(n, 1, new LinkedList<>(), k);
        return ans;
    }

    public void backtrack(int target, int idx, LinkedList<Integer> path, int k) {
        if (path.size() == k && target == 0) {
            ans.add(new LinkedList<>(path));
            return;
        }

        if (target < 0 || path.size() > k) {
            return;
        }

        for (int i = idx; i <= 9; i++) {
            target -= i;
            path.addLast(i);

            backtrack(target, i + 1, path, k);

            target += i;
            path.removeLast();
        }
    }
}
