package array.binary_search.application;

import java.util.ArrayList;
/**
 * leetcode 392 simple 判断子序列
 * https://leetcode.cn/problems/is-subsequence/description/
 */
public class isSubsequence {
    // 如果仅仅是按照题目要求，那么有两种方案可以解决
    // 一是求解两者的最长公共子序列LCS，如果len(LCS)==len(t)，就证明是子序列O(m*n)
    // 二是使用贪心的算法，使用双指针 O(m)
    public boolean isSubsequence(String s, String t) {
        // s是否为t的子序列
        int i = 0;
        for (int j = 0; j < t.length(); j++) {
            if (i < s.length() && t.charAt(j) == s.charAt(i)) {
                i++;
            }
        }

        return i == s.length();
    }

    /**
     * 但是有一个进阶问题，如果有大量输入的 S，称作 S1, S2, ... , Sk 其中 k >= 10亿，
     * 你需要依次检查它们是否为 T 的子序列。在这种情况下，你会怎样改变代码？
     * 
     * 这种情况下我们仍然可以使用贪心的算法，此时时间复杂度为O(k*n)
     * 但是使用二分的话，可以将时间复杂度降为O(k*logn)
     */
    public static boolean isSubsequenceBetter(String s, String t) {
        // s是不是t的子序列，t是比较长的那一个
        ArrayList<Integer>[] index = new ArrayList[256];
        for (int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            if (index[ch] == null) {
                index[ch] = new ArrayList<>();
            }
            index[ch].add(i);
        }

        int i = 0;
        int j = 0;
        for (; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (index[ch] == null) {
                return false; // 如果s中的字符，t里面根本没有，那s肯定不是t的子序列
            }
            // j是在t上的指针，下面这个函数的目的就是找到比j大的最小索引
            int pos = leftBound(index[ch], j);
            if (pos == -1) {
                // 说明没有ch可以提供使用了
                return false;
            }

            j = index[ch].get(pos) + 1;
        }

        return true;
    }

    private static int leftBound(ArrayList<Integer> arr, int target) {
        int left = 0, right = arr.size() - 1;
        // Collections.sort(arr); // 不用，往里放的时候就是顺序放的
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr.get(mid) == target) {
                right = mid - 1;
            } else if (arr.get(mid) < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        if (left >= arr.size()) {
            return -1;
        }

        // 有多少个比target小的数
        return left;
    }

    public static void main(String[] args) {
        String s = "axc";
        String t = "ahbgdc";
        isSubsequenceBetter(s, t);
    }
}
