/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-13 14:18:19
 * @LastEditTime: 2022-12-20 17:56:44
 */
package quick

import (
	"math/rand"
	"time"
)

func QuickSort(nums []int) []int {
	// 打乱nums，避免极端情况出现，比如123456，pivot选成了6
	rand.Seed(time.Now().UnixNano())
	rand.Shuffle(len(nums), func(i, j int) {
		nums[i], nums[j] = nums[j], nums[i]
	})

	sort(nums, 0, len(nums)-1)

	return nums
}

func sort(nums []int, left, right int) {
	if left >= right {
		return
	}

	pivot := partition(nums, left, right)
	sort(nums, left, pivot-1)
	sort(nums, pivot+1, right)
}

func partition(nums []int, left, right int) int {
	// // 打乱nums，避免极端情况出现，比如123456，pivot选成了6
	// rand.Seed(time.Now().UnixNano())
	// rand.Shuffle(len(nums[left:right+1]), func(i, j int) {
	// 	nums[i], nums[j] = nums[j], nums[i]
	// })
	// 不知道出于什么原因，在这里打乱nums[left:right+1]内的元素，leetcode上ac不了👿

	// 选择最左侧的节点作为pivot
	pivot := nums[left]
	leftIdx, rightIdx := left+1, right

	for leftIdx <= rightIdx {
		// 这个循环结束之后，nums[leftIdx] > pivot
		for leftIdx < right && nums[leftIdx] <= pivot {
			leftIdx++
		}

		// 这个循环结束之后，nums[rightIdx] <= pivot
		for rightIdx > left && nums[rightIdx] > pivot {
			rightIdx--
		}

		if leftIdx >= rightIdx {
			break
		}

		// swap
		nums[leftIdx], nums[rightIdx] = nums[rightIdx], nums[leftIdx]
	}

	// 一定要注意，将pivot放到合适的位置
	nums[left], nums[rightIdx] = nums[rightIdx], nums[left]

	return rightIdx
}
