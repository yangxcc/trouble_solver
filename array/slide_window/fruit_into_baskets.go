package slidewindow

// leetcode 904 middle 水果成篮，窗口的更新方式不太一样
func totalFruit(fruits []int) int {
	// key是fruits数组中的元素，value是元素的个数
	window := make(map[int]int, 2)
	left, right, ans := 0, 0, 0
	for right < len(fruits) {
		window[fruits[right]]++
		right++

		for len(window) > 2 {
			window[fruits[left]]--
			if window[fruits[left]] == 0 {
				delete(window, fruits[left])
			}
			left++
		}

		// if len(window) == 2 {
		//     if right - left > ans {
		//         ans = right - left
		//     }
		// }
		// 如果是上面这种更新方式，对于fruits=[0,0]来说，没法更新窗口数据
		sum := 0
		// 使用窗口中元素的数量来表示窗口长度
		for _, cnt := range window {
			sum += cnt
		}
		if sum > ans {
			ans = sum
		}
	}
	return ans
}
