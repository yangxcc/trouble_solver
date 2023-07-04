/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-07-03 22:27:57
 * @LastEditTime: 2023-07-04 09:44:19
 */
package array.binary_search.application;

/**
 * leetcode 793 hard 阶乘函数后k个零
 *  f(x) 是 x! 末尾是 0 的数量。回想一下 x! = 1 * 2 * 3 * ... * x，且 0! = 1 。
 * 例如， f(3) = 0 ，因为 3! = 6 的末尾没有 0 ；而 f(11) = 2 ，因为 11!= 39916800 末端有 2 个 0 。
 * 给定 k，找出返回能满足 f(x) = k 的非负整数 x 的数量。
 */
public class Leetcode793 {
    public int preimageSizeFZF(int k) {
        return (int)(rightBound(k) - leftBound(k) + 1);
    }

    private long leftBound(int k) {
        // 相当于在[1, Long.MAX_VALUE]的范围内找到最小的能够满足阶乘末尾有k个0的数
        long left = 1;
        long right = Long.MAX_VALUE;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (count0(mid) == k) {
                right = mid - 1;
            } else if (count0(mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private long rightBound(int k) {
        // 相当于在[1, Long.MAX_VALUE]的范围内找到最大的能够满足阶乘末尾有k个0的数
        long left = 1;
        long right = Long.MAX_VALUE;
        while (left <= right) {
            long mid = left + (right - left) / 2;
            if (count0(mid) == k) {
                left = mid + 1;
            } else if (count0(mid) < k) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return right;
    }

    // num的阶乘末尾有多少个0
    // 题目中给定了k的范围，0 <= k <= 10^9，所以这里返回int就好，肯定不会越界
    // 但是不写long的话过不了用例
    private long count0(long mid) {
        // nunm中有多少个2和5的组合，5的个数肯定是比2多，所以就是统计有多少个5的个数
        int ans = 0;
        long flag = 5;
        while (mid >= flag) {
            ans += mid / flag;
            flag *= 5;
        }

        return ans;
    }
}
