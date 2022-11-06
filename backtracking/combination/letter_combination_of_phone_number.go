package combination

/**
给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。

输入：digits = "23"
输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]

0 <= digits.length <= 4
digits[i] 是范围 ['2', '9'] 的一个数字。

2对应的是abc，3对应的是def，组合的都是两位字母，而且不包含ab,ac...这种的

a的ASCII码是97，A的ASCII码是65
*/
func letterCombinations(digits string) []string {
	lenth := len(digits)
	if lenth == 0 || lenth > 4 {
		return []string{}
	}
	var ans []string
	// var convert [][]string
	// // 97+(i-1)*3
	// for i, _ := range digits {
	// 	ascii, _ := strconv.Atoi(string(digits[i]))
	// 	var tmp []string
	// 	for i := 0; i <= 2; i++ {
	// 		tmp = append(tmp, string(97+(ascii-1)*3))
	// 	}
	// 	convert = append(convert, tmp)
	// }
	digitsMap := [10]string{
		"",     // 0
		"",     // 1
		"abc",  // 2
		"def",  // 3
		"ghi",  // 4
		"jkl",  // 5
		"mno",  // 6
		"pqrs", // 7
		"tuv",  // 8
		"wxyz", // 9
	}

	// idx表示的是digits的索引
	var backtrack5 func(digits string, idx int, path string)
	backtrack5 = func(digits string, idx int, path string) {
		if len(digits) == len(path) {
			ans = append(ans, path)
			return
		}

		index := digits[idx] - '0'
		letters := digitsMap[index] // 对应的字符集
		for i := 0; i < len(letters); i++ {
			path += string(letters[i])

			backtrack5(digits, idx+1, path)

			path = path[:len(path)-1]
		}
	}

	backtrack5(digits, 0, "")
	return ans
}
