/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-14 21:46:26
 * @LastEditTime: 2023-01-14 22:11:36
 */
package parentheses

func minInsertions(s string) int {
	rightCount, ans := 0, 0
	for _, b := range s {
		if b == '(' {
			rightCount += 2
			if rightCount%2 == 1 {
				ans++
				rightCount--
			}
		} else {
			rightCount--
			if rightCount == -1 {
				ans++
				rightCount = 1
			}
		}
	}
	return ans + rightCount
}
