package removeelement

func removeElement(nums []int, val int) int {
	// 双指针
	left := 0
	right := len(nums) - 1
	// 当left == right + 1的时候跳出来
	for left <= right {
		if nums[left] != val {
			left++
		} else {
			if nums[right] == val {
				right--
			} else {
				// swap
				tmp := nums[left]
				nums[left] = nums[right]
				nums[right] = tmp
			}
		}
	}
	return left
}

func removeDuplicates(nums []int) int {
	slow, fast := 0, 0
	for fast < len(nums) {
		if nums[fast] != nums[slow] {
			slow++
			nums[slow] = nums[fast]
		}
		fast++
	}

	return slow + 1
}
