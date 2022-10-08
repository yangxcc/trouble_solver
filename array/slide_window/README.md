## 滑动窗口总结

比如 leetcode 209 simple [长度最小的子数组](https://leetcode.cn/problems/minimum-size-subarray-sum/)，能够体现出使用滑动窗口算法的两个前提条件：

- 数组中没有负数
- 想要得到的结果是连续子数组

比如leetcode 904 middle [水果成篮](https://leetcode.cn/problems/fruit-into-baskets/)，这道题与其他不同的是窗口的更新方式不同

比如leetcode 76 hard [最小覆盖字串](https://leetcode.cn/problems/minimum-window-substring/)是我目前为止遇到的最难的滑动窗口的算法题了，下面滑动窗口的模板就是根据这道题总结出来的【labuladong】

其他例题：leetcode 567 middle [字符串排列](https://leetcode.cn/problems/permutation-in-string/)，字符串的排列和字母异位词是一样的👀，leetcode 438 middle [找到字符串中所有字母异位词](https://leetcode.cn/problems/find-all-anagrams-in-a-string/)


**滑动窗口模板代码 🌈**

```
public void slidingWindow(String s, String t) {   // t是子数组
    HashMap<Character, Integer> need = new HashMap<>();
    HashMap<Character, Integer> window = new HashMap<>();
    int left = 0, right = 0;
    int valid = 0;
    
    while (right < s.length()) {
        // c是移入窗口的字符
        char c = s.charAt(right);
        // 右移窗口
        right++;    
        // 从上面两行代码可以看出区间是左开右闭的[left,right)
        
        // 对窗口内的数据进行更新
        ...
            
        while (window needs shrink) {   // 窗口能够收缩的条件
            // d是移出窗口的字符
            char d = s.charAt(left);
            // 收缩窗口
            left++;
            // 对窗口内数据进行更新
            ...
        }    
    }
}   
```