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
}