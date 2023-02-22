package greedy.interval_cross;

import java.util.Arrays;

/**
 * leetcode 452 middle 用最少数量的箭引爆气球
 * 
 * 输入：points = [[10,16],[2,8],[1,6],[7,12]]
 * 输出：2
 * 解释：气球可以用2支箭来爆破:
 * -在x = 6处射出箭，击破气球[2,8]和[1,6]。
 * -在x = 11处发射箭，击破气球[10,16]和[7,12]。
 */
public class MinArrow {
    public int findMinArrowShots(int[][] points) {
        // 按照左边界从小到大排序
        Arrays.sort(points, (a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return a[0] - b[0];
        });
        // [[10,16],[2,8],[1,6],[7,12]]
        // [1,6],[2,8],[7,12],[10,16]

        int ans = 1;
        // 相交一下减1
        int[] bound = points[0];
        for (int i = 1; i < points.length; i++) {
            if (points[i][0] <= bound[1]) {
                // 相交
                bound[0] = points[i][0];
                bound[1] = Math.min(bound[1], points[i][1]);
            } else {
                ans++;
                bound = points[i];
            }
        }
        return ans;
    }
}
