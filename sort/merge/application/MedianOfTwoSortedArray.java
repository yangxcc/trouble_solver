package sort.merge.application;

/**
 * leetcode 4 hard 寻找两个正序数组中的中位数
 * 要求时间复杂度为O(log(m+n))
 */
public class MedianOfTwoSortedArray {
    // 如果m+n是奇数，那么中位数就是arr[(m+n)/2]
    // 如果m+n是偶数，那么中位数就是(arr[(m+n)/2]+arr[(m+n)/2+1])/2
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        int idx1 = (m + n + 1) / 2; // m+n-1/2，第m+n-1/2+1个位置，（0号位置，第1个）
        int idx2 = (m + n + 2) / 2; // m+n/2,m+n/2+1 ---> m+n+2/2

        return (findMedian(nums1, 0, nums2, 0, idx1) + findMedian(nums1, 0, nums2, 0, idx2)) / 2;
    }

    // 函数的含义是从nums1[start1...]和nums2[start2...]这两个数组中找到idx位置的数
    private double findMedian(int[] nums1, int start1, int[] nums2, int start2, int idx) {
        if (start1 >= nums1.length) {
            return nums2[start2 + idx - 1];
        }

        if (start2 >= nums2.length) {
            return nums1[start1 + idx - 1];
        }

        if (idx == 1) {
            return Math.min(nums1[start1], nums2[start2]);
        }

        // 去找第idx/2个数
        int val1 = start1 + idx / 2 - 1 < nums1.length ? nums1[start1 + idx / 2 - 1] : Integer.MAX_VALUE;
        int val2 = start2 + idx / 2 - 1 < nums2.length ? nums2[start2 + idx / 2 - 1] : Integer.MAX_VALUE;
        if (val1 < val2) {
            // 第idx/2个数肯定不在nums1[start1, start1+idx/2-1]这个区间内了
            return findMedian(nums1, start1 + idx / 2, nums2, start2, idx - idx / 2);
        } else {
            return findMedian(nums1, start1, nums2, start2 + idx / 2, idx - idx / 2);
        }
    }
}
