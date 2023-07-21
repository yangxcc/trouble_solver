/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-07-06 22:08:52
 * @LastEditTime: 2023-07-10 22:36:40
 */
package skill.ugly_number;

import java.util.PriorityQueue;

public class UglyNumber {
    /**
     * leetcode 263 simple 是否为丑数，丑数 就是只包含质因数 2、3 和 5 的正整数。
     * 比如12=2×2×3是丑数，14=2×7不是丑数
     */
    public boolean isUgly(int n) {
        while (n % 2 == 0) {
            n /= 2;
        }
        while (n % 3 == 0) {
            n /= 3;
        }
        while (n % 5 == 0) {
            n /= 5;
        }

        return n == 1;
    }

    /**
     * leetcode 剑指offer49 丑数 middle，与丑数II竟然是一样的，只是丑数II中多了一个“或”字
     * 我们把只包含质因子 2、3 和/或 5 的数称作丑数（Ugly Number）。求按从小到大的顺序的第 n 个丑数。
     * 
     * 解题思路：大丑数一定是由小丑数*2，*3，*5得到的，对于每一个丑数都*2，*3，*5，本质上是会得到三个序列，其实我们的想法就是升序的合并这三个序列
     * 每个序列中都是丑数，也就是说14这种根本不是丑数！！
     * 需要注意序列里面的重复数字！！
     * 
     */
    public static int nthUglyNumber(int n) {
        int[] ugly = new int[n + 1];
        int p2 = 1, p3 = 1, p5 = 1;
        int product2 = 1, product3 = 1, product5 = 1;
        int idx = 1;
        while (idx <= n) {
            int product = Math.min(Math.min(product2, product3), product5);
            ugly[idx] = product;
            idx++;

            if (product == product2) {
                product2 = ugly[p2] * 2;
                p2++;
            }

            if (product == product3) {
                product3 = ugly[p3] * 3;
                p3++;
            }

            if (product == product5) {
                product5 = ugly[p5] * 5;
                p5++;
            }
        }

        return ugly[n];
    }

    /**
     * leetcode 313 middle 超级丑数
     * 超级丑数 是一个正整数，并满足其所有质因数都出现在质数数组 primes 中。
     * 给你一个整数 n 和一个整数数组 primes ，返回第 n 个 超级丑数 。
     * 
     * 题目数据保证第 n 个 超级丑数 在 32-bit 带符号整数范围内。
     * 
     * 相当于合并多条有序链表，使用优先级队列
     */
    public int nthSuperUglyNumber(int n, int[] primes) {
        // 需要知道是哪条链表上的 ----> 当前值,质因数是多少，当前指针，
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[0] - b[0];
        });
        for (int prim : primes) {
            pq.add(new int[] { 1, prim, 1 });
        }

        int[] ugly = new int[n + 1];
        int idx = 1;
        while (idx <= n) {
            int[] pair = pq.poll();
            int num = pair[0];
            int prime = pair[1];
            int index = pair[2];
            // 去重
            if (num != ugly[idx - 1]) {
                ugly[idx] = num;
                idx++;
            }

            // 将下一个丑数加入到优先级队列
            int[] next = new int[] { ugly[idx - 1] * prime, prime, index + 1 };
            pq.add(next);
        }
        return ugly[n];
    }

    /**
     * leetcode 1201 丑数3 middle
     * 给你四个整数：n 、a 、b 、c ，请你设计一个算法来找出第 n 个丑数。
     * 丑数是可以被 a 或 b 或 c 整除的 正整数 。
     * 
     * 这道题是修改了丑数的定义，只要一个正整数x存在a, b, c中的任何一个因子，那么x就是丑数。
     */
    public int nthUglyNumber3(int n, int a, int b, int c) {
        // 三个链表
        int productA = a, productB = b, productC = c;
        int idx = 1;
        int ans = 0;
        while (idx <= n) {
            ans = Math.min(productA, Math.min(productB, productC));
            idx++;
            if (ans == productA) {
                productA += a;
            }
            if (ans == productB) {
                productB += b;
            }
            if (ans == productC) {
                productC += c;
            }
        }

        return ans;
    }
}
