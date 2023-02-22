package greedy.interval_cross;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;;

/**
 * leetcode 56 middle 合并区间
 * 
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] == o2[0]) {
                    return o1[1] - o2[1];
                }
                return o1[0] - o2[0];
            }
        });

        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);

        for (int i = 1; i < intervals.length; i++) {
            // 只比较ans的最后一个即可
            int[] bound = ans.get(ans.size() - 1);

            if (intervals[i][0] <= bound[1]) {
                // 左边界选小的，右边界选大的
                bound[1] = Math.max(bound[1], intervals[i][1]);
            } else {
                ans.add(intervals[i]);
            }
        }

        return ans.toArray(new int[ans.size()][2]);
    }
}
