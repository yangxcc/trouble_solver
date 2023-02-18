package skill.jump_game;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * leetcode 1345 跳跃游戏4 hard
 * 
 * 给你一个整数数组 arr ，你一开始在数组的第一个元素处（下标为 0）。
 * 每一步，你可以从下标 i 跳到下标 i + 1 、i - 1 或者 j ：
 * i + 1 需满足：i + 1 < arr.length
 * i - 1 需满足：i - 1 >= 0
 * j 需满足：arr[i] == arr[j] 且 i != j
 * 
 * 请你返回到达数组最后一个元素的下标处所需的 最少操作次数 。
 * 注意：任何时候你都不能跳到数组外面。
 */
public class JumpGame4 {
    @Deprecated // bad case [100,-23,-23,404,100,23,23,23,3,404]
    public int minJumps(int[] arr) {
        HashMap<Integer, LinkedList<Integer>> ele2Idx = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            LinkedList<Integer> linkedList = ele2Idx.getOrDefault(arr[i], new LinkedList<>());
            linkedList.addLast(i);

            ele2Idx.put(arr[i], linkedList);
        }

        int n = arr.length;
        int[] dp = new int[n];
        Arrays.fill(dp, n - 1);  // 这里别写Integer.MAX_VALUE，因为这个数+1之后就会越界
        dp[0] = 0;
        
        for (int i = 1; i < n; i++) {
            if (i - 1 >= 0) {
                dp[i] = Math.min(dp[i], dp[i - 1] + 1);
            }
            if (i + 1 < n) {
                dp[i] = Math.min(dp[i], dp[i + 1] + 1);
            }

            LinkedList<Integer> indexs = ele2Idx.get(arr[i]);
            for (int idx : indexs) {
                if (idx == i) {
                    continue;
                }
                dp[i] = Math.min(dp[i], dp[idx] + 1);
            }
        }

        return dp[n - 1];
    }

    // 使用bfs，思路应该就是这么个思路，内存超了...
    public int minJumpsRightWay(int[] arr) {
        HashMap<Integer, LinkedList<Integer>> ele2Idx = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            LinkedList<Integer> linkedList = ele2Idx.getOrDefault(arr[i], new LinkedList<>());
            linkedList.addLast(i);

            ele2Idx.put(arr[i], linkedList);
        }

        int ans = 0;
        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(0);
        int n = arr.length;
        boolean[] visited = new boolean[n];

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int idx = q.removeFirst();
                if (idx < 0 || idx >= n || visited[idx]) {
                    continue;
                }

                if (idx == n - 1) {
                    return ans;
                }

                q.addLast(idx + 1);
                q.addLast(idx - 1);
                LinkedList<Integer> indexs = ele2Idx.get(arr[idx]);
                for (int index : indexs) {
                    if (index == idx) {
                        continue;
                    }
                    q.addLast(index);
                }
                visited[idx] = true;
            }

            ans++;
        }

        return ans;
    }
}
