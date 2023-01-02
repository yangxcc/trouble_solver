package slidewindow

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
