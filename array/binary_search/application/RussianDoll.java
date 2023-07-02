package array.binary_search.application;

import java.util.Arrays;

/**
 * leetcode 354 hard 俄罗斯套娃问题
 * https://leetcode.cn/problems/russian-doll-envelopes/description/
 */
public class RussianDoll {
    // 85/87
    public int maxEnvelopes(int[][] envelopes) {
        // 每个信封有两个维度，先对宽度 w 进⾏升序排序，如果遇到 w 相同的情况，则按照⾼度 h 降序排序。
        // 之后把所有的 h 作为⼀个数组，在这个数组上计算 最长递增序列（LIS） 的⻓度就是答案。
        Arrays.sort(envelopes, (a, b) -> {
            return a[0] == b[0] ? b[1] - a[1] : a[0] - b[0];
        });
        int n = envelopes.length;
        // dp[i]以i结尾的数组，最长递增序列是多少
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        int ans = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // 这里对于宽度的判断还不能去掉，去掉了通过的用例数更少
                if (envelopes[i][0] > envelopes[j][0] && envelopes[i][1] > envelopes[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    public int maxEnvelopesAllKilled(int[][] envelopes) {
        int m = envelopes.length;
        int[] dp = new int[m];
        Arrays.fill(dp, 1);

        // 按照第一个维度排序
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

            int left = 0, right = piles; // 堆数从0开始，所以这里是开区间
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
