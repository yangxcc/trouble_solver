package replace

func repalceSpace(s string) string {
	var ans []byte
	for i, _ := range s {
		if s[i] == ' ' {
			// 这里一定要记住一维数组里面只能append单个元素，二维数组能够append一维数组
			ans = append(ans, []byte("%20")...)
		} else {
			ans = append(ans, s[i])
		}
	}
	return string(ans)
}

// 这道题是simple，但是如果要求原地修改，就上了一点难度🌧
// 其实很多数组填充类的问题，都可以先预先给数组扩容带填充后的大小，然后在从后向前进行操作。
func repalceSpaceMoreEfficient(s string) string {
	var countForSpace int
	b := []byte(s)
	// 记录下最后一个字符的位置，方便下面的操作
	length := len(b)
	for _, v := range b {
		if v == ' ' {
			countForSpace++
		}
	}

	// %20是' '的三倍大小，因此需要在原数组上扩充countForSpace * 2
	resize := countForSpace * 2
	tmp := make([]byte, resize)
	b = append(b, tmp...)
	for i, j := length-1, len(b)-1; i >= 0; i-- {
		if b[i] == ' ' {
			b[j] = '0'
			b[j-1] = '2'
			b[j-2] = '%'
			i--
			j -= 3
		} else {
			b[j] = b[i]
			i--
			j--
		}
	}
	return string(b)
}
