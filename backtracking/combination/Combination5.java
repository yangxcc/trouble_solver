package backtracking.combination;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * 一个数组中的全部组合数
 */
public class Combination5 {
    public static void main(String[] args) {
        int[] nums = {1, 1, 2, 3};
        Arrays.sort(nums);
        backtrack(nums, 0, new LinkedList<>());
        for (List<Integer> path : ans) {
            System.out.println(path.toString());
        }
    }

    static List<List<Integer>> ans = new LinkedList<>();

    private static void backtrack(int[] nums, int start, LinkedList<Integer> path) {
        ans.add(new LinkedList<>(path));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            path.addLast(nums[i]);
            backtrack(nums, i + 1, path);
            path.removeLast();
        }
    }
}
