# 动态规划

斐波那契数列的数学形式就是递归的
```
func fib(int N) {
    if N == 1 || N == 2 {
        return 1
    }
    return fib(N-1)+fib(N-2)
}
```
从下面的递归树中可以看出，上述代码其实是暴力递归，它是非常低效的，**因为其中包含了大量的重复计算**，而对于重复计算我们可以使用备忘录的方式来避免重复计算
![斐波那契数列的递归树](../image/dp/1.jpg)

递归算法的时间复杂度如何计算呢？**就是用子问题个数 ✖ 解决一个子问题所需要的时间**

我们称图中的这种方式为**自顶向下**的方式，从一个规模较大的问题`f(20)`向下逐渐分解规模，直到`f(1),f(2)`这两个base case，然后逐层返回答案

反过来，如果我们直接从最底下、最简单、问题规模最小、已知结果的`f(1),f(2)`（base case）开始往上推，直到拿到我们想要的答案，这就是递归的思路，这也是**动态规划一般都脱离了递归，而是由循环迭代完成计算的原因**

## dp数组的遍历方向

无论是什么样的顺序，我们需要把握住两点：
- 遍历的过程中，所有状态必须是已经计算出来的
- 遍历结束后，存储结果的位置必须已经被计算出来


## 递归算法的调试
```
int dp(string& ring, int i, string& key, int j) {
    /* base case */
    if (j == key.size()) {
        return 0;
    }
    /* 状态转移 */
    int res = INT_MAX;
    for (int k : charToIndex[key[j]]) {
        res = min(res, dp(ring, j, key, i + 1));
    }
    return res;
}
```
如何对递归函数进行debug呢，labuladong中给出了一个很好的方法：通过缩进体现出递归的层次

```
int count = 0;
void printIndent(int n) {
    for (int i=0; i<n; i++) {
        printf("    ");
    }
}

/**
    就是在函数开头和所有return语句对应的地方加上一些代码
*/
int dp(string& ring, int i, string& key, int j) {
    printIndent(count++);
    printf("i=%d, j=%d", i, j);

    /* base case */
    if (j == key.size()) {
        printIndent(--count);
        printf("return 0\n");

        return 0;
    }
    /* 状态转移 */
    int res = INT_MAX;
    for (int k : charToIndex[key[j]]) {
        res = min(res, dp(ring, j, key, i + 1));
    }

    printIndent(--count);
    printf("return %d\n", res);

    return res;
}
```
![](../image/dp/2.jpg)


**一个知识点：子数组必须是连续的，而子序列可以是不连续的，所以最长公共子序列和最长公共子数组的代码略有不同**
```
// dp[i][j]表示的是nums[0..i](以i结尾)和nums[0..j](以j结尾)的最长公共子数组的长度 
// base case dp[i][0]和dp[0][i]都是0
for i := 1; i <= m; i++ {
    for j := 1; j <= n; j++ {
        if nums1[i-1] == nums2[j-1] {
            dp[i][j] = 1 + dp[i-1][j-1]
            ans = max(ans, dp[i][j])
        }
    }
}
// 在公共子数组中必须要有ans来保存最大长度，因为dp[m][n]是最大的，因为子数组是连续的，dp[m][n]表示的是分别以m和n结尾的子数组了，以他们两个结尾不一定最大

// dp[i][j]表示的是nums[0..i](以i结尾)和nums[0..j](以j结尾)的最长公共子序列的长度 
for i := 1; i <= m; i++ {
    for j := 1; j <= n; j++ {
        if nums1[i-1] == nums2[j-1] {
            dp[i][j] = 1 + dp[i-1][j-1]
        } else {
            dp[i][j] = max(dp[i-1][j], dp[i][j-1])
        }
    }
}
// 在公共子序列中就不需要ans来保存最大长度，因为dp[m][n]一定是最大的，因为子序列不一定是连续的，肯定是在[0,m]和[0,n]这个范围内最大
```