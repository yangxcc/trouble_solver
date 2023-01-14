/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-14 22:33:41
 * @LastEditTime: 2023-01-14 22:50:04
 */
package parentheses

func minAddToMakeValid(s string) int {
	leftCount, rightCount := 0, 0
	for _, b := range s {
		if b == '(' {
			rightCount++
		} else {
			rightCount--
			if rightCount == -1 {
				rightCount = 0
				leftCount++
			}
		}
	}
	return leftCount + rightCount
}
