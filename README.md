# trouble_solver
分别使用Java和Golang解决算法题

## ACM模式中的输入输出处理
一直在leetcode上刷题，只需要填充代码逻辑就好，不需要自己处理输入输出，但是很多公司的笔试都是ACM模式，所以在这里整理一下ACM模式下的格式问题？（主要是处理输入输出）
- 首先，ACM模式下类名必须得是Main
- 输入使用java.util.Scanner来处理，固定写法
- ACM模式下所有依赖包的import都得自己来处理，一般都是util下的包，这里我现在的做法是直接`import java.util.*;`

```java
// 固定写法
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        /**
         * 如果题目中的要求是数组，他肯定会告诉我们数组的长度
         */
        int arrayLength = in.nextInt();
        int[] arr = new int[arrayLength];
        for (int i = 0; i < arrayLength; i++) {
            arr[i] = in.nextInt();
        }

        /**
         * 如果题目中的要求是链表，他肯定也会告诉我们链表的长度，但是会告诉我们后面不要再使用了
         * 比如 https://www.nowcoder.com/practice/54404a78aec1435a81150f15f899417d?tpId=37&tags=&title=%E9%93%BE%E8%A1%A8&difficulty=0&judgeStatus=&rp=1&sourceUrl=%2Fexam%2Foj%2Fta%3Fpage%3D1%26tpId%3D37%26type%3D37&gioEnter=menu
         * 
         * 而且这道题目中，还是用了连续输入，不是连续使用，不用使用hasNext()
         * 注意：不是链表必须连续输入，这是因为上面示例题中是这么输入的
         */
        while (in.hasNext()) {
            int n = in.nextInt();
            ListNode dummy = new ListNode();
            ListNode cur = dummy;
            for (int i = 0; i < n; i++) {
                cur.next = new ListNode(in.nextInt(), null);
                cur = cur.next;
            }

            // 逻辑处理...
        }

        /**
         * 如果题目中要求的是字符串
         */
        String str = in.nextLine();


        /**
         * 输出
         */
        System.out.println(); // 按照格式输出
    }
}

class ListNode {
    int val;
    ListNode next;

    public ListNode() {

    }

    public ListNode(int v, ListNode n) {
        this.val = v;
        this.next = n;
    }
}

```

[2022年9月底到2023年2月中旬算法题历时5个月算是过了一遍了，从今往后每天都得再看一看，不能忘了]


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
5. 最小生成树涉及到的两个方法：kruskal算法和prim算法
   1. Kruskal算法的过程：先对图中的所有边从小到大排序，然后依次将边加入到mst中，如果加边过程中碰到了环，那就跳过此边（明显使用并查集）
   2. Prim算法的过程：任意选择一个节点，以迭代的方式找出最小生成树中各结点权重最小的边，并加到最小生成树中，同样加边过程中如果遇到环，也跳过。（使用优先级队列快速得到节点邻居中的最短边，还需要一个boolean数组记录图中节点是否全部加入到mst中）
6. 一般在找最短路径的时候，使用BFS，否则还是使用DFS，因为BFS的空间复杂度高
7. 有两个数`M`和`N`，在大多数的编程语言中，`M / N`会向下取整，`M + (N-1) / N`会向上取整
8. 回溯和dfs的区别
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

9.  滑动窗口模板
    ```java
    public void slidWindow(String s, String t) {
        HashMap<Character, Integer> record = new HashMap<>();
        for (char ch : t.toCharArray()) {
            record.put(ch, record.getOrDefault(ch, 0) + 1);
        }

        HashMap<Character, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int valid = 0;

        while (left <= right) {
            char ch = s.charAt(right);
            right++;

            // 将ch加入窗口（有可能需要判断一下是否加入窗口，比如leetcode 76 hard 最小覆盖字串和leetcode438 middle 找到字符串中所有字母异位词）

            // 满足了窗口所有的条件
            while (window need shrink) {
                char l = s.charAt(left);
                left++;
                // 其他的条件判断
            }
        }

        return;
    }
    ```