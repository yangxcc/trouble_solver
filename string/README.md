<!--
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-11 21:22:11
 * @LastEditTime: 2023-02-15 13:21:24
-->
### 基础
golang中的string是有一个指向[]byte数组的指针和length属性构成的，与Java类似，他们两个都是不可修改的
```
type stringStruct struct {
	array unsafe.Pointer  // 指向一个 [len]byte 的数组
	length int             // 长度
}
```

**涉及到的题目类型**
- 反转字符串，可能是反转某个字符，也可能是反转部分字符，还有可能是以单词为单位反转，在实现过程中使用的是双指针
  - leetcode 344 simple [反转字符串](https://leetcode.cn/problems/reverse-string/)
  - leetcode 542 simple [反转字符串II](https://leetcode.cn/problems/reverse-string-ii/)
  - leetcode 151 middle [反转字符串里的单词](https://leetcode.cn/problems/reverse-words-in-a-string/)
- 替换字符串中的空格（或其他字符），这道题如果没有任何要求是simple，但是如果要求尽量节省空间，那么我们就需要先预估空间，在进行操作
  - leetcode 剑指offer-05 simple [替换空格](https://leetcode.cn/problems/ti-huan-kong-ge-lcof/) 
- 旋转字符串，同理，如果没有要求原地旋转，只需要将两个字符串拼接，然后进行切片操作即可；但是如果要求不使用额外空间，那么我们就需要按照局部反转+全局反转的原则来调整
  - leetcode 剑指offer-58 II [左旋转字符串](https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof/)
- KMP算法...还是没咋看懂😔
  - kmp算法除了能够用在字符串中找子字符串，也能够用在重复子字符串这张题目中（也是没明白😂）
  - leetcode 28 middle [实现strStr()](https://leetcode.cn/problems/find-the-index-of-the-first-occurrence-in-a-string/)，即在字符串中寻找子字符串
  - leetcode 459 simple [重复的子字符串](https://leetcode.cn/problems/repeated-substring-pattern/)


### KMP算法
参考文章，[carl网站](https://programmercarl.com/0028.%E5%AE%9E%E7%8E%B0strStr.html#%E5%85%B6%E4%BB%96%E8%AF%AD%E8%A8%80%E7%89%88%E6%9C%AC)

#### 相关概念

**KMP的核心思想就是当出现字符串不匹配时，可以记录一部分之前已经匹配的文本内容，利用这些信息避免再从头开始匹配**

因此，如何记录已经匹配的文本内容是KMP的重点，这也是后面next数组的作用

next数组又叫做前缀数组，前缀数组的作用就是当前位置匹配失败，找到之前已经匹配上的位置，再重新匹配，此也意味着在某个字符失配时，前缀表会告诉你下一步匹配中，模式串应该跳到哪个位置

**前缀表的定义：** 记录下标i之前（包括i）的字符串中，相同前缀后缀的长度

- 前缀指的是以第一个字符开头但不包含最后一个字符的**所有**连续子串
- 后缀指的是以最后一个字符结尾但不包含第一个字符的**所有**连续子串

比如对于字符串`aabaaf`来说，其前缀是`aabaa`的所有连续子串（子串必须以第一个a开头），后缀是`abaaf`的所有连续子串（子串必须以最后一个f结尾）

#### 计算前缀表

根据上面的定义，以下面例子来理解前缀表的计算

对于字符串`aabaaf`来说：

- 一个字符`a`，其前缀后缀的公共长度为0，因为一个字符既是前缀也是后缀
- 两个字符`aa`，其前缀后缀的公共长度为1
- 三个字符`aab`，其前缀后缀的公共长度为0
  - 前缀：`a`,`aa`
  - 后缀：`b`,`ab`

- 四个字符`aaba`，其前缀后缀的公共长度为1
- 五个字符`aabaa`，其前缀后缀的公共长度为2
- 六个字符`aabaaf`，其前缀后缀的公共长度为0

```
因此有前缀表
        a a b a a f
        0 1 0 1 2 0
```


#### 利用前缀表
```
文本串：        aabaabaafa
模式串：        aabaaf
前缀表：        010120
```

当找到不匹配的位置，那么此时我们要看它**前一个字符的前缀表的数值**是多少，然后把下标移动到该值所在的位置上
> 因为是在当前位置不匹配的，当前位置的前缀表值对于此时来说没有意义，因为已经匹配不上了；所以需要找上一个位置的前缀表值

上面这个动作反复进行

#### 时间复杂度分析

假定文本串长度为`n`，模式串长度为`m`
- 暴力算法的时间复杂度为$O(m \times n)$
- KMP算法的时间复杂度分为两个部分：求得模式串的前缀表 + 匹配过程
  - 求模式串的前缀表的时间复杂度为$O(n)$，遍历模式串
  - 匹配过程的时间复杂度为$O(m)$，遍历文本串的过程中查前缀表
  - 因此总时间复杂度为$O(m+n)$

