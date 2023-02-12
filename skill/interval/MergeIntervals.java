package skill.interval;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * leetcode 56 middle 合并区间
 * 
 * 以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi]。
 * 请你合并所有重叠的区间，并返回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。
 */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        LinkedList<int[]> ansHelper = new LinkedList<>();
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
                // 这里右边界的排序无所谓，因为合并区间的左边界肯定是第一个，右边界是所有能合并区间的右边界的最大值
            }
        });

        int i = 0;
        int n = intervals.length;
        while (i < n) {
            int leftBound = intervals[i][0];
            int rightBound = intervals[i][1];

            while (i < n - 1 && rightBound >= intervals[i + 1][0]) {
                rightBound = Math.max(rightBound, intervals[i + 1][1]);
                i++;
            }
            ansHelper.add(new int[] { leftBound, rightBound });
            i++;
        }

        return ansHelper.toArray(new int[ansHelper.size()][2]);
    }
}
