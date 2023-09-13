/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-08 20:58:10
 * @LastEditTime: 2023-03-06 17:44:17
 */
package random;

import java.util.Random;

import linkedlist.ListNode;

/**
 * 水塘抽样算法
 * 例题：leetcode 382 middle 链表随机节点（只选择一个）
 * 
 * 给你一个单链表，随机选择链表的一个节点，并返回相应的节点值。每个节点 被选中的概率一样 。
 * 实现 Solution 类：
 * Solution(ListNode head) 使用整数数组初始化对象。
 * int getRandom() 从链表中随机选择一个节点并返回该节点的值。链表中所有节点被选中的概率相等。
 * 
 */
public class ReserviorSample {
    ListNode cur;

    public ReserviorSample(ListNode head) {
        this.cur = head;
    }

    public int getRandom() {
        ListNode helper = cur;
        int i = 0, res = 0;
        Random rand = new Random();

        while (helper != null) {
            i++;
            // 生成[0, i)区间内的一个随机数，只取0相等时，概率为1/i
            if (0 == rand.nextInt(i)) {
                res = helper.val;
            }

            helper = helper.next;
        }

        return res;
    }


    /**
     * 数组中的水池抽样算法
     *
     * 给定一个数据流，在数据量很大且数据量未知的情况下，使用线性复杂度，随机选取k个不重复的数，保证每个数被选择的概率相同。
     */
    public int[] getRandom2(int[] dataStream, int k) {
        int[] ans = new int[k];
        for (int i = 0; i < k; i++) {
            ans[i] = dataStream[i];
        }

        Random rand = new Random();
        for (int i = k; i < dataStream.length; i++) {
            int idx = rand.nextInt(i + 1);
            if (idx < k) {
                ans[idx] = dataStream[i];
            }
        }

        return ans;
    }

    /**
     * 分析上述算法
     * （1）当i<=k的时候，数据会被直接放到蓄水池，所以第i个数据进入蓄水池的概率是1
     * （2）当i>k的时候，在[0,i]这个区间内，选择一个随机数d，如果d<=k，那么就会替换掉蓄水池的中第d个数字，概率为k/i
     * （3）当i<=k的时候，程序会从接收到第k+1个数据的时候开始执行替换操作，第k+1次操作会替换池中第i个数据的概率为1/k+1，同理，
     * 第k+2次操作会替换池中第i个数据的概率为1/k+2... 所以第k+1次操作不会替换池中数据的概率为k/k+1，第k+2次操作不会替换池中
     * 数据的概率为k+1/k+2...，所以第i个数据不会被替换的概率为k/k+1*(k+1)/k+2*...=k/n
     * （4）当i>k时，程序从接收第i+1个数据时开始有可能替换第i个数据，同理（3），第i个数据不会被替换的概率为i/n
     *
     * 综上，无论是i<=k还是 i>k，每个数据最终留在水池中的概率都是k/n
     *
     */
}