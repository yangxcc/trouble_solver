/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-20 14:12:31
 * @LastEditTime: 2022-12-20 14:42:16
 */
package application

func CountRangeSum(nums []int, lower int, upper int) int {
	// if len(nums) <= 1 {
	// 	return 0
	// }

	preSum := make([]int, len(nums)+1)
	for i := 1; i <= len(nums); i++ {
		preSum[i] = preSum[i-1] + nums[i-1]
	}

	var count int
	tmp := make([]int, len(nums))
	var merge func(arr []int, left, mid, right int)
	merge = func(arr []int, left, mid, right int) {
		// 先将[left, right]这个区间内的数值拷贝到tmp中，以便能够直接将结果写入到nums中
		for k := left; k <= right; k++ {
			tmp[k] = arr[k]
		}

		start, end := mid+1, mid+1
		for i := left; i <= mid; i++ {
			// 下面的两个for循环是去找符合条件的区间
			for start <= right && arr[start]-arr[i] < lower {
				start++
			}
			for end <= right && arr[end]-arr[i] <= upper {
				end++
			}
			count += end - start
		}

		i, j := left, mid+1
		for p := left; p <= right; p++ {
			if i == mid+1 {
				// 左边的已经全都合并完了
				arr[p] = tmp[j]
				j++
			} else if j == right+1 {
				arr[p] = tmp[i]
				i++
			} else if tmp[i] > tmp[j] {
				arr[p] = tmp[j]
				j++
			} else {
				arr[p] = tmp[i]
				i++
			}
		}
	}

	var sortHelper func(arr []int, left, right int)
	sortHelper = func(arr []int, left, right int) {
		if left < right {
			mid := left + (right-left)/2
			sortHelper(arr, left, mid)
			sortHelper(arr, mid+1, right)
			merge(arr, left, mid, right)
		}
	}

	// 对前缀和数组进行归并排序
	sortHelper(preSum, 0, len(preSum)-1)

	return count
}
