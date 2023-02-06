package graph.bfs;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;

/**
 * leetcode 752 middle 打开转盘锁
 * 
 * 你有一个带有四个圆形拨轮的转盘锁。每个拨轮都有10个数字： '0', '1', '2', '3', '4', '5', '6', '7', '8',
 * '9' 。
 * 每个拨轮可以自由旋转：例如把 '9' 变为 '0'，'0' 变为 '9' 。每次旋转都只能旋转一个拨轮的一位数字。
 * 
 * 锁的初始数字为 '0000' ，一个代表四个拨轮的数字的字符串。
 * 列表 deadends 包含了一组死亡数字，一旦拨轮的数字和列表里的任何一个元素相同，这个锁将会被永久锁定，无法再被旋转。
 * 字符串 target 代表可以解锁的数字，你需要给出解锁需要的最小旋转次数，如果无论如何不能解锁，返回 -1 。
 */
public class Leetcode752 {
    /**
     * 从"0000"到target，每次只能够转一个位置上的数，可以把这个过程想象成一颗树，使用层序遍历
     * 
     * @param deadends
     * @param target
     * @return
     */
    public int openLock(String[] deadends, String target) {
        HashSet<String> memo = new HashSet<>();
        for (String str : deadends) {
            memo.add(str);
        }

        int ans = 0;
        String start = "0000";
        Deque<String> q = new ArrayDeque<>();
        q.offer(start);

        /**
         * 很重要，需要有一个结构来记录，避免走回头路，因为一个位置上的数，会向上转也会向下转
         */
        HashSet<String> visited = new HashSet<>();
        visited.add(start);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                String curState = q.poll();

                if (memo.contains(curState)) {
                    continue;
                }

                if (curState.equals(target)) {
                    return ans;
                }

                for (int j = 0; j < 4; j++) {
                    String up = turnUp(curState, j);
                    // 不能这么写，会把下面的down越过去
                    // if (visited.contains(up) || memo.contains(up)) {
                    // continue;
                    // }
                    // q.offer(up);
                    if (!visited.contains(up)) {
                        q.offer(up);
                        visited.add(up); // 别忘了
                    }

                    String down = turnDown(curState, j);
                    // if (visited.contains(down) || memo.contains(down)) {
                    // continue;
                    // }
                    // q.offer(down);
                    if (!visited.contains(down)) {
                        q.offer(down);
                        visited.add(down); // 别忘了
                    }
                }
            }
            ans++;
        }

        return -1;
    }

    /**
     * 向上转动i位置上的字符
     * 
     * @param str
     * @param i
     * @return
     */
    private String turnUp(String str, int i) {
        char[] chs = str.toCharArray();
        if (chs[i] == '0') {
            chs[i] = '9';
        } else {
            chs[i] -= 1;
        }

        return new String(chs);
    }

    private String turnDown(String str, int i) {
        char[] chs = str.toCharArray();
        if (chs[i] == '9') {
            chs[i] = '0';
        } else {
            chs[i] += 1;
        }

        return new String(chs);
    }
}
