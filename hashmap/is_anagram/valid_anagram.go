package hashmap

import "sort"

func isAnagram(s string, t string) bool {
	// 不写上这个判断不行，bad case: s="ab", t="a"
	if len(s) != len(t) {
		return false
	}

	sMap := map[byte]int{}
	for i, _ := range s {
		sMap[s[i]]++
	}

	for i, _ := range t {
		if _, ok := sMap[t[i]]; !ok {
			return false
		} else {
			sMap[t[i]]--
			if sMap[t[i]] < 0 {
				return false
			}
		}
	}
	return true
}

/*
	NOTE
	go中遍历字符串不能够使用range中的value，类型不一样
	Go语言的字符有以下两种：
		一种是uint8类型，或者叫byte型，代表了ASCII码的一个字符。
		另一种是rune类型，代表一个UTF-8字符，当需要处理中文、日文或者其他复合字符时，则需要用到rune类型。rune类型等价于int32类型(int32类型的别名)。
	for i, v := range s {
		fmt.Sprintf("%t", s[i])   // uint8
		fmt.Sprintf("%t", v)	  // int32(rune)
	}
*/

// 这种写法虽然能够通过全部测试用例，但是在leetcode中会超时
func groupAnagrams(strs []string) [][]string {
	ans := [][]string{}
	// 用来记录已经被访问过的单词
	sMap := map[string]bool{}
	for i := 0; i < len(strs); i++ {
		if sMap[strs[i]] {
			continue
		}
		tmp := []string{strs[i]}
		for j := i + 1; j < len(strs); j++ {
			if isAnagram(strs[i], strs[j]) {
				tmp = append(tmp, strs[j])
				sMap[strs[j]] = true
			}
		}
		ans = append(ans, tmp)
	}
	return ans
}

// 时间复杂度变为O(n)，解题思路是按照字母异位词的性质：排序之后是相同的
// 值得注意的是sort包的使用
func groupAnagramBySort(strs []string) (ans [][]string) {
	m := make(map[string][]string)
	for _, str := range strs {
		sa := []byte(str)
		sort.Slice(sa, func(i, j int) bool {
			return sa[i] < sa[j]
		})

		sign := string(sa)
		m[sign] = append(m[sign], str)
	}

	for _, v := range m {
		ans = append(ans, v)
	}
	return ans
}
