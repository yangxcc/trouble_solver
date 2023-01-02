/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-02 11:15:27
 * @LastEditTime: 2023-01-02 11:35:00
 */
package slidewindow

/**
给定两个字符串 s 和 p，找到 s 中所有 p 的 异位词 的子串，返回这些子串的起始索引。不考虑答案输出的顺序。
异位词 指由相同字母重排列形成的字符串（包括相同的字符串）。
*/
func findAnagrams(s string, p string) []int {
	need, window, ans := map[byte]int{}, map[byte]int{}, []int{}
	for _, v := range p {
		need[byte(v)]++
	}
	left, right, valid := 0, 0, 0
	for right < len(s) {
		b := byte(s[right])
		right++
		if need[b] > 0 {
			window[b]++
			if window[b] == need[b] {
				valid++
			}
		}

		for right-left >= len(p) {
			if valid == len(need) {
				ans = append(ans, left)
			}

			l := byte(s[left])
			left++
			if need[l] > 0 {
				if window[l] == need[l] {
					valid--
				}
				window[l]--
			}
		}
	}
	return ans
}
