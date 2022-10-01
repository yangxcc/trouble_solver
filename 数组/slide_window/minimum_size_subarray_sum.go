package slidewindow

// leetcode 209 middle 长度最小的子数组，最简单的滑动窗口题目
func minSubArrayLen(target int, nums []int) int {
	left, right := 0, 0
	cur, ans := 0, len(nums)+1

	for right < len(nums) {
		cur += nums[right]
		right++

		// 左闭右开区间
		for cur >= target {
			if right-left < ans {
				ans = right - left
			}
			cur -= nums[left]
			left++
		}
	}
	if ans == len(nums)+1 {
		return 0
	}
	return ans
}

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

// leetcode 76 hard 最小覆盖字串，最难的滑动窗口的题目了
func minWindow(s string, t string) string {
	need, window := map[byte]int{}, map[byte]int{}
	sLen, tLen := len(s), len(t)
	// right和cnt的初始值要讲究一些
	// 最开始我的right写的是0，bad case：s="a", t="b"
	// 最开始cnt写的是sLen，bad case：s="a", t="a"
	left, right, cnt := 0, -1, sLen+1

	for i := 0; i < tLen; i++ {
		need[t[i]]++
	}

	for i, j := 0, 0; j < sLen; j++ {
		window[s[j]]++

		for judge(window, need) {
			// 更新窗口
			if j-i+1 < cnt {
				cnt = j - i + 1
				left = i
				right = j
			}

			// 缩减窗口
			window[s[i]]--
			i++
		}
	}
	return s[left : right+1]
}

func judge(sMap, tMap map[byte]int) bool {
	for k, v := range tMap {
		if sMap[k] < v {
			return false
		}
	}
	return true
}
