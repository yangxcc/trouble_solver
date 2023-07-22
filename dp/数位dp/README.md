数位动态规划（数位DP）主要用于解决“在某个区间范围内，满足某种约束的数字的数量、总和、平方“

数位dp的题在leetcode上都是hard类型的

很牛逼的一个有关数位dp的[leetcode题解](https://leetcode.cn/problems/count-special-integers/solutions/1746956/shu-wei-dp-mo-ban-by-endlesscheng-xtgx/)，把模板都总结出来了

以 [leetcode 2376](https://leetcode.cn/problems/count-special-integers/description/) 为例，在这里按照上面题解的思路自己也写一遍

题目描述：如果一个正整数的每一个数位都是互不相同的，我们称他们为特殊整数，给你一个正整数n，请你返回区间`[1,n]`之间特殊整数的数目

```java
import java.util.Arrays;

class Solution {
    char[] chs;  // 用于保存整型n的字符串格式
    int[][] memo; // 减少重复计算

    public int countSpecialNumbers(int n) {
        this.chs = Integer.toString(n).toCharArray();
        this.memo = new int[chs.length][1 << 10];
        for (int i = 0; i < chs.length; i++) {
            Arrays.fill(memo[i], -1);
        }
        
        return dp(0, 0, true, false);
    }

    /**
     * 函数的作用是 从i开始填充数字，mask是前面填充数字的集合，能够构造出的特殊整数的最大数量
     * @param i 从i开始填充数字
     * @param mask 前面已经填充了的数字的集合
     * @param limit 表示的是前面的数字是否都是填的s[i]对应位置上的数，如果是的话为true，那么当前位置上的最大值是
     *              int(s[pos])，如果为false的话，当前位置上可选的数字为0-9
     * @param isNum 表示的是前面是否已经填写数字了，如果为true的话，则表示可以从0开始，如果为false的话，则表示房钱位置
     *              可以跳过，或者是从1开始（这个是为了解决前导0的问题）
     * @return
     */
    private int dp(int i, int mask, boolean limit, boolean isNum) {
        if (i == chs.length) {
            // 如果没有填充数，就返回0，填充了返回1
            return isNum ? 1 : 0;
        }

        int ans = 0;
        if (!isNum) {
            // 还没有填充数，此时可以选择跳过
            ans = dp(i + 1, mask, false, false);
        }

        // 枚举的上界取决于limit
        int up = limit ? chs[i] - '0' : 9;
        // 枚举的下界取决于isNum
        int low = isNum ? 0 : 1;
        for (int d = low; d <= up; d++) {
            if ((mask >> d & 1) == 0) { // 按照题目要求来写
                // d不在已经出现的数字集合中，使用d来填充
                ans += dp(i + 1, mask | (1 << d), limit && d == up, true);
            }
        }

        return ans;
    }


    // 我们可以在模板中加上备忘录，减少重复计算
    private int dp(int i, int mask, boolean limit, boolean isNum) {
        if (i == chs.length) {
            // 如果没有填充数，就返回0，填充了返回1
            return isNum ? 1 : 0;
        }
        
        if (!limit && isNum && memo[i][mask] != -1) {
            return memo[i][mask];
        }

        int ans = 0;
        if (!isNum) {
            // 还没有填充数，此时可以选择跳过
            ans = dp(i + 1, mask, false, false);
        }

        // 枚举的上界取决于limit
        int up = limit ? chs[i] - '0' : 9;
        // 枚举的下界取决于isNum
        int low = isNum ? 0 : 1;
        for (int d = low; d <= up; d++) {
            if ((mask >> d & 1) == 0) { // 按照题目要求来写
                // d不在已经出现的数字集合中，使用d来填充
                ans += dp(i + 1, mask | (1 << d), limit && d == up, true);
            }
        }
        
        if (!limit && isNum) {
            memo[i][mask] = ans;
        }

        return ans;
    }
}
```

与本题相同的还有[leetcode 1012 hard 至少有一位重复的数字](https://leetcode.cn/problems/numbers-with-repeated-digits/description/)，只需要把最终返回值接改为`n-dp(0,0,true,false)`即可，因为题目中要求的是至少含有一个重复元素的个数，所以就是`总个数-不含重复元素的个数`


