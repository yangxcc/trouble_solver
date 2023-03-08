# trouble_solver
🖊 刷题路线：总体思路是先用go刷一遍，再使用java刷一遍

- 先使用Golang根据[代码随想录](https://programmercarl.com/)的顺序刷一遍
- 第二遍使用java根据labuladong公众号的目录来刷，补齐代码随想录中没有的题


通过对比Golang和Java在leetcode上的提交记录，可以发现同样的算法Golang的内存消耗比Java小很多

## 文件命名规则

整体命名方式，不同的题目使用文件夹命名，保证最小一级的文件夹中只有2-4(包含测试文件)个文件

- go文件的命名方式：a_b.go
- java文件的命名方式AaBb.java，使用驼峰命名法

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

## 技巧记录
### 数组中常见的问题和技巧
- [二分查找](./array/binary_search/BinarySearch.java)，前提是数组已经排好序了，常规的二分查找有一个前提条件，那就是数组中没有重复元素，直接二分找就好了，对于有重复元素的数组来说使用二分寻找某个元素出现的左右边界，我习惯的做法是使用闭区间，这里左右边界都要记得处理两种异常情况，其实这里只需要记住返回值的意义，以左边界为例，返回的`left`表示的是左边有多少比输入target小的数，如果输入的target比任何数字都大，那么`left==nums.length()`，这是一种异常情况，如果target在数组中不存在，这是第二种异常情况；同理，右边界中输出的`right`表示的是右边有多少比target大的数
  > 二分的应用：最难理解的是[leetcode 410 hard 分割数组的最大值](array/binary_search/application/Leetcode410.java)
- [前缀和](./array/prefix_sum/range_sum/RangeSum.java)，常用于统计某个区间的和，对于二维数组中的前缀和最好看着图将四部分的代码写好
- [差分数组](./array/diff_array/modify_array/ModifyArray.java)，常用于某个区间频繁发生改动
- 双指针，就我个人而言，我感觉使用双指针的题目是最灵活的，最常见的有快慢指针、左右边界指针等
- [滑动窗口](array/slide_window/README.md)，我习惯滑动窗口的模板中，区间是`[left, right)`的，滑动窗口的使用过程中通常还需要HashMap来记录，需要注意的是滑动窗口使用的条件有两个：一是数组中的元素没有负数，二是最终的结果是连续的，过程也得是连续的。

> 一个数组问题中可以用到的小技巧：可以使用min, max函数优雅的避免索引越界，比如`int x = Math.max(0, a)`,[代码示例](array/prefix_sum/matrix_block_sum/matrix_block_sum.go)
> 二维数组映射到一维数组中的技巧，假设二维数组的大小是$m \times n$，映射到一维数组，一维数组的大小是`int[] arr = new int[m * n]`，对于二维数组中的一个坐标$(x,y)$，映射到一维之后是$x \times （n - 1） + y$，将二维数组映射到一维数组的原因通常是我们的一些模板，比如dijkstra中的distance数组、并查集中的parent数组等都是一维的，将二维压缩成一维能直接套进来，[代码示例](graph/minimun_spanning_tree/kruskal/Leetcode1584.java)
> 



**技巧 & 知识点记录**
1. 数组排序 `Arrays.sort(2Darray, (a, b) -> {return a[0] - b[0]; });`；列表排序`List<int[]>    Collections.sort(list, (a, b) -> {return a[0] - b[0];})` ；或者是都可以重写`Comparator类中的compare方法来实现`
2. 最小生成树涉及到的两个方法：kruskal算法和prim算法
   1. Kruskal算法的过程：先对图中的所有边从小到大排序，然后依次将边加入到mst中，如果加边过程中碰到了环，那就跳过此边（明显使用并查集）
   2. Prim算法的过程：任意选择一个节点，以迭代的方式找出最小生成树中各结点权重最小的边，并加到最小生成树中，同样加边过程中如果遇到环，也跳过。（使用优先级队列快速得到节点邻居中的最短边，还需要一个boolean数组记录图中节点是否全部加入到mst中）
3. 一般在找最短路径的时候，使用BFS，否则还是使用DFS，因为BFS的空间复杂度高
4. 有两个数`M`和`N`，在大多数的编程语言中，`M / N`会向下取整，`M + (N-1) / N`会向上取整
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

6.  滑动窗口模板
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
7.  一些比较重要的位运算
    1.  **n & (n - 1)**来统计1的个数
    ```java
    // 如果n是2的幂，那么有 
    (n & (n - 1)) == 0  // 见leetcode 231 simple 2的幂，本质上这个位运算的目的是判断n这个数的二进制表示中是否只有一个1

    // 通过上面这个式子同样能够判断数字n的二进制表示中有几个1，如下
    while (n > 0) {
        n = n & (n - 1);
        ans++;
    }
    ```
    2. 异或运算
       - 基本性质：`0 ^ n = n;  n ^ n = 0`\

    3. 求某个数n二进制的最右边的1：`n & (~n + 1)`
    4. 

8.      