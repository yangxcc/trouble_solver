package skill.interval;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 986 middle 区间列表的交集
 * 
 * 给定两个由一些 闭区间 组成的列表，firstList 和 secondList，
 * 其中 firstList[i] = [starti, endi] 而 secondList[j] = [startj, endj]。
 * 每个区间列表都是成对 不相交 的，并且 已经排序 。
 * 
 * 返回这 两个区间列表的交集 。
 * 
 * 形式上，闭区间 [a, b]（其中 a <= b）表示实数 x 的集合，而 a <= x <= b 。
 * 两个闭区间的 交集 是一组实数，要么为空集，要么为闭区间。例如，[1, 3] 和 [2, 4] 的交集为 [2, 3] 。
 */
public class IntersectionInterval {
    /**
     * 这种做法太慢了，时间复杂度O(n^2)
     * 
     * @param firstList
     * @param secondList
     * @return
     */
    public int[][] intervalIntersection(int[][] firstList, int[][] secondList) {
        if (firstList.length == 0 || secondList.length == 0) {
            return new int[][] {};
        }

        /**
         * 难点在去重，比如
         * [2, 3]和[1, 4]
         * [2, 3]和[1, 5]
         * 但是题目中有要求，每个区间都是不相交的，所以这种情况不存在
         */
        List<int[]> ansHelper = new ArrayList<>();
        for (int[] fList : firstList) {
            for (int[] sList : secondList) {
                int[] inter = intersection(fList, sList);
                if (inter.length > 0) {
                    ansHelper.add(inter);
                }
            }
        }

        return ansHelper.toArray(new int[ansHelper.size()][2]);
    }

    /**
     * 返回区间a和区间b的交集
     * 
     * @param a
     * @param b
     * @return
     */
    private int[] intersection(int[] a, int[] b) {
        // 左边界找最大，右边界找最小，比如[1, 3], [2, 4]
        int left = Math.max(a[0], b[0]);
        int right = Math.min(a[1], b[1]);

        // 有可能没有交集，比如[1, 3], [4, 5]
        if (left > right) {
            return new int[] {};
        }

        return new int[] { left, right };
    }

    /**
     * 使用双指针，区间都已经给排好序了
     * 
     * @param firstList  [[0,2],[5,10],[13,23],[24,25]]
     * @param secondList [[1,5],[8,12],[15,24],[25,26]]
     * @return
     */
    public int[][] intervalIntersection2(int[][] firstList, int[][] secondList) {
        int i = 0, j = 0;
        List<int[]> ansHelper = new ArrayList<>();

        while (i < firstList.length && j < secondList.length) {
            // // 什么情况下无交集
            // if (firstList[i][1] < secondList[j][0] || firstList[i][0] > secondList[j][1]) {

            // }

            // 有交集，无交集情况的反
            if (firstList[i][1] >= secondList[j][0] && firstList[i][0] <= secondList[j][1]) {
                ansHelper.add(new int[] { Math.max(firstList[i][0], secondList[j][0]),
                        Math.min(firstList[i][1], secondList[j][1]) });
            }

            // 怎么移动指针，右边界小的区间移动到下一个，一画图就理解了
            if (firstList[i][1] < secondList[j][1]) {
                i++;
            } else {
                j++;
            }
        }

        return ansHelper.toArray(new int[ansHelper.size()][2]);
    }
}
