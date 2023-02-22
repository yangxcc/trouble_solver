/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-12 17:41:11
 * @LastEditTime: 2023-02-18 09:06:03
 */
package palindrome

/**
给你一个字符串 s，请你将 s 分割成一些子串，使每个子串都是 回文串。返回 s 所有可能的分割方案。
回文串 是正着读和反着读都一样的字符串。
*/
func partition(s string) [][]string {
	if len(s) == 0 {
		return [][]string{}
	}

	ans := [][]string{}
	var backtrack func(s string, idx int, path []string)
	backtrack = func(s string, idx int, path []string) {
		if idx == len(s) {
			ans = append(ans, copy(path))
			return
		}

		for i := idx; i < len(s); i++ {
			if isPermutation(s[idx : i+1]) {
				path = append(path, s[idx:i+1])
				backtrack(s, i+1, path)
				path = path[:len(path)-1]
			} else {
				continue
			}
		}
	}

	backtrack(s, 0, []string{})
	return ans
}

func isPermutation(str string) bool {
	for i, j := 0, len(str)-1; i < j; i, j = i+1, j-1 {
		if str[i] != str[j] {
			return false
		}
	}

	return true
}

func copy(a []string) (b []string) {
	b = append(b, a...)
	return
}
