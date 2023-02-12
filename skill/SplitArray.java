package skill;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 659 middle 分割数组为连续子序列
 * 
 * 给你一个按 非递减顺序 排列的整数数组 nums 。
 * 请你判断是否能在将 nums 分割成 一个或多个子序列 的同时满足下述 两个 条件：
 * 每个子序列都是一个 连续递增序列（即，每个整数 恰好 比前一个整数大 1 ）。
 * 所有子序列的长度 至少 为 3 。
 * 
 * 如果可以分割 nums 并满足上述条件，则返回 true ；否则，返回 false 。
 */
public class SplitArray {
    /**
     * 对于数组中的任意一个元素，有两种选择
     * 一种是将其加入到某个序列中
     * 第二种是自己开辟新的序列，做序列中的第一名
     * 
     * @param nums
     * @return
     */
    public static boolean isPossible(int[] nums) {
        HashMap<Integer, Integer> freq = new HashMap<>(); // 元素对应的频次
        HashMap<Integer, Integer> need = new HashMap<>(); // 元素被几个序列需要

        HashMap<Integer, LinkedList<LinkedList<Integer>>> sequence = new HashMap<>(); // 记录元素被哪几个序列需要

        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        for (int num : nums) {
            if (freq.get(num) == 0) {
                continue;
            }

            if (need.containsKey(num) && need.get(num) > 0) {
                // 能够将num加入到其他的序列中
                freq.put(num, freq.get(num) - 1);
                need.put(num, need.get(num) - 1);
                // 对于num+1的需求增加1个
                need.put(num + 1, need.getOrDefault(num + 1, 0) + 1);

                /** 加入序列中，好像不对 */
                // LinkedList<Integer> helperSeq = sequence.get(num).removeLast();
                // helperSeq.addLast(num);
                // LinkedList<LinkedList<Integer>> helper = sequence.getOrDefault(num + 1, new LinkedList<>());
                // helper.addLast(new LinkedList<>(helperSeq));
                // sequence.put(num + 1, helper);

            } else if (freq.getOrDefault(num, 0) > 0 && freq.getOrDefault(num + 1, 0) > 0
                    && freq.getOrDefault(num + 2, 0) > 0) {
                        // 如果是斗地主中的顺子，这里就是从num到num+5
                // num自立门户
                freq.put(num, freq.get(num) - 1);
                freq.put(num + 1, freq.get(num + 1) - 1);
                freq.put(num + 2, freq.get(num + 2) - 1);
                // 对num+3的需求增加了
                need.put(num + 3, need.getOrDefault(num + 3, 0) + 1);

                /** 加入序列中，好像不对 */
                // LinkedList<Integer> seq = new LinkedList<>();
                // seq.add(num);
                // seq.add(num + 1);
                // seq.add(num + 2);
                // LinkedList<LinkedList<Integer>> helper = sequence.getOrDefault(num + 3, new LinkedList<>());
                // helper.addLast(new LinkedList<>(seq));
                // sequence.put(num + 3, helper);

            } else {
                return false;
            }
        }

        // for (LinkedList<LinkedList<Integer>> seq : sequence.values()) {
        //     for (LinkedList<Integer> seq1 : seq) {
        //         for (int v : seq1) {
        //             System.out.print(v + '\t');
        //         }
        //         System.out.println('\n');
        //     }
        // }

        return true;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,3,3,4,4,5,5};
        isPossible(nums);
    }
}
