/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-22 10:44:47
 * @LastEditTime: 2023-02-22 10:35:51
 */
package greedy.interval_cross;

import java.util.Arrays;

/**
 * leetcode 453 middle 无重叠区间
 * 
 * 给定一个区间的集合 intervals ，其中 intervals[i] = [starti, endi] 。
 * 返回 需要移除区间的最小数量，使剩余区间互不重叠 。
 * 
 *  [[1,2],[2,3],[3,4],[1,3]] 去掉[1,3]
 */
public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });

        int[] bound = intervals[0];
        int ans = 0;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < bound[1]) {
                ans++;
                bound[0] = intervals[i][0];
                bound[1] = Math.min(bound[1], intervals[i][1]);
            } else {
                bound = intervals[i];
            }
        }

        return ans;
    }
}
