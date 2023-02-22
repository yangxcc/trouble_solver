package restoreip

import "strconv"

func restoreIpAddresses(s string) []string {
	if len(s) < 4 {
		return []string{}
	}
	ans := []string{}
	var backtrack func(s string, idx int, dotCount int)
	backtrack = func(s string, idx, dotCount int) {
		// 不能这么写，dotCount==3的时候就需要return了
		// if dotCount == 3 && isValidNumber(s, idx, len(s)) {
		// 	ans = append(ans, path+s[idx:])
		// 	return
		// }
		if dotCount == 3 {
			if isValidNumber(s, idx, len(s)-1) {
				ans = append(ans, s)
			}
			return
		}

		for i := idx; i < len(s); i++ {
			if isValidNumber(s, idx, i) {
				dotCount++
				// 在i后面插入点
				s = s[:i+1] + "." + s[i+1:]

				backtrack(s, i+2, dotCount)

				dotCount--
				// 把i后面的点删除
				s = s[:i+1] + s[i+2:]
			} else {
				break
				// continue
				// 这里continue和break都可以，因为从左到右数会越来越大
			}
		}
	}

	backtrack(s, 0, 0)
	return ans
}

func isValidNumber(s string, start, end int) bool {
	if end-start > 0 {
		if s[start] == '0' {
			return false
		}
	}
	// 排除非法字符
	for i := start; i <= end; i++ {
		if s[i] < '0' || s[i] > '9' {
			return false
		}
		// 在golang里面[]byte不太好转int
		// java中可以这么写 num = num * 10 + (s[i] - '0')
	}
	num, _ := strconv.Atoi(s[start : end+1])
	return num >= 0 && num <= 255
}
