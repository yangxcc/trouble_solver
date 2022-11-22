package monotoneincreasingdigits

import "strconv"

/**
暴力解法，用例通过数 223/308，超出时间限制
*/
func monotoneIncreasingDigits(n int) int {
	var checkNum func(num int) bool
	checkNum = func(num int) bool {
		max := 10
		for num > 0 {
			tail := num % 10
			if max >= tail {
				max = tail
			} else {
				return false
			}
			num /= 10
		}
		return true
	}

	for i := n; i >= 0; i-- {
		if checkNum(i) {
			return i
		}
	}
	return 0
}

/**
贪心思想
局部最优：遇到strNum[i - 1] > strNum[i]的情况，让strNum[i - 1]--，然后strNum[i]给为9，可以保证这两位变成最大单调递增整数
*/
func monotoneIncreasingDigitsBetter(n int) int {
	s := strconv.Itoa(n)
	ss := []byte(s)
	for i := len(ss) - 1; i > 0; i-- {
		if ss[i] < ss[i-1] {
			ss[i-1]--
			// i-1后面所有的数都置为9
			for j := i; j < len(ss)-1; j++ {
				ss[j] = '9'
			}
		}
	}
	ans, _ := strconv.Atoi(string(ss))
	return ans
}
