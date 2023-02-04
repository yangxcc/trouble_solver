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

从今天（2022/12/12）开始，参考labuladong刷题了，补充Java的做法的同时规范代码格式

**技巧 & 知识点记录**
1. 通过 min, max 函数优雅避免索引越界的技巧，[代码示例](array/prefix_sum/matrix_block_sum/matrix_block_sum.go)
2. 二维数组映射到一维数组的常见技巧，二维数组的大小$m \times n$，m行n列，创建一个一维数组，大小为$[ m \times n ]$，
   对于二维坐标$(x,y)$，映射成一维，就是 $x \times n + y$. [代码示例](graph/union_find/application/SurroundedRegions.java)
3. 二维数组坐标点可以使用索引，来将二维坐标点变成一位的索引，[代码示例](graph/minimun_spanning_tree/kruskal/Leetcode1584.java)  
4. 数组排序 `Arrays.sort(2Darray, (a, b) -> {return a[0] - b[0]; });`；列表排序`List<int[]>    Collections.sort(list, (a, b) -> {return a[0] - b[0];})` ；或者是都可以重写`Comparator类中的compare方法来实现`
5. 回溯和dfs的区别
   ```java
        // 回溯模板
        void backtrack(TreeNode root, List<Integer> path) {
            if (root == null) {
                return;
            }

            for (TreeNode child : root.children) {
                // 做选择
                path.add(child.val);

                // 回溯
                backtrack(child, path);

                // 撤销选择
                path.removeLast();
            }
        }

        // dfs模板
        void dfs(TreeNode root, List<Integer> path) {
            if (root == null) {
                return;
            }

            path.add(root.val);

            for (TreeNode child : root.children) {
                dfs(child, path);
            }

            path.removeLast();
        }
   ```
   可以看到，从代码角度，回溯的选择和撤销操作在for循环内，而dfs在for循环外，回溯算法关注的是树枝，而dfs关注的是节点