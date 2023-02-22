package greedy.candy;

import java.util.Arrays;

/**
 * leetcode 135 hard 分发糖果
 * 
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 * 你需要按照以下要求，给这些孩子分发糖果：
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 */
public class Candy {
    // bad case很多，再次做还是踩了一遍坑，一个没记住，详情在Go代码中
    public int candy(int[] ratings) {
        int n = ratings.length;
        int ans = 0;
        int[] record = new int[n];
        Arrays.fill(record, 1);

        for (int i = 0; i < n - 1; i++) {
            if (ratings[i] < ratings[i + 1]) {
                record[i + 1] = record[i] + 1;
            }
        }

        for (int i = n - 1; i > 0; i--) {
            // 比左右都大，且没有算过
            if (ratings[i - 1] > ratings[i]) {
                record[i - 1] = Math.max(record[i] + 1, record[i - 1]);
            }
            ans += record[i];
        }

        return ans + record[0];
    }
}