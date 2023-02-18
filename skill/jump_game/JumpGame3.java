/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-18 15:54:17
 * @LastEditTime: 2023-02-18 16:24:28
 */
package skill.jump_game;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * leetcode 1306 middle 跳跃游戏3
 * 
 * 这里有一个非负整数数组 arr，你最开始位于该数组的起始下标 start 处。
 * 当你位于下标 i 处时，你可以跳到 i + arr[i] 或者 i - arr[i]。
 * 
 * 请你判断自己是否能够跳到对应元素值为 0 的 任一 下标处。
 * 注意，不管是什么情况下，你都无法跳到数组之外。
 */
public class JumpGame3 {
    // 可以使用bfs
    public boolean canReach(int[] arr, int start) {
        int n = arr.length;
        boolean[] visited = new boolean[n];

        Deque<Integer> q = new ArrayDeque<>();
        q.addLast(start);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int idx = q.removeFirst();
                if (idx < 0 || idx >= n) {
                    continue;
                }
                if (visited[idx]) {
                    continue;
                }
                if (arr[idx] == 0) {
                    return true;
                }

                visited[idx] = true;
                q.addLast(idx + arr[idx]);
                q.addLast(idx - arr[idx]);
            }
        }
        return false;
    }
}
