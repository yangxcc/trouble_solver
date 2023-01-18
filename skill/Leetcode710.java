/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-18 10:37:50
 * @LastEditTime: 2023-01-18 11:05:39
 */
package skill;

import java.util.HashMap;

/**
 * leetcode 710 hard 黑名单中的随机数
 * 
 * 给定一个整数 n 和一个 无重复 黑名单整数数组 blacklist。
 * 设计一种算法，从 [0, n - 1] 范围内的任意整数中选取一个 未加入 黑名单 blacklist 的整数。
 * 任何在上述范围内且不在黑名单 blacklist 中的整数都应该有 同等的可能性 被返回。
 * 
 * 优化你的算法，使它最小化调用语言 内置 随机函数的次数。
 * 
 * 实现 Solution 类:
 * Solution(int n, int[] blacklist) 初始化整数 n 和被加入黑名单 blacklist 的整数
 * int pick() 返回一个范围为 [0, n - 1] 且不在黑名单 blacklist 中的随机整数
 * 
 */

 // 这道题目很难想明白hashmap的映射关系
public class Leetcode710 {
    private HashMap<Integer, Integer> memo;
    private int validBound;
    
    public Leetcode710(int n, int[] blacklist) {
        this.memo = new HashMap<>();
        // 取随机数就从这个范围内取
        this.validBound = n - blacklist.length;
        // 主要是方便判断last是否是黑名单中的数
        for (int b : blacklist) {
            memo.put(b, -1);
        }

        int last = n - 1;
        for (int b : blacklist) {
            if (b >= validBound) {
                continue;
            }
            while (memo.containsKey(last)) {
                last--;
            }
            memo.put(b, last);
            last--;
        }
        // 上面for循环的意思是 对于黑名单中的数，我们将其映射到尾部
        /**
         * 比如，n=5,blacklist={1,0}，那么会映射成
         *          0  1  2  3  4
         *          3  4
         * 也就是0被3替换了，1被4替换了，加上bound变量，我们只需要从[0, bound]这个区间内找就行了
         * 相当于把0.1换到了后面
         * 
         * 这里就存在一个问题，如果blacklist={1,4}，这样如果按照上面方法映射，1被4替换，同样还是黑名单中的数！！
         * 那么我们需要判断last是否为黑名单中的数
         * 
         * 还存在一个问题，blacklist中的数据本来就是validbound之外
         * 比如，n=5,blacklist={4,1}，那么会将4映射成3，而我们需要的只是把1映射成3
         */
    }   

    public int pick() {
        int idx = (int)Math.random() * validBound;
        if (memo.containsKey(idx)) {
            return memo.get(idx);
        }

        return idx;
    }
}
