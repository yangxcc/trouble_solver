/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-12-09 21:22:58
 * @LastEditTime: 2022-12-09 21:40:49
 */
package longestcommonsubsequence

import "testing"

func TestMethod(m *testing.T) {
	s := "abc"
	t := "ahbgdc"
	// 思路来源：https://leetcode.cn/problems/is-subsequence/solutions/82137/dui-hou-xu-tiao-zhan-de-yi-xie-si-kao-ru-he-kuai-s/
	isSubsequenceWithMassiveStr(s, t)
}
