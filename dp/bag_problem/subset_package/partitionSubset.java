/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-06 20:10:54
 * @LastEditTime: 2023-05-07 22:16:22
 */
package dp.bag_problem.subset_package;

public class PartitionSubset{
    /**
     * leetcode 416 middle 分割等和子集
     *
     * 给你一个 只包含正整数 的 非空 数组 nums 。请你判断是否可以将这个数组分割成两个子集，使得两个子集的元素和相等。
     *
     * 组合（回溯）中都能够分成k份，这里分成两份，比较特殊，所以能使用dp
     */
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % 2 == 1) {
            return false;  // 和是奇数的话，一定是不能分成两份完全相等的
        }
        sum /= 2;

        boolean[] dp = new boolean[sum + 1];
        dp[0] = true;

        // bad case：[1, 2, 5]，不能重复选择，注意和零钱兑换区别，这里的一维数组实际上是状态压缩了
        // for (int num : nums) {
        //     for (int i = 1; i <= sum; i++) {
        //         if (i < num) {
        //             continue;
        //         }

        //         dp[i] = dp[i - num] || dp[i];
        //     }
        // }

        for (int num : nums) {
            for (int i = sum; i >= 1; i--) {
                if (i < num) {
                    continue;
                }

                dp[i] = dp[i - num] || dp[i];
            }
        }
        return dp[sum];
    }


    /**
     * leetcode 494 middle 目标和
     * 给你一个非负整数数组 nums 和一个整数 target 。
     * 向数组中的每个整数前添加 '+' 或 '-' ，然后串联起所有整数，可以构造一个 表达式 ：
     * 例如，nums = [2, 1] ，可以在 2 之前添加 '+' ，在 1 之前添加 '-' ，然后串联起来得到表达式 "+2-1" 。
     * 返回可以通过上述方法构造的、运算结果等于 target 的不同 表达式 的数目。
     */
    public int findTargetSumWays(int[] nums, int target) {
        for (int x : nums) {
            target += x;
        }
        if (target % 2 == 1 || target < 0) {
            return 0;
        }

        target /= 2;
        int n = nums.length;
        // 这样其实就变成了从数组nums中，找到能够组成target的组合数，但是与零钱兑换2不同的是，base case不一样，而且这个是每个元素只能选一次
        int[][] dp = new int[n + 1][target + 1];
        dp[0][0] = 1;
        // 这里不能跟零钱兑换2一样dp[..][0]=1，因为能够构成0的个数不一定有多少个，因为nums中的数字可以为负数
        for (int i = 1; i <= n; i++) {
            for (int j = 0; j <= target; j++) {
                // 这里也不是从1开始，具体从多少开始，需要看base case
                if (j - nums[i - 1] < 0) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = dp[i - 1][j] + dp[i - 1][j - nums[i - 1]];
                }
            }
        }

        return dp[n][target];
    }

    /**
     * 这道题得转个弯，对于一个数组中的元素，我们不是+，就是-
     * 也就是说，对于每一个nums[i]只有两种选择，一种是+nums[i]，一种是-nums[i]
     * dp[i][j]表示0-i凑成j的方法数
     * 每个nums[i]只能选择一次，所以是0-1背包问题
     * 状态压缩，dp[j]表示凑成j的方法数
     * dp[j] = dp[j - nums[i]] + dp[j + nums[i]]
     * 但是这样的话，对于target不好确定值，所以变成了下面的思路
     *
     * 假设x是nums数组中取+的数的和，那么sum-x就是取-的数的和
     * 所以有x - (sum - x) = target --> x = (sum + target) / 2
     * sum和target都是固定的，所以我们就是要得到nums中能够凑出x的组合数
     * 所以我们可以套用回溯的模板来解决这道题
     * 因为只是让求个数，所以使用动态规划也可以
     * dp[j]表示的是nums能够凑出j的组合数
     * dp[j] += dp[j - nums[i]]
     */
}


