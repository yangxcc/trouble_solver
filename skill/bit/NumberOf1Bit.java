/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-10 15:34:11
 * @LastEditTime: 2023-02-10 15:37:38
 */
package skill.bit;

/**
 * leetcode 191 simple 位1的个数
 */
public class NumberOf1Bit {
    public int hammingWeight(int n) {
        int ans = 0;
        while (n != 0) {
            n = n & (n - 1);
            ans++;
        }
        return ans;
    }
}
