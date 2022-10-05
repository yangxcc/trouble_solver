package squares

func sortedSquares(nums []int) []int {
	left := 0
	count := len(nums) - 1
	right := count
	res := make([]int, count+1)

	// golang math包中的abs要求float 64
	for left <= right {
		if nums[left]+nums[right] < 0 {
			res[count] = nums[left] * nums[left]
			left++
		} else {
			res[count] = nums[right] * nums[right]
			right--
		}
		count--
	}
	return res
}
