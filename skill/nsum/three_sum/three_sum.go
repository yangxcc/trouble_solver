package threesum

import "sort"

// 三数之和 可以抽象成 两数之和
// 题目的难点在于不可以包含重复的三元组
func threeSum(nums []int) [][]int {
	ans := [][]int{}
	n := len(nums) - 1
	sort.Slice(nums, func(i, j int) bool {
		return nums[i] < nums[j]
	})

	// 这里不需要再去判断最小的两个数，因为这两个数一定不是两个数的和
	for k := n; k >= 2; k-- {
		if k < n && nums[k] == nums[k+1] {
			continue
		}
		for i, j := 0, k-1; i < j; {
			if nums[i]+nums[j] == -nums[k] {
				tmp := []int{nums[i], nums[j], nums[k]}
				ans = append(ans, tmp)
				for i < j && nums[i] == nums[i+1] {
					i++
				}
				for i < j && nums[j] == nums[j-1] {
					j--
				}
				i++
				j--
			} else if nums[i]+nums[j] < nums[k] {
				i++
			} else {
				j--
			}
		}
	}
	return ans
}

func threeSum2(nums []int) [][]int {
	ans := [][]int{}
	sort.Ints(nums)

	for i := 0; i < len(nums)-2; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		l := i + 1
		r := len(nums) - 1
		for l < r {
			if nums[l]+nums[r] == -nums[i] {
				tmp := []int{nums[l], nums[r], nums[i]}
				ans = append(ans, tmp)
				if l < r && nums[l] == nums[l+1] {
					l++
				}
				if l < r && nums[r] == nums[r-1] {
					r--
				}
				l++
				r--
			} else if nums[l]+nums[r] < -nums[i] {
				l++
			} else {
				r--
			}
		}
	}
	return ans
}
