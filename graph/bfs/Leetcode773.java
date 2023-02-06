package graph.bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

/**
 * leetcode 773 hard 滑动谜题
 * 
 * 在一个 2 x 3 的板上（board）有 5 块砖瓦，用数字 1~5 来表示, 以及一块空缺用 0 来表示。
 * 一次 移动 定义为选择 0 与一个相邻的数字（上下左右）进行交换.
 * 最终当板 board 的结果是 [[1,2,3],[4,5,0]] 谜板被解开。
 * 给出一个谜板的初始状态 board ，返回最少可以通过多少次移动解开谜板，如果不能解开谜板，则返回 -1 。
 * 
 * 只能0移动
 */
public class Leetcode773 {
    public int slidingPuzzle(int[][] board) {
        String target = "123450";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                sb.append(board[i][j]);
            }
        }
        String start = sb.toString();
        Deque<String> q = new ArrayDeque<>();
        q.offer(start);

        HashSet<String> visited = new HashSet<>();
        visited.add(start);

        int ans = 0;

        int[][] dir = new int[][] { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String curState = q.poll();
                if (curState.equals(target)) {
                    return ans;
                }
                // 找到 0 的位置
                int index = -1;
                for (int j = 0; j < curState.length(); j++) {
                    if (curState.charAt(j) == '0') {
                        index = j;
                        break;
                    }
                }

                // 所在位置 [index / 3, index % 3]
                // 左[index / 3, index % 3 - 1], 右[index / 3, index % 3 + 1]
                // 上[index / 3 - 1, index % 3], 下[index / 3 + 1, index % 3]
                for (int[] d : dir) {
                    int x = index / 3 + d[0];
                    int y = index % 3 + d[1];
                    if (x < 0 || x >= board.length || y < 0 || y >= board[0].length) {
                        continue;
                    }
                    String tmp = swap(curState, index, x * 3 + y);
                    if (!visited.contains(tmp)) {
                        visited.add(tmp);
                        q.offer(tmp);
                    }
                }
            }
            ans++;
        }

        return -1;
    }

    private String swap(String s, int i, int j) {
        char[] chs = s.toCharArray();
        char tmp = chs[i];
        chs[i] = chs[j];
        chs[j] = tmp;

        return new String(chs);
    }
}
