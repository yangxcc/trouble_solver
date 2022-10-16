package repeat

func repeatedSubstringPattern(s string) bool {
	for i := 1; i <= len(s); i++ {
		subStr := s[0:i]
		if len(s)%len(subStr) != 0 {
			continue
		}
		// 子串是否能多次构成s
		for j := 0; j <= len(s); j += len(subStr) {
			if j == len(s) {
				return true
			}
			if subStr != s[j:j+len(subStr)+1] {
				break
			}
		}
	}
	return false
}

// 如果s+s构成的字符串中，又出现了一个s，那么说明肯定会存在子字符串
func repeatedSubstringPatternWithKMP(s string) bool {
	str := s + s
	// 去掉第一个和最后一个字符
	str = str[1 : len(str)-1]
	// 判断s是否在str中出现
	return kmp(str, s)
}

func kmp(s, pattern string) bool {
	next := getNext(pattern)
	j := 0

	for i := 0; i < len(s); i++ {
		for j > 0 && s[i] != pattern[j] {
			j = next[j-1]
		}
		if s[i] == pattern[j] {
			j++
		}

		if j == len(pattern) {
			return true
		}
	}
	return false
}

func getNext(pattern string) []int {
	length := len(pattern)
	next := make([]int, length)
	j := 0
	next[0] = j

	for i := 1; i < length; i++ {
		for j > 0 && pattern[i] != pattern[j] {
			j = next[j-1]
		}

		if pattern[i] == pattern[j] {
			j++
		}
		next[i] = j
	}
	return next
}
