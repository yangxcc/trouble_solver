package rotate

// 反转前n个字符
func rotateLeftWords(s string, n int) string {
	x := s + s
	return x[n : n+len(s)]
}

/*
	思路：局部反转+整体反转
		1. 先翻转前n个
		2. 再反转后n个
		3. 最后反转整个串
*/
func rotateLeftWordsWithoutExtraMem(s string, n int) string {
	b := []byte(s)
	reverse(b, 0, n-1)
	reverse(b, n, len(b)-1)
	reverse(b, 0, len(b)-1)
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
