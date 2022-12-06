# trouble_solver
分别使用Java和Golang解决算法题


规划刷题路线：总体思路是先用go刷一遍，再使用java刷一遍

- 先使用golang根据[代码随想录](https://programmercarl.com/)的顺序刷一遍
- 第二遍使用java根据labuladong公众号的目录来刷，补齐代码随想录中没有的题

🖊 后续补充吧...

通过对比golang和Java在leetcode上的提交记录，可以发现同样的算法golang的内存消耗比Java小很多

## 仓库规则（后续完善）

整体命名方式，不同的题目使用文件夹命名，保证最小一级的文件夹中只有2-4(包含测试文件)个文件

- go文件的命名方式：a_b.go
- java文件的命名方式AaBb.java，使用驼峰命名法

无论是go文件，还是java文件，文件的规范如下：
```
/**
作者注释
快捷键：ctrl + alt + i 生成头部注释
快捷键：ctrl + alt + t 生成函数注释
*/

/**
题目描述：xxx
示例：
*/
func method(strs []string, m int, n int) int {
}
```