package random;

import java.util.Random;

import linkedlist.ListNode;

/**
 * 水塘抽样算法
 * 例题：leetcode 382 middle 链表随机节点（只选择一个）
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