package happynumber

func isHappy(n int) bool {
	m := map[int]bool{}
	for {
		if n == 1 {
			return true
		}
		if m[n] {
			break
		}
		m[n] = true
		n = canculate(n)
	}
	return false
}

func canculate(n int) int {
	var res int
	for n > 0 {
		tmp := n % 10
		res += tmp * tmp
		n = n / 10
	}
	return res
}
