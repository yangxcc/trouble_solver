### 哈希表

1. leetcode 242 simple [有效的字母异位词](https://leetcode.cn/problems/valid-anagram/)，其中需要注意对字符串的遍历方法
   
   - 类似的一道题，leetcode 49 middle [字母异位词分组](https://leetcode.cn/problems/group-anagrams/)，在找异位词的基础上分组
   - 值得注意的是 leetcode 438 middle [找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string/)，这道题不要惯性思维，暴力破解，这样时间复杂度太高了，这个其实是**滑动窗口**的题目😂

2. x数之和
   
   - 对于leetcode 1 simple [两数之和](https://leetcode.cn/problems/two-sum/)，使用hashmap能够很快的得到答案，因为题目中给定了条件，所以无需去重
   - 对于leetcode 15 middle [三数之和](https://leetcode.cn/problems/3sum/)以及leetcode 18 middle [四数之和](https://leetcode.cn/problems/4sum/)给定target之后，要求组合不能重复，如果用暴力法我们可以使用三层/四层循环来遍历数组，将所有符合条件的组合都放入集合中，然后再去重，这样时间复杂度太高了。因此，以三数之和为例，我们的方法是先排序，之后固定一个数，再去选择另外两个数；同理对于四数之和，排序之后先固定两个数，只是多了一层for循环