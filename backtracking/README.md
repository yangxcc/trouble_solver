## 回溯算法

回溯算法的本质是穷举，所以他的效率不高

**能够使用回溯法解决的问题**

- 排列问题：N个数按一定规则全排列，有几种排列方式
- 组合问题：N个数里面按一定规则找出k个数的集合
- 子集问题：一个N个数的集合里有多少符合条件的子集
- 切割问题：一个字符串按一定规则有几种切割方式
- 棋盘问题：N皇后，解数独等


回溯算法的代码框架如下：
```
result = [];
def backtrack(路径, 选择列表):
    if 满足约束条件:
        res.add(路径);
        return;
    for 选择 in 选择列表:
        做选择
        backtrack(路径, 选择列表);  # 递归
        撤销选择
```
**for循环相当于横向遍历（同一层的遍历），backtrack相当于纵向遍历（深度遍历）**，理解这个对于去重很有帮助

- 如果是一个集合来求组合的话，就需要startIndex，例如：77.组合，216.组合总和III。
- 如果是多个集合取组合，各个集合之间相互不影响，那么就不用startIndex，例如：17.电话号码的字母组合


leetcode 332 hard [重新安排行程](https://leetcode.cn/problems/reconstruct-itinerary/description/)，这种类型的题在labuladong的某一个类型中讲解的很透彻！！

像需要记录路径的题目，一般都使用到了回溯，如果是没有要求记录路径，那么很大可能是dp

需要注意[leetcode 131 middle 分割回文串](https://leetcode.cn/problems/palindrome-partitioning/)和[leetcode LCR 087 middle 复原 IP 地址
](https://leetcode.cn/problems/0on3uN/description/)对字符串的切割方式不同，需要根据结果来判断应该怎么划分

分割回文串这道题的结果是如下格式
```java
输入：s = "aab"
输出：[["a","a","b"],["aa","b"]]
```
可以看出，结果中是将字符串的每一部分都单独的拿出来组成了结果数组中的一个元素，所以分割字符串的方式应该是
```java
for (int i = idx; i < s.length(); i++) {
    if (isValid(s.substring(idx, i + 1))) {
        path.add(s.substring(idx, i + 1));
        // 在path列表中加入字符串的部分
        // 字符串本身没有发生变化，根据索引的变动来越过对应的位置
        backtrack(s, i + 1, path); 
        path.remove(path.size() - 1);
    }
}
```

而复原IP这道题的结果是如下格式
```java
输入：s = "25525511135"
输出：["255.255.11.135","255.255.111.35"]
```
可以看出，结果中是将整体的字符串当作结果数组中的元素，所以分割字符串的方式应该是
```java
private void backtrack(String s, int idx, int dotCount) {
    if (dotCount == 3) {
        if (isValid(s.substring(idx))) {
            ans.add(s);
        }
        return;
    }

    for (int i = idx; i < s.length(); i++) {
        if (isValid(s.substring(idx, i + 1))) {
            // 对字符串整体改变，而不能是s=s.substring(idx, i+1) + "." + s.substring(i + 1);
            s = s.substring(0, i + 1) + "." + s.substring(i + 1);
            dotCount++;
            backtrack(s, i + 2, dotCount);
            s = s.substring(0, i + 1) + s.substring(i + 2); // 注意这里是i+2，把.跳过去
            dotCount--;
        }
    }
}
```