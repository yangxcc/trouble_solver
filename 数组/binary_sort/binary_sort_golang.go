package sort

// 给定一个 n 个元素有序的（升序）整型数组 nums 和一个目标值 target，
// 写一个函数搜索 nums 中的 target，如果目标值存在返回下标，否则返回 -1。
func sortWithoutRepeatElement(nums []int, target int) int {
	left := 0
	right := len(nums) - 1

	for left <= right {
		mid := left + (right-left)/2
		if nums[mid] == target {
			return mid
		} else if nums[mid] < target {
			left = mid + 1
		} else {
			right = mid - 1
		}
	}
	return -1
}

// 给定一个排序数组和一个目标值，在数组中找到目标值，并返回其索引。
// 如果目标值不存在于数组中，返回它将会被按顺序插入的位置。
func findInsertPlace(nums []int, target int) int {
	left := 0
	right := len(nums) - 1

	for left <= right {
		mid := left + (right-left)/2
		if nums[mid] == target {
			return mid
		} else if nums[mid] < target {
			left = mid + 1
		} else {
			right = mid - 1
		}
	}
	return right + 1
}

// 在排序数组中查找元素的第一个和最后一个位置，数组中有重复元素，要求时间复杂度为O(logn)
func sortWithRepeatElement(nums []int, target int) []int {
	leftBound := findLeftBound(nums, target)
	rightBound := findRightBound(nums, target)
	return []int{leftBound, rightBound}
}

func findLeftBound(nums []int, target int) int {
	left := 0
	right := len(nums) - 1
	for left <= right {
		mid := left + (right-left)/2
		if nums[mid] == target {
			right = mid - 1
		} else if nums[mid] < target {
			left = mid + 1
		} else {
			right = mid - 1
		}
	}

	// 如果target比所有的元素都大,left会越界
	if left >= len(nums) || nums[left] != target {
		return -1
	}
	return left
}

func findRightBound(nums []int, target int) int {
	left := 0
	right := len(nums) - 1
	for left <= right {
		mid := left + (right-left)/2
		if nums[mid] == target {
			left = mid + 1
		} else if nums[mid] < target {
			left = mid + 1
		} else {
			right = mid - 1
		}
	}

	// 如果target比所有的元素都小,right会越界
	if right < 0 || nums[right] != target {
		return -1
	}
	return right
}
