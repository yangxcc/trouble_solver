/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 20:40:41
 * @LastEditTime: 2023-03-03 17:07:42
 */
package sort.merge.application;

import java.util.ArrayList;
import java.util.List;

/**
 * leetcode 315 hard 计算右侧小于当前元素的个数
 * 
 * 给你一个整数数组 nums ，按要求返回一个新数组 counts 。
 * 数组 counts 有该性质： counts[i] 的值是  nums[i] 右侧小于 nums[i] 的元素的数量。
 */
public class CountSmaller {
    List<Integer> ans = new ArrayList<>();
    int[] tmp;
    Pair[] tmpPair;

    public List<Integer> countSmaller(int[] nums) {
        int n = nums.length;
        Pair[] pairs = new Pair[n];
        tmp = new int[n];
        tmpPair = new Pair[n];
        for (int i = 0; i < n; i++) {
            pairs[i] = new Pair(i, nums[i]);
        }

        mergeSort(pairs, 0, n - 1);

        for (int ele : tmp) {
            ans.add(ele);
        }
        return ans;
    }

    public void mergeSort(Pair[] pairs, int left, int right) {
        if (left < right) {
            int mid = left + (right - left) / 2;
            mergeSort(pairs, left, mid);
            mergeSort(pairs, mid + 1, right);
            merge(pairs, left, mid, right);
        }
    }

    public void merge(Pair[] pairs, int left, int mid, int right) {
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            tmpPair[k] = pairs[k];
        }

        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                pairs[k] = tmpPair[j++];
            } else if (j == right + 1) {
                pairs[k] = tmpPair[i++];
                tmp[pairs[k].idx] += j - mid - 1;  // 这两个地方必须是+=，bad case [5,2, 6,1]
            } else if (tmpPair[i].val <= tmpPair[j].val) {
                pairs[k] = tmpPair[i++];
                tmp[pairs[k].idx] += j - mid - 1;
            } else if (tmpPair[i].val > tmpPair[j].val) {
                pairs[k] = tmpPair[j++];
            }
        }
    }
}

class Pair {
    int idx;
    int val;

    public Pair(int index, int value) {
        this.idx = index;
        this.val = value;
    }
}