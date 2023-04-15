/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-07 20:13:20
 * @LastEditTime: 2023-04-15 15:11:48
 */
package advantageshuffle

import "sort"

/**
 *
 * 给定两个大小相等的数组 nums1 和 nums2，nums1 相对于 nums2 的优势可以用满足 nums1[i] > nums2[i]
 * 的索引 i 的数目来描述。
 * 返回 nums1 的任意排列，使其相对于 nums2 的优势最大化。
 */
func advantageCount(nums1 []int, nums2 []int) []int {
	sort.Ints(nums1)

	tmp4nums2 := [][]int{}

	sort.Slice(tmp4nums2, func(i, j int) bool {
		return tmp4nums2[i][1] < tmp4nums2[j][1]
	})

	// 最大值和最大值比较，如果nums1的最大值比nums2的最大值大，那就这样，如果小，那就用nums1中当前的最小值来代替
	ans := make([]int, len(nums1))
	left := 0
	right := len(nums1) - 1
	for i := len(nums2) - 1; i >= 0; i-- {
		pairs := tmp4nums2[i]
		// 索引和对应的值
		if nums1[right] > pairs[1] {
			ans[pairs[0]] = nums1[right]
			right--
		} else {
			ans[pairs[0]] = nums1[left]
			left++
		}
	}

	return ans
}
