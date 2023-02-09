package random;

import java.util.Random;

/**
 * leetcode 528 middle 按权重随机选择
 * 
 * 给你一个 下标从 0 开始 的正整数数组 w ，其中 w[i] 代表第 i 个下标的权重。
 * 请你实现一个函数 pickIndex ，它可以 随机地 从范围 [0, w.length - 1] 内（含 0 和 w.length - 1）选出并返回一个下标。
 * 选取下标 i 的 概率 为 w[i] / sum(w) 。
 * 
 * 例如，对于 w = [1, 3]，挑选下标 0 的概率为 1 / (1 + 3) = 0.25 （即，25%），而选取下标 1 的概率为 3 / (1 + 3) = 0.75（即，75%）。
 */
public class RandomPickWithWeight {
    private int[] preSum;
    private Random rand;

    public RandomPickWithWeight(int[] w) {
        rand = new Random();

        int n = w.length;
        preSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + w[i - 1];
        }
    }
    
    public int pickIndex() {
        int n = preSum.length;
        // 生成[1, preSum[n-1]]区间内的数
        int r = rand.nextInt(preSum[n - 1]) + 1;
        int left = 0, right = n;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (preSum[mid] == r) {
                right = mid;
            } else if (preSum[mid] < r) {
                left = mid + 1;
            } else if (preSum[mid] > r) {
                right = mid;
            }
        }
        return left - 1;  // 这里的这个-1只是针对preSum（长度比w多一个），不是针对二分的
    }
}

/**
 * 本题的思路，给定一个数组 w = [1, 3, 2, 1]，按照其权值作为概率来随机选择，我们可以把数组中的元素打平到坐标轴上
 * 
 *           - | ---  | -- | - |
 * index       0      1    2   3
 * weight      1      4    6   7
 * 
 * 写到这里，能够看到这个weight就是前缀和数组，所以先实现前缀和数组
 * 
 * 我们要做的就是在区间[1, 7]内生成一个随机数，通过这个随机数来选择索引，具体地，
 * 如果我们生成了一个5，那么应该选择哪一个索引呢? 很明显，这里应该是选择2这个索引，
 * 因为5是落在2的这个范围内的，因此，对于随机生成的一个数，我们应该找到前缀和数组中大于等于这个数的值对应的索引（-1， 因为前缀和数组多了一个0）
 * 
 * 问题1：为什么生成的是区间[1, 7]内的随机数，而不是[0, 7]或者[0， 7）
 * 我的理解是，因为我们生成的随机数是离散的，即{1}, {2, 3, 4}, {5, 6}, {7}，如果是[0, 7]，那么0号索引就对应这两个数了{0, 1}
 * 同理，如果是[0, 7)，3号索引没有对应元素了
 * 
 * 问题2：如何在前缀和数组中快速找到 >= 随机数的元素
 * 答案是二分法，通过寻找左边界的二分来找到这个数，即使随机数在前缀和数组中不存在，最后返回的这个左边界也会是正好大于这个随机数的值
 * 这个left有几个意义
 *  （1）代表数组中有多少个数比随机数小
 *  （2）随机数应该在数组中的插入位置
 *  （3）代表大于等于这个随机数的最小元素索引（正好大于）
 *  */