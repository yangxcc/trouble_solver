/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-08 15:10:38
 * @LastEditTime: 2023-02-08 15:22:49
 */
package other.game;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode 514 hard 自由之路
 * 
 * 这个题目特别长，但是这道题目是个很好的题目，有变动
 * https://leetcode.cn/problems/freedom-trail/description/
 * 
 * 题目也是要求用ring中的字符拼出key中的字符的最短路径
 * 不适合bfs，因为这个每次旋转不是固定次数，和开锁的那道题不一样
 */
public class Leetcode514 {
    HashMap<Character, List<Integer>> char2Idx;
    int[][] memo;

    public int findRotateSteps(String ring, String key) {
        char2Idx = new HashMap<>();
        memo = new int[ring.length()][key.length()];
        for (int[] row : memo) {
            Arrays.fill(row, -1);
        }

        for (int i = 0; i < ring.length(); i++) {
            char ch = ring.charAt(i);
            if (!char2Idx.containsKey(ch)) {
                char2Idx.put(ch, new ArrayList<>());
            }
            char2Idx.get(ch).add(i);
        }

        return dp(ring, 0, key, 0);
    }

    /**
     * 此函数的含义为：当前转盘指针指向ring[i]，从这里开始拼写出key[j...]的最少步数
     * 
     * @param ring
     * @param i
     * @param key
     * @param j
     * @return
     */
    public int dp(String ring, int i, String key, int j) {
        if (j == key.length()) {
            return 0;
        }

        if (memo[i][j] != -1) {
            return memo[i][j];
        }

        int res = Integer.MAX_VALUE;
        // ring中可能有重复字母，所以同一个字母会有多个位置
        for (int k : char2Idx.get(key.charAt(j))) {
            int move = Math.abs(k - i);
            int minMove = Math.min(move, ring.length() - move); // 比较顺时针和逆时针，怎么走步数少

            res = Math.min(res, 1 + minMove + dp(ring, k, key, j + 1));
        }

        memo[i][j] = res;

        return res;
    }
}
