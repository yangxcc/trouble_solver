/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-09-30 21:32:23
 * @LastEditTime: 2023-07-09 14:18:54
 */
package array.multi_pointer.remove_element;

import linkedlist.ListNode;

// NOTE: 快慢指针，在删除元素的这种场景下[0, slow]维护的是一个合法的区间

/**
 * leetcode 27 simple https://leetcode.cn/problems/remove-element/
 * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
 */
public class RemoveEle {
    public int removeElement(int[] nums, int val) {
        int left = 0, right = nums.length - 1;
        while (left <= right) {
            if (nums[left] != val) {
                left++;
            } else {
                if (nums[right] == val) {
                    right--;
                } else {
                    // swap(left, right)
                    int tmp = nums[left];
                    nums[left] = nums[right];
                    nums[right] = tmp;
                }
            }
        }
        return left;
    }

    // 使用快慢指针解决leetcode 27，删除数组中值为val的元素
    public int removeElement2(int[] nums, int val) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != val) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        return slow;
    }

    /**
     * leetcode 26 simple. 删除有序数组中的重复项
     * [0,0,1,1,1,2,2,3,3,4]
     * 
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int fast = 0; // 当前索引
        int slow = 0; // 无重复项区间的右边界
        while (fast < nums.length) {
            if (nums[slow] != nums[fast]) {
                slow++;
                nums[slow] = nums[fast];
            }
            fast++;
        }
        return slow + 1;
    }

    // leetcode 283 移动0，将0都移动到数组最后面
    public void moveZeroes(int[] nums) {
        int fast = 0, slow = 0;
        while (fast < nums.length) {
            if (nums[fast] != 0) {
                nums[slow] = nums[fast];
                slow++;
            }
            fast++;
        }

        // slow及其之后需要设置为0
        for (int i = slow; i < nums.length; i++) {
            nums[i] = 0;
        }
    }

    /**
     * 给你一个数组 nums 和一个值 val，你需要 原地 移除所有数值等于 val 的元素，并返回移除后数组的新长度。
     * 不要使用额外的数组空间，你必须仅使用 O(1) 额外空间并 原地 修改输入数组。
     * 元素的顺序可以改变。你不需要考虑数组中超出新长度后面的元素。
     * 
     * 其实和移动零这道题是一模一样的，移动零的本质其实可以是删除数组中val等于0的元素
     */
    class day0415 {
        public int removeElement(int[] nums, int val) {
            int fast = 0, slow = 0;
            while (fast < nums.length) {
                if (nums[fast] != val) {
                    nums[slow] = nums[fast];
                    slow++;
                }
                fast++;
            }

            return slow;
        }

        // leetcode 26 数组升序排列，让每个元素都只出现一次
        public int removeDuplicates(int[] nums) {
            int fast = 0, slow = 0;
            while (fast < nums.length) {
                if (nums[fast] != nums[slow]) {
                    nums[slow + 1] = nums[fast];
                    slow++;
                }
                fast++;
            }

            return slow + 1;
        }
    }

    // 同理，删除链表中的重复元素也是这一套 leetcode 83 simple 保留一个
    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (fast != null) {
            if (slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        slow.next = null;
        return head;
    }

    // 删除排序链表中的重复元素2，将链表中重复的节点都删掉，只留下不同的数字
    // 1,2,3,3,4,4,5 ----> 1,2,5
    public ListNode deleteDuplicates2(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode slow = dummy;
        ListNode fast = dummy.next;
        while (fast != null && fast.next != null) {
            if (fast.val == fast.next.val) {
                while (fast.next != null && fast.val == fast.next.val) {
                    fast = fast.next;
                }
                slow.next = fast.next;
                fast = fast.next;
            } else {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return dummy.next;
    }
}