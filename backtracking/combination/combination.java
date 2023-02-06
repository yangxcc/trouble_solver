package backtracking.combination;

import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * leetcode 77 middle 组合
 * 
 * 给定两个整数 n 和 k，返回范围 [1, n] 中所有可能的 k 个数的组合。
 * 你可以按 任何顺序 返回答案。
 */
public class Combination {
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backtrack(n, k, 1, new LinkedList<>());
        return ans;
    }

    public void backtrack(int n, int k, int idx, LinkedList<Integer> path) {
        if (path.size() == k) {
            ans.add(new LinkedList<>(path));
            return;
        }

        for (int i = idx; i <= n; i++) {
            path.addLast(i);

            // 本来写的是能够选自己，结果题目输出中是不能够选自己的
            backtrack(n, k, i + 1, path);

            path.removeLast();
        }
    }
}