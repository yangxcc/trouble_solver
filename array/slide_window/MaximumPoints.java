package array.slide_window;

/**
 * leetcode 1423 middle 可获得的最大点数 https://leetcode.cn/problems/maximum-points-you-can-obtain-from-cards
 * 几张卡牌 排成一行，每张卡牌都有一个对应的点数。点数由整数数组 cardPoints 给出。
 * 每次行动，你可以从行的开头或者末尾拿一张卡牌，最终你必须正好拿 k 张卡牌。
 * 你的点数就是你拿到手中的所有卡牌的点数之和。
 * 给你一个整数数组 cardPoints 和整数 k，请你返回可以获得的最大点数。
 *
 * 不用考虑越界的问题，题目中给出的数据范围，数组总和也不会超过2^32
 */
public class MaximumPoints {
    public int maxScore(int[] cardPoints, int k) {
        if (k == cardPoints.length) {
            int sum = 0;
            for (int x : cardPoints) {
                sum += x;
            }
            return sum;
        }
        process(cardPoints, 0, cardPoints.length - 1, 0, 0, k);
        return ans;
    }

    // 我的方法是使用这种dfs来解决，需要加上k==cardPoints.length的情况，但是这种方式超时了
    int ans = 0;
    private void process(int[] cardPoints, int left, int right, int curSum, int count, int k) {
        if (left > right) {
            return;
        }
        if (count == k) {
            ans = Math.max(ans, curSum);
            return;
        }

        // 可以选择left和right
        process(cardPoints, left + 1, right, curSum + cardPoints[left], count + 1, k);
        process(cardPoints, left, right - 1, curSum + cardPoints[right], count + 1, k);
    }


    // 使用滑动窗口来解决这个问题，因为只能在左边和右边选择，所以最后剩下的肯定是中间的位置
    // 最后会剩下n-k个元素，因此，我只需要用滑动窗口求出这n-k个连续元素的最小和就可以了
    public int maxScore2(int[] cardPoints, int k) {
        int n = cardPoints.length;
        int windowSize = n - k;
        // 先初始化一下
        int ans = 0;
        int sum = 0;
        for (int i = 0; i < windowSize; i++) {
            ans += cardPoints[i];
            sum += cardPoints[i];
        }
        System.out.println(ans);

        int minAns = ans;
        for (int i = windowSize; i < n; i++) {
            // 这样写的话不是滑动窗口了，ans的值每次滑动都需要变化
            // ans = Math.min(ans, ans + cardPoints[i] - cardPoints[i - windowSize]);
            ans += cardPoints[i] - cardPoints[i - windowSize];
            minAns = Math.min(minAns, ans);
            sum += cardPoints[i];
        }
        return sum - minAns;
    }
}
