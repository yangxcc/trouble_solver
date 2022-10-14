package kmp

// 构造前缀表next
// i,j分别指向前缀和后缀的末尾，此外j还表示当前位置前缀后缀的最大公共长度
func GetNext(needle string) []int {
	next := make([]int, len(needle))
	j := 0
	next[0] = j

	for i := 1; i < len(needle); i++ {
		// 前缀和后缀有元素不相等了，需要向前回退，这个回退是一个连续的过程
		for j > 0 && needle[j] != needle[i] {
			j = next[j-1]
		}
		if needle[i] == needle[j] {
			j++
		}
		next[i] = j
	}
	return next
}

func StrStr(haystack string, needle string) int {
	n := len(needle)
	if n == 0 {
		return 0
	}

	j := 0
	next := GetNext(needle)
	for i := 0; i < len(haystack); i++ {
		for j > 0 && haystack[i] != needle[j] {
			j = next[j-1]
		}
		if haystack[i] == needle[j] {
			j++
		}
		if j == n {
			return i - n + 1
		}
	}
	return -1
}
