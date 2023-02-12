package skill.have_meeting;

import java.util.Arrays;

public class Meeting {
    /**
     * leetcode 252 simple 会议室
     * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],...] (si < ei)，
     * 请你判断一个人是否能够参加这里面的全部会议。
     */
    public boolean canAttendMeetings(int[][] intervals) {
        int n = intervals.length;
        Arrays.sort(intervals, (a, b) -> {
            return a[0] - b[0];
        });
        for (int i = 0; i < n; i++) {
            if (i > 0 && intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }

        return true;
    }

    /**
     * leetcode 253 middle 会议室II
     * 
     * 给定一个会议时间安排的数组，每个会议时间都会包括开始和结束的时间 [[s1,e1],[s2,e2],…] (si < ei)，
     * 为避免会议冲突，同时要考虑充分利用会议室资源，请你计算至少需要多少间会议室，才能满足这些会议安排。
     * 
     * @param intervals
     * @return
     * 
     *         这道题目翻译一下，其核心就是 计算同一时刻最多有多少会议同时开（同一时刻最多有多少区间重合）
     */
    public int minMeetingRooms(int[][] intervals) {
        /**
         * 一个思路：可以创建一个数组，比如[0, 20]就在tmp[0] - tmp[20]的范围内+1，同理其他的区间
         * 最后统计出该数组中最大的值，为了更快的进行区间增加，我们可以使用差分数组来完成这个目标
         * 
         * 这样的话时间复杂度是O(n)，总共遍历两次，第一次得到差分数组的长度，第二次统计最大值，但由于需要实现差分数组，显得不够优雅
         * 
         * 我们还可以把会议的起止时间放到一维坐标轴上，当碰到会议开始的时间+1，碰到会议结束的时间-1
         */
        int n = intervals.length;
        int[] startTimes = new int[n];
        int[] endTimes = new int[n];
        for (int i = 0; i < n; i++) {
            startTimes[i] = intervals[i][0];
            endTimes[i] = intervals[i][1];
        }

        Arrays.sort(startTimes);
        Arrays.sort(endTimes);

        int ans = -1;
        int count = 0;
        int i = 0, j = 0;

        while (i < n && j < n) {
            if (startTimes[i] < endTimes[j]) {
                // 遇到了开始时间
                count++;
                i++;
            } else {
                count--;
                j--;
            }

            ans = Math.max(ans, count);
        }

        return ans;
    }

    /**
     * leetcode 2402 hard 会议室3
     * 
     * 给你一个整数 n ，共有编号从 0 到 n - 1 的 n 个会议室。
     * 给你一个二维整数数组 meetings ，其中 meetings[i] = [starti, endi]
     * 表示一场会议将会在 半闭 时间区间 [starti, endi) 举办。所有 starti 的值 互不相同 。
     * 
     * 会议将会按以下方式分配给会议室：
     * 每场会议都会在未占用且编号 最小 的会议室举办。
     * 如果没有可用的会议室，会议将会延期，直到存在空闲的会议室。延期会议的持续时间和原会议持续时间 相同 。
     * 当会议室处于未占用状态时，将会优先提供给原 开始 时间更早的会议。
     * 
     * 返回举办最多次会议的房间 编号 。如果存在多个房间满足此条件，则返回编号 最小 的房间。
     * 
     * @param n
     * @param meetings
     * @return
     * 
     * 模拟这个过程
     */
    public int mostBooked(int n, int[][] meetings) {
        // freeTime[i]表示第i间房子的释放时间，0表示未占用
        int[] freeTime = new int[n];
        int[] count = new int[n];

        Arrays.sort(meetings, (a, b) -> {
            return a[0] - b[0];
        });

        for (int[] meeting : meetings) {
            int startTime = meeting[0];
            int endTime = meeting[1];

            // int flag = 0; // 用来标记所有的房间是否空闲
            int best = 0; // 用来表示当前不可用，但是可用时间最早的会议室编号
            boolean flag = false; // 用来标记是否需要等待

            for (int i = 0; i < n; i++) {
                // 包含了freeTime[i] == 0这种情况
                if (freeTime[i] <= startTime) {
                    count[i]++;
                    freeTime[i] = endTime;
                    flag = true;
                    break;
                } else if (freeTime[i] < freeTime[best]) {
                    // i号房间比best房间的释放时间都早
                    best = i;
                }
            }

            if (!flag) {
                count[best]++;
                freeTime[best] += endTime - startTime;
            }
        }

        int ans = 0;
        for (int i = 0; i < n; i++) {
            if (count[i] > count[ans]) {
                ans = i;
            }
        }
        return ans;
    }
}
