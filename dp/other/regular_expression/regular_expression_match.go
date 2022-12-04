package regularexpression

/**
给你一个字符串 s 和一个字符规律 p，请你来实现一个支持 '.' 和 '*' 的正则表达式匹配。
	'.' 匹配任意单个字符
	'*' 匹配零个或多个前面的那一个元素
	所谓匹配，是要涵盖 整个 字符串 s的，而不是部分字符串。
*/
func isMatch(s string, p string) bool {
	// s[i...]和p[j...]的匹配情况
	var process func(s string, i int, p string, j int) bool
	process = func(s string, i int, p string, j int) bool {
		// base case
		if j == len(p) {
			return i == len(s)
		}
		if i == len(s) {
			// case: s="a", p="ab*c*d*" true 或者 s="a", p="abc*" false
			if (len(p)-j)%2 == 1 {
				return false // 剩余p的长度是奇数，肯定不会出现ab*c*d*这种情况
			}
			for ; j+1 < len(p); j += 2 {
				if p[j+1] != '*' {
					return false
				}
			}
			return true
		}

		if s[i] == p[j] || p[j] == '.' {
			// bad case: s="aa",p=="a*"，或者是P==".*" 不能直接都往下推进
			if j+1 < len(p) && p[j+1] == '*' {
				// 可以重复前面的字符n次或0次
				return process(s, i+1, p, j) || process(s, i, p, j+2)
			} else {
				return process(s, i+1, p, j+1)
			}
		} else {
			// p[j+1]=='*'
			if j+1 < len(p) && p[j+1] == '*' { // *可以让他前面的字符匹配0次
				// 相当于把j位置的字符删掉，后面继续推进，记得把*号也去掉
				return process(s, i, p, j+2)
			} else {
				return false
			}
		}
	}
	return process(s, 0, p, 0)
}
