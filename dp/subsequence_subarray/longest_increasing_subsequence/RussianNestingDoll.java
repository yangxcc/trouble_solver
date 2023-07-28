/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-01 13:53:23
 * @LastEditTime: 2023-02-06 22:14:47
 */
package dp.subsequence_subarray.longest_increasing_subsequence;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode 354 hard 俄罗斯套娃信封问题
 * 
 * 给你一个二维整数数组 envelopes ，其中 envelopes[i] = [wi, hi] ，表示第 i 个信封的宽度和高度。
 * 当另一个信封的宽度和高度都比这个信封大的时候，这个信封就可以放进另一个信封里，如同俄罗斯套娃一样。
 * 请计算 最多能有多少个 信封能组成一组“俄罗斯套娃”信封（即可以把一个信封放到另一个信封里面）。
 * 注意：不允许旋转信封。
 * 
 * 看题义也是递增序列，只不过维度变成了两个
 * 最开始写错了，我想的是不改变数组的顺序，但实际上能够改变顺序，如case
 * 输入：envelopes = [[5,4],[6,4],[6,7],[2,3]]
 * 输出：3
 * 解释：最多信封的个数为 3, 组合为: [2,3] => [5,4] => [6,7]。
 */
public class RussianNestingDoll {
    public int maxEnvelopesWrong(int[][] envelopes) {
        int m = envelopes.length;
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        int ans = 1;

        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }

        return ans;
    }

    // 超时....，原来难度hard体现在这里...
    public int maxEnvelopes(int[][] envelopes) {
        int m = envelopes.length;
        int[] dp = new int[m];
        Arrays.fill(dp, 1);
        int ans = 1;

        // 按照第一个维度排序
        Arrays.sort(envelopes, (a, b) -> {
            // bugfix
            return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
        });

        for (int i = 1; i < envelopes.length; i++) {
            for (int j = 0; j < i; j++) {
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    ans = Math.max(ans, dp[i]);
                }
            }
        }

        return ans;
    }

    public int maxEnvelopesBetter(int[][] envelopes) {
        int m = envelopes.length;
        int[] dp = new int[m];
        Arrays.fill(dp, 1);

        // 按照第一个维度排序，如果第一个维度相等，按照第二个维度降序排列（必须）
        // 这是根据题目要求来的，如果两个信封宽度相同，按照高度升序排列，那么在计算的时候
        // 后面的信封就能够将前面的装起来，这显然是不合题意的（宽度相同，不能装）
        // 所以得降序排列
        Arrays.sort(envelopes, (a, b) -> {
            return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
        });

        int[] height = new int[m];
        for (int i = 0; i < envelopes.length; i++) {
            height[i] = envelopes[i][1];
        }
        return lengthOfLIS(height);
    }

    /**
     * 按照扑克牌来想
     * 其实这里我看的也是迷迷糊糊的，目前是硬背阶段 labuladong
     */
    private int lengthOfLIS(int[] nums) {
        // 每堆的顶部元素
        int[] top = new int[nums.length];
        // 堆数初始化为0
        int piles = 0;

        for (int i = 0; i < nums.length; i++) {
            int poker = nums[i];
            
            int left = 0, right = piles;  // 堆数从0开始，所以这里是开区间
            while (left < right) {
                // 找左边界
                int mid = (left + right) / 2;
                if (top[mid] < poker) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }

            // 没找到合适的堆，太大了
            if (left == piles) {
                piles++;
            }
            // 把这张牌放到堆顶
            top[left] = poker;
        }

        return piles; // 堆数就是最长递增序列
    }
}
