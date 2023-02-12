package skill.interval;

import java.util.Arrays;
import java.util.Comparator;

/**
 * leetcode 1288 middle 删除被覆盖的区间
 * 
 * 给你一个区间列表，请你删除列表中被其他区间所覆盖的区间。
 * 只有当 c <= a 且 b <= d 时，我们才认为区间 [a,b) 被区间 [c,d) 覆盖。
 * 在完成所有删除操作后，请你返回列表中剩余区间的数目。
 */
public class DeleteCoveredIntervals {
    public int removeCoveredIntervals(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] a, int[] b) {
                if (a[0] == b[0]) {
                    // 左边界相等时，右边界降序排列
                    return b[1] - a[1];
                }
                return a[0] - b[0];
            }
        });

        // [[1,2],[1,4],[3,4]]
        int covered = 0;
        int n = intervals.length;
        int left = intervals[0][0];
        int right = intervals[0][1];

        for (int i = 1; i < n; i++) {
            // 如果是覆盖区间
            if (left <= intervals[i][0] && right >= intervals[i][1]) {
                covered++;
            }

            // 如果是交叉区间[[1,4], [3,5]]
            if (right >= intervals[i][0] && right <= intervals[i][1]) {
                // 虚拟区间变成[1, 5]
                right = intervals[i][1];
            }

            // 区间无交集，更新边界值
            if (intervals[i][0] > right) {
                left = intervals[i][0];
                right = intervals[i][1];
            }
        }

        return n - covered;
    }
}
