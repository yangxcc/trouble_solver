/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-21 14:26:08
 * @LastEditTime: 2022-12-26 16:34:22
 */
package application

// TopK问题，在升序排列的数组中，第k大元素的位置在len(nums)-k
func findKthLargest(nums []int, k int) int {
	left, right := 0, len(nums)-1

	for left <= right {
		// 在[left, right]范围内找pivot
		pivot := partition(nums, left, right)
		if pivot == len(nums)-k {
			return nums[pivot]
		} else if pivot < len(nums)-k {
			// 区间调整为[pivot+1, right]
			left = pivot + 1
		} else {
			// 区间调整为[left, pivot-1]
			right = pivot - 1
		}
	}

	return -1
}

func partition(nums []int, left, right int) int {
	pivot := nums[left]

	leftIdx, rightIdx := left+1, right
	for leftIdx <= rightIdx {
		for leftIdx < right && nums[leftIdx] <= pivot {
			leftIdx++
		}

		for rightIdx > left && nums[rightIdx] > pivot {
			rightIdx--
		}

		if leftIdx >= rightIdx {
			break
		}

		nums[leftIdx], nums[rightIdx] = nums[rightIdx], nums[leftIdx]
	}

	nums[left], nums[rightIdx] = nums[rightIdx], nums[left]

	return rightIdx
}
