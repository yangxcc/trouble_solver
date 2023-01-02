/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-02 11:00:06
 * @LastEditTime: 2023-01-02 11:08:33
 */
package slidewindow

/**
给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
*/
func lengthOfLongestSubstring(s string) int {
	window := map[byte]int{}
	left, right, maxLen := 0, 0, 0

	for right < len(s) {
		b := byte(s[right])
		window[b]++
		right++

		for window[b] > 1 {
			l := byte(s[left])
			window[l]--
			left++
		}

		if right-left > maxLen {
			maxLen = right - left
		}
	}

	return maxLen
}
