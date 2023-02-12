package skill.interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * leetcode 1024 middle 视频拼接
 * 
 * 你将会获得一系列视频片段，这些片段来自于一项持续时长为 time 秒的体育赛事。这些片段可能有所重叠，也可能长度不一。
 * 使用数组 clips 描述所有的视频片段，其中 clips[i] = [starti, endi] 表示：某个视频片段开始于 starti 并于 endi
 * 结束。
 * 
 * 甚至可以对这些片段自由地再剪辑：
 * 
 * 例如，片段 [0, 7] 可以剪切成 [0, 1] + [1, 3] + [3, 7] 三部分。
 * 我们需要将这些片段进行再剪辑，并将剪辑后的内容拼接成覆盖整个运动过程的片段（[0, time]）。
 * 
 * 返回所需片段的最小数目，如果无法完成该任务，则返回 -1 。
 */
public class ContactVideo {
    public int videoStitching(int[][] clips, int time) {
        // 合并重叠区间 --> 不需要真正合并，当区间起点相等时，只需要选择终点长的那个就行了
        int n = clips.length;
        Arrays.sort(clips, (a, b) -> {
            // 保证前面的区间肯定大
            if (a[0] == b[0]) {
                return b[1] - a[1];
            }

            return a[0] - b[0];
        });

        // List<int[]> nonIntersection = new ArrayList<>();
        
        // for (int i = 0; i < n; i++) {
        //     // 两个区间重叠
        //     if (i > 0 && clips[i][1] <= clips[i - 1][1]) {
        //         continue;
        //     }
        //     nonIntersection.add(clips[i]);
        // }
        int curEnd = 0, nextEnd = 0;
        int res = 0;
        for (int i = 0; i < n && clips[i][0] <= curEnd;) {
            // 当前区间的起点小于等于目前的终点
            while (i < n && clips[i][0] <= curEnd) {
                nextEnd = Math.max(nextEnd, clips[i][1]);
                i++;
            }

            res++;
            curEnd = nextEnd;
            if (curEnd >= time) {
                return res;
            }
        }

        return -1;
    }
}

/**
 * 
 * 输入：clips = [[0,2],[4,6],[8,10],[1,9],[1,5],[5,9]], time = 10
 * 输出：3
 * 解释：
 * 选中 [0,2], [8,10], [1,9] 这三个片段。
 * 然后，按下面的方案重制比赛片段：
 * 将 [1,9] 再剪辑为 [1,2] + [2,8] + [8,9] 。
 * 现在手上的片段为 [0,2] + [2,8] + [8,10]，而这些覆盖了整场比赛 [0, 10]。
 */