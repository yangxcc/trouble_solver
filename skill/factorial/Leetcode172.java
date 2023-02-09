/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-09 20:01:30
 * @LastEditTime: 2023-02-09 20:13:26
 */
package skill.factorial;

/**
 * leetcode 172 middle 阶乘后的0
 * 
 * 给定一个整数 n ，返回 n! 结果中尾随零的数量。
 * 提示 n! = n * (n - 1) * (n - 2) * ... * 3 * 2 * 1
 * 
 * 输入：n = 5
 * 输出：1
 * 解释：5! = 120 ，有一个尾随 0
 */
public class Leetcode172 {
    /**
     * 这是一个错误的做法，我的思路就是来看一下这个序列里面有多少个5和2的倍数，因为0只是由2*5得到的
     * 实际上，2的个数一定是比5多的
     * 但是这种思路错的地方在于：25当成了一个5的倍数，事实上，它是由两个5组成的，比如25*4=100了
     * @param n
     * @return
     */
    @Deprecated
    public int trailingZeroesWrong(int n) {
        int count5 = 0;
        int count2 = 0;
        int count = 0;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0 && i % 5 == 0) {
                count++;
            } else if (i % 2 == 0) {
                count2++;
            } else if (i % 5 == 0) {
                count5++;
            }
        }
        return count + Math.min(count2, count5);
    }

    /**
     * 查找[1, n]这个序列中有多少个5，25是2个数，125是3个5
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int ans = 0;
        // 25 20 15 10 5 ---> 6
        long flag = 5;  // flag有可能发生溢出
        while (n >= flag) {
            ans += n / flag; // 有多少个能提供5，对于25， 50， 75这种只算了一次
            flag *= 5;
        }
        // 比如对于125的阶乘：125 / 5 + 125 / 25 + 125 / 125 
        return ans;
    }
}
