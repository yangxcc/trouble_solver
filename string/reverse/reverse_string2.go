package reverse

/*
	给定一个字符串 s 和一个整数 k，从字符串开头算起，每计数至 2k 个字符，就反转这 2k 字符中的前 k 个字符。
	如果剩余字符少于 k 个，则将剩余字符全部反转。
	如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
*/
func reverStr(s string, k int) string {
	// 和Java一样，golang中的字符串也是不可修改的
	b := []byte(s)
	for i := 0; i < len(b); i += 2 * k {
		if i+k >= len(b) {
			// 这种情况下说明剩余的字符数小于k
			reverse(b, i, len(b)-1)
			// 一定不要忘记结束这次循环
			continue
		}
		// 剩余字符无论是大于k还是大于2k都是反转前k个的
		reverse(b, i, i+k)
	}
	return string(b)
}

func reverse(s []byte, i, j int) {
	for i < j {
		tmp := s[i]
		s[i] = s[j]
		s[j] = tmp
		i++
		j--
	}
}
