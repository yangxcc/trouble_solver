package skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * leetcode 759 hard 员工空闲时间（会员题目）
 * 
 * 给定员工的 schedule 列表，表示每个员工的工作时间。
 * 每个员工都有一个非重叠的时间段 Intervals 列表，这些时间段已经排好序。
 * 返回表示 所有 员工的 共同，正数长度的空闲时间 的有限时间段的列表，同样需要排好序。
 * 
 * 输入：schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
 * 输出：[[3,4]]
 * 解释：共有 3 个员工，并且所有共同的空间时间段是 [-inf, 1], [3, 4], [10, inf]。
 * 我们去除所有包含 inf 的时间段，因为它们不是有限的时间段。
 */
public class FreeTime {
    public static List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> intervals = new ArrayList<>();
        for (List<Interval> s : schedule) {
            for (Interval x : s) {
                intervals.add(x);
            }
        }

        if (intervals.size() == 1) {
            return intervals;
        }

        Collections.sort(intervals, new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.start - b.start == 0 ? a.end - b.end : a.start - b.start;
            }
        });

        List<Interval> ans = new ArrayList<>();
        int rightBound = intervals.get(0).end;
        int i = 1;

        while (i < intervals.size()) {
            if (intervals.get(i).start < rightBound) {
                rightBound = Math.max(intervals.get(i).end, rightBound);
            } else {
                // 这里就是一个空闲的
                ans.add(new Interval(rightBound, intervals.get(i).start));
                rightBound = intervals.get(i).end;
            }

            i++;
        }

        return ans;
    }

    public static void main(String[] args) {
        // schedule = [[[1,2],[5,6]],[[1,3]],[[4,10]]]
        List<List<Interval>> schedule = new ArrayList<>();
        List<Interval> list1 = new ArrayList<>();
        list1.add(new Interval(1, 2));
        list1.add(new Interval(5, 6));
        schedule.add(list1);

        List<Interval> list2 = new ArrayList<>();
        list2.add(new Interval(1, 3));

        List<Interval> list3 = new ArrayList<>();
        list3.add(new Interval(4, 10));
        schedule.add(list2);
        schedule.add(list3);

        List<Interval> employeeFreeTime = employeeFreeTime(schedule);
        for (Interval x : employeeFreeTime) {
            System.out.printf("[%d, %d] \t", x.start, x.end);
        }
    }
}

class Interval {
    public int start;
    public int end;

    public Interval() {
    }

    public Interval(int start, int end) {
        this.start = start;
        this.end = end;
    }
}
