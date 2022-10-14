package reverse

func reverseWords(s string) string {
	// 如果是在Java中，可以直接使用split和trim来解决这个问题，但最好还是不要直接使用API
	// 先把s中的每个单词取出来
	b := []byte(s)
	var strs []string
	var tmp []byte
	for _, v := range b {
		if v == ' ' {
			if len(tmp) > 0 {
				strs = append(strs, string(tmp))
				tmp = []byte{}
			}
			continue
		}

		tmp = append(tmp, v)
	}

	// 把最后一个单词加进来
	if len(tmp) > 0 {
		strs = append(strs, string(tmp))
	}

	var ans []byte
	for i := len(strs) - 1; i >= 0; i-- {
		ans = append(ans, []byte(strs[i])...)
		if i > 0 {
			ans = append(ans, []byte(" ")...)
		}
	}
	return string(ans)
}
