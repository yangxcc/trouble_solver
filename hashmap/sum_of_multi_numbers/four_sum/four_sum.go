package foursum

import "sort"

// 其实相较于三数之和，就有多了一层循环
func fourSum(nums []int, target int) [][]int {
	ans := [][]int{}
	sort.Ints(nums)

	for i := 0; i < len(nums)-3; i++ {
		if i > 0 && nums[i] == nums[i-1] {
			continue
		}
		for j := i + 1; j < len(nums)-2; j++ {
			if j > i+1 && nums[j] == nums[j-1] {
				continue
			}
			l := j + 1
			r := len(nums) - 1
			for l < r {
				if nums[i]+nums[j]+nums[l]+nums[r] == target {
					tmp := []int{nums[i], nums[j], nums[l], nums[r]}
					ans = append(ans, tmp)
					for l < r && nums[l] == nums[l+1] {
						l++
					}
					for l < r && nums[r] == nums[r-1] {
						r--
					}
					l++
					r--
				} else if nums[i]+nums[j]+nums[l]+nums[r] < target {
					l++
				} else {
					r--
				}
			}
		}
	}
	return ans
}
