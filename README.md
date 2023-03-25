<!--
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-09-25 11:41:57
 * @LastEditTime: 2023-03-25 17:07:07
-->
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
在做笔试题的时候，如果通过的用例数超过了75%，那么可能是发生了越界，比如有乘法或者加法存在的时候，这时候要注意使用long来代替以下试试看~

## 技巧记录
### 数组
- [二分查找](./array/binary_search/BinarySearch.java)，前提是数组已经排好序了，常规的二分查找有一个前提条件，那就是数组中没有重复元素，直接二分找就好了，对于有重复元素的数组来说使用二分寻找某个元素出现的左右边界，我习惯的做法是使用闭区间，这里左右边界都要记得处理两种异常情况，其实这里只需要记住返回值的意义，以左边界为例，返回的`left`表示的是左边有多少比输入target小的数，如果输入的target比任何数字都大，那么`left==nums.length()`，这是一种异常情况，如果target在数组中不存在，这是第二种异常情况；同理，右边界中输出的`right`表示的是右边有多少比target大的数
  > 二分的应用：最难理解的是[leetcode 410 hard 分割数组的最大值](array/binary_search/application/Leetcode410.java)
- [前缀和](./array/prefix_sum/range_sum/RangeSum.java)，常用于统计某个区间的和，对于二维数组中的前缀和最好看着图将四部分的代码写好
- [差分数组](./array/diff_array/modify_array/ModifyArray.java)，常用于某个区间频繁发生改动
- 双指针，就我个人而言，我感觉使用双指针的题目是最灵活的，最常见的有快慢指针、左右边界指针等
- [滑动窗口](array/slide_window/README.md)，我习惯滑动窗口的模板中，区间是`[left, right)`的，滑动窗口的使用过程中通常还需要HashMap来记录，需要注意的是滑动窗口使用的条件有两个：一是数组中的元素没有负数，二是最终的结果是连续的，过程也得是连续的。
- 顺子问题：有不少的题目中都会涉及到顺子问题，其实这种问题的思路也是比较简单，直接比较给出的几个数中的最大值和最小值，看看两者之间的差值是否超过了数的个数，比如
  ```java
  共有15张牌，2~10分别表示他们本身，A是1，JQK分别是11，12，13，判断5张扑克牌是不是顺子，大小王可以当作任意数字
    public boolean check(int[] nums) {  // nums中有5个数字,0表示大小王，可以代表任意数字
        // Arrays.sort(nums);
        // 用来记录出现的牌
        int[] helper = new int[14];
        for (int n : nums) {
            helper[n]++;
        }
        
        int max = -1;
        int min = 14;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                continue;
            }
            if (helper[nums[i]] > 1) return false;
            if (max < nums[i]) max = nums[i];
            if (min > nums[i]) min = nums[i];
        }
        return max - min < 5;
    }
  ```

> 小技巧1：可以使用min, max函数优雅的避免索引越界，比如`int x = Math.max(0, a)`,[代码示例](array/prefix_sum/matrix_block_sum/matrix_block_sum.go)
> 小技巧2：二维数组映射到一维数组，假设二维数组的大小是$m \times n$，映射到一维数组，一维数组的大小是`int[] arr = new int[m * n]`，对于二维数组中的一个坐标$(x,y)$，映射到一维之后是$x \times （n - 1） + y$，将二维数组映射到一维数组的原因通常是我们的一些模板，比如dijkstra中的distance数组、并查集中的parent数组等都是一维的，将二维压缩成一维能直接套进来，[代码示例](graph/minimun_spanning_tree/kruskal/Leetcode1584.java)


### 回溯
写在最前面：回溯的本质是**穷举**，常见的问题通常都是排列、组合问题或其变形，我们可以想象出他们的递归树来解决问题
- [排列](./backtracking/permutations/)，因为排列问题的递归树，无论在哪层（深度是多少），都需要遍历一遍数组，因此，在这个过程中需要用到`visited`数组来判断路径中是否已经存在了这个节点，此外，需要注意的是**去重**问题，对于存在重复元素的数组，首先要做的就是对数组排序，然后针对同一层来进行去重，代码为`if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) {continue;}`
- [组合](./backtracking/combination/)，在组合中，我们通常会碰到两种情况，一是能够重复选择元素，二是不能重复选择元素，组合中不需要`visited`数组，因为组合中的回溯函数规定好了每次递归时的起始位置，如果能重复选择那就是`backtrack(nums, i, path)`，不能重复选择那就是`backtrack(nums, i + 1, path)`，对于存在重复元素的数组，去重代码是`if (i > idx && nums[i] == nums[i - 1]) {continue;}`，这里其实还有一个小技巧，那就是对于数组排序，有利于剪枝操作
- [子集](./backtracking/sub/subset/)，子集问题其实和组合问题是一样的，唯一一点不同的是子集在进入递归函数之后不需要条件判断直接加入结果集就好；其中[将一个数组划分成和相等的k个子集](./backtracking/sub/subset/KSubset.java)这道题需要好好理解，从桶的角度和数字的视角来看
- [子序列](./backtracking/sub/subsequence/)，其中的题目[递增的子序列](./backtracking/sub/subsequence/Subsequence.java)给我们提供了另一种在同层去重的方式，因为是要求子序列，所以数组中元素的相对顺序是不能发生变化的，因此可以在递归函数中使用一个局部的set，局部的set不需要撤销选择，因为这个局部的set的范围只是这一层，到了下一层之后set就又会从头开始放元素
- 有的时候会给backtrack函数一个返回值，目前所做的题目中多数都是boolean类型，目前还没有想明白回溯函数返回值的定义时机

### 动态规划
- [背包问题](./dp/bag_problem/README.md)，0-1背包指的是每个物品的数量是确定的，而完全背包指的是物品数量是无限的，背包问题中dp数组的常见定义是：`dp[i][j]`表示的是前i个物品在背包容量为j的情况下能够达到的最大价值，至于零钱兑换中的定义，是因为硬币的个数是无限的，所以可以不考虑硬币，直接定义成`dp[amount + 1]`表示容量为amount的背包能够得到的最大价值，这可不是状态压缩；值得注意的是，当状态压缩时，即二维/三维dp数组压缩成一维/二维的时候，背包需要倒着遍历
  > 对于物品和背包的遍历顺序也是很有讲究的：
  > - 如果求组合数就是外层for循环遍历物品，内层for循环遍历背包。
  > - 如果求排列数就是外层for循环遍历背包，内层for循环遍历物品。

- [路径问题](./dp/path/)，根据我的经验，能够使用dp解决的路径问题是这样的：机器人只能够走上下左右中的两个方向，如果四个方向都能走，那就和岛问题类似了，得用dfs，dp中的路径问题主要集中在求路径数，求最小/大路径和。
- [子序列](./dp/subsequence_subarray/)，子序列可以是不连续的，其中的题目，有一些需要注意的地方
  - [t在s的子序列中出现的次数](./dp/subsequence_subarray/different_subsequence/)：注意有一种情况，即使`s.charAt(i) == s.charAt(j)`，也是有可能将i这个位置上的数删除的
  - [最长公共子序列LCS](./dp/subsequence_subarray/longest_common_subsequence/)：没啥好注意的，最常规的
  - [最长递增子序列](./dp/subsequence_subarray/longest_increasing_subsequence/)：`dp[i]`的含义是以i结尾的序列的最长递增子序列，所以还得用一个变量来接遍历过程中的最大值，而不是`dp[n]`
  - [最长回文子序列](./dp/subsequence_subarray/palindromic_subsequence/LongestPalindromicSubsequence.java)：这里需要注意的是判断是否为回文字符串，需要先知道该字符串的长度是奇数还是偶数，所以每次判断的时候都会以中间的一个位置和中间的两个位置同时比较
  - [统计一个字符串中的回文子串的数量](./dp/subsequence_subarray/palindromic_subsequence/Palindromic.java)：可以使用dp数组将时间复杂度从$O(n^3)$降到$O(n^2)$，`dp[i][j] = true`表示[i, j]区间内的字符串是回文子串
  
- [子数组](./dp/subsequence_subarray/)，子数组必须得是连续的，所以它还是和子序列有一些不同的，具体到代码实现中`dp[i][j]`表示的是以i结尾的数组1和以j结尾的数组2的最长公共子数组，因此需要一个ans来接受最大的结果，如果`nums1[i]`和`nums2[j]`不相等，那么就直接不用处理了
- [预测先手和后手的赢家](./dp/other/boyi/PredictWinner.java)：递归方法比较熟悉了，使用dp数组的方式得加强记忆，使用三维数组`dp[i][j][0]`表示在[i,j]区间内先手的最大获利，`dp[i][j][1]`表示在[i,j]区间内后手的最大获利
- [买卖股票](./dp/other/stock/AllConditions.java)：买卖股票问题有多种情况，比如只允许1次买卖，允许k次买卖，不限制买卖次数，买卖股票时有手续费，买卖股票后有冷冻期等。无论是冷冻期还是限制交易次数，都是在买入的时候进行操作。其dp数组的定义是`dp[i][0]`表示第i天持有股票的最大收益，`dp[i][1]`表示第i天不持有股票的最大收益
- [打家劫舍问题](./dp/other/robber/)：他可以和股票问题一样，使用二维dp来做，即`dp[i][0]`表示偷第i间房子能够获得的最大收益，但要记住打家劫舍问题中的一维dp，`dp[i]`表示的是从第i间屋子开始，最多能有多少获利，对于环形房间，那就只有三种情况：第一间房子偷，最后一件不偷；第一间房子不偷；最后一间偷，第一间和最后一间都不偷。对于二叉树形状组织的房间来说，我们可以使用后序遍历，即让dp函数返回多一些数据，`int[] dp(TreeNode cur)`返回的结果数组中包含两个数，`ans[0]`表示的是偷cur这间房子能够带来的最大收益，`ans[1]`表示的是不偷cur这间房子能够带来的最大收益
- [扔鸡蛋](./dp/other/egg/DropEgg.java)，可以从线性判断转到二分，其中`dp(k, n)`函数的意义是使用k个鸡蛋测试n层楼中f的最少操作次数
- [正则表达式的匹配](./dp/other/regular_expression/)：多看看其中的多种情况
- [最长的有效括号](./dp/other/parentheses/)：`dp[i]`的定义是以i结尾的最长的有效括号的长度，所以，当计算dp[i]的时候，不能仅仅简单的`dp[i]=dp[i-1]+1`，而是需要`pre = i - dp[i-1] - 1`，如果`pre>0`，`dp[i] += dp[pre-1]`

### 图
- [图遍历](./graph/traverse/AllPath.java)：遍历一般是限定在有向无环图上，因为无环，所以`visited`和`onpath`数组都不需要。其实有向无环图就是一个多叉树，所以对于无环图的遍历同样可以使用dfs和bfs两种方式
- [岛问题dfs](./graph/islands/)：一般在图中使用dfs的情况是这样的：从一个点出发，上下左右四个方向都能移动，所以这种情况下一般我们也还需要一个visited数组，来记录已经访问过的位置，但是由于岛问题的特殊性，我们可以通过修改值的方式来标记位置是否被访问过
- [bfs](./graph/bfs/)：一般在图中求两个节点之间的最短路径通常可以使用bfs（或者dijkstra，它能够得到一个点到图中所有点的最短距离），bfs的写法需要借助优先级队列，比如[打开转盘锁](./graph/bfs/Leetcode752.java)，这道题使用bfs很好理解，就是一层层的往下搜索，直到碰到target；再比如[滑动谜题](./graph/bfs/Leetcode773.java)，主要的难点在于需要把二维数组转换成一维的字符串，二维位置和一维位置的映射。
- [环检测算法](./graph/cycle_detect/CourseSchedule.java)：对于环检测算法，首先从本质上来看还是属于图的遍历问题，因为我们需要鉴定的图可能是有环的，所以需要`visited`和`onpath`两个数组，前一个数组的作用是节点是否被访问过，后一个数组的作用是节点是否在路径上了；或者使用bfs来检测是否存在环，维护一个优先级队列，只有入度为0的节点才会进入到优先级队列中，其中需要重点理解的是是否有环的判断：`count == numCourse`表示所有节点都遍历过，如果有环的话count会比较小，也就是说bfs不会遍历完所有的节点就会退出了
- [拓扑排序](./graph/topology/CourseSchedule2.java)：拓扑排序的定义是无环图中的所有节点能够形成一条直线，其中所有边都指向同一个方向，因此对于任何一个无环图都会有一个拓扑排序，而拓扑排序的求法是：**图后序遍历结果的逆**，或者就直接使用bfs使用一个path数组把结果给带出来（拓扑排序只需要在环检测算法的基础上稍微修改一下就好）
- [二分图](./graph/bipartite_graph/IsBipartiteGraph.java)：二分图的判定过程其实就是一个染色的过程，所谓染色，即为在遍历的过程中，将每条边的两个点标记为不同的颜色，如果所有的节点都能够被顺利的标记上，那说明这个图是能够被分成二分图的
- [最小生成树](./graph/minimun_spanning_tree/)：最小生成树指的是将图中所有节点连接起来的一个最短路径，求最小生成树的两种方法：[Kruskal](./graph/minimun_spanning_tree/kruskal)和[Prim](./graph/minimun_spanning_tree/prim)算法，前者的做法是将所有的边升序排列，从最小的边开始选择，直到包含了所有的点，在选择边的过程中需要保证点不会重复，所以Kruskal的实现可以借助并查集。后者的做法是以点的视角来看，每次都选择点的最短路径，因此实现过程中需要借助优先级队列，队列中存在的内容是目前遍历节点的边从下到大排列好的
- [dijkstra](./graph/dijkstra/Dijkstra.java)：dikstra算法的目的是计算出一个节点到其余全部节点的最短路径，其具体实现也是需要借助优先级队列维护一个`distance`数组，其中最难理解的是[K站中转内最便宜的航班](./graph/dijkstra/Leetcode787.java)这道题，这道题目[做](./dp/other/Leetcode787.java)更好一些
  


### 链表
在链表的题目中，一定要多加注意空指针的问题，尤其是循环中链表指针移动，要时刻注意`cur.next`之否为空了，如果已经为null了，一定不能再`cur.next.next`了
- [设计链表](./linkedlist/design_linkedlist/DesignLinkedlist.java)：可以使用单链表，也可以使用双链表，当使用双链表的时候，不要忘记该链表是一个环，所以当遍历的时候，不要忘记判断一下是否已经到了头节点了，题解涉及到了双向链表中的增、删、查三种操作
- [删除链表中的元素](./linkedlist/delete_element/RemoveLinkedlistElement.java)：给定一个值val，删除链表中所有值为val的节点，这种题型很明显是双指针，加上一个dummy节点就不需要考虑过于复杂的边界条件了，直接`pre==dummy, cur==head`往下走就行了
- [链表交叉](./linkedlist/intersect_linkedlist/GetIntersectNode.java)：两个链表可能存在一个相交的位置，这道题目的解法其实属于“双向奔赴”，本质上是如果两个链表相交，那么让他们相遇时走过的路是一样的
- [环形链表](./linkedlist/intersect_linkedlist/cycle_linkedlist/IsCycleLinkedlist.java)：对于一个链表，我们首先可以使用快慢指针来判断链表中是否有环，快指针每次走两步，慢指针每次走一步，如果有环，快慢指针总是会在环内相遇的。那么如果球的环形链表的入环节点呢？假设三段距离分别为x,y,z，那么当两个指针相遇时，慢指针走了x+y，**快指针走了x+y+n(y+z)**，因为快指针的速度是慢指针的2倍，所以会有这个式子2(x+y)=x+y+n(y+z)，可得x=(n-1)(y+z)+z，y+z是环的长度，走多少环无所谓，所以x=z，即当两个指针相遇时，将快指针移动到头部，然后快慢指针每次都移动一个位置，直到两者再次相遇，此时相遇点便是环的入口
- [删除倒数第k个节点](./linkedlist/remove_node_from_end/RemoveNodeFromEndOfList.java)：同样使用快慢指针，快指针先走k步，慢指针在开始出发，当`fast.next==null`的时候，慢指针的下一个节点刚好是倒数第k个节点，因此这样安排也不需要在使用一个pre指针了，但是这样也存在一种情况不行，这种情况就是链表中只有一个节点的时候，会出现空指针问题，因此我们需要使用一个dummy节点来让链表至少有两个节点，所以相应的slow和fast都从dummy出发，**fast也是先走k步就好了**，最后返回`dummy.next`
- [反转链表](./linkedlist/reverse_linkedlist/ReserveList.java)：首先，对于反转链表既可以使用递归，又可以使用迭代，我是用递归多一些，首先对于整个链表的反转，使用递归理解起来问题不大，然后是对固定区间的链表反转，假设左边界固定为0，右边界为right，反转前right个节点，需要注意的是`head.next == reverseN.next`这样写是不对的，因为reverseN是已经反转后的指针了，它的next根本不是原始链表中的next了，所以需要一个helper指针来保存原始链表中的reverseN的next。接下来我们就可以使用reverseN来实现reverseLeftBetweenRight了
- [对链表进行partiton](./linkedlist/split_linkedlist/SplitLinkedlist.java)：给定一个值x，将比x小的元素放到坐标，将比x大的放在右边，只不过这次不是在数组中，而是在链表中。对于链表中的partition，做法如下：分别使用两个dummy头来标识大于x的链表和小于x的链表，遍历一遍数组就能够将以dummy为头的两个链表拆开，其中需要注意的是需要有一个节点来记录链表中的下一个节点，因为我们每次连接之后都必须把cur后面的指针断开
- [两两交换链表中的节点](./linkedlist/swap_node/SwapNodeInPairs.java)：递归同样好理解一些，如果使用迭代的话，可以按照奇数位和偶数位上的值将链表拆开，拆链的最后一定不能忘记奇数链的最后一个地方要置为空，这是为了将奇数链上的最后一个节点后面的指针断开，拆分好了之后，需要把奇数链和偶数链依次合并

### 树
- [遍历](./tree/traversal/)：树的遍历方式有前序遍历、中序遍历、后序遍历和层序遍历，层序遍历的写法需要借助队列，前中后序遍历的递归写法比较熟悉了，在做树的题目时首先要确定使用哪种遍历方式，有的题目中使用后序遍历有奇效。三种的迭代方式也要会写
- [找祖先问题](./tree/ancestor/)：直接使用函数的定义递归就好了，在左右子树中分别找p，q的祖先，如果两个都不为空，那祖先就是root；在BST中找祖先能够根据BST的性质做一下剪枝，如果两个节点都大于0，直接在右树中找，以此类推
- [bst](./tree/bst/)：二叉搜索树的题目做题思路主要有两个：一个是通过与根节点的值比较，得知是从左子树上搜索还是右子树上搜索，第二个思路是bst的中序遍历是升序排列的；其中仍需要重点理解的是[bst转换成双向链表](./tree/bst/ConvertToLinkedList.java)，千万不要忘记中序遍历结束之后将首尾连接起来；比较有难度的是[不同的二叉搜索树II，将dp方法应用到树上的场景](./tree/bst/DifferenceBST.java)，[二叉树中的bst的最大键值和，使用后序遍历，返回的数据中能够判断以cur为根的树是否为bst以及他的和](./tree/bst/MaxSum.java)；要记住BST的验证需要携带上最小值和最大值，这也是为什么`二叉树中的bst的最大键值和`这道题目返回的是四个值，而不是两个值;此外还需要记住一种解题方式，在搜索二叉树中使用pre来记录前一个节点，因为bst中经常使用中序遍历，所以在相应位置加上对应的代码能够拿到当前节点的前一个节点，这种方式用到了很多题目中，比如[BST中的众数](./tree/bst/Mode.java)，[BST中的最小绝对差](./tree/bst/MinAbsoluteDifference.java)代码如下：
  ```java
  inOrder(root.left);
  if (pre == null) {
    head = root;
  } else {
    // 处理pre和cur
  }
  pre = cur;
  inOrder(root.right);

  // 还可以这么写
  inOrder(root.left);
  if (pre != null && 其他条件) {
    // 处理逻辑
  }
  pre = root;
  inOrder(root.right);
  ```
- [路径和问题](./tree/path_and_sum/)：[二叉树中的最大路径和](./tree/path_and_sum/MaxPathSum.java)是比较难的，这道题和[二叉树的直径](./tree/path_and_sum/Diameter.java)很相似，相同的做法。
- [重构二叉树](./tree/rebuild/Build.java)：使用先序+中序，后序+中序比较熟悉了，重点看[先序+后序](./tree/rebuild/Build.java)的做法
- [重复的子树](./tree/subtree/Leetcode652.java)：这道题目的解题思路是：当来到某个节点时，我们要做的实际上有两件事：第一件事是直到这颗子树的形状，第二件事是判断这颗子树是否重复，如果重复，则把这个根节点加入到结果集中，但还需要注意一点因为子树重复的次数可能是2次以上，所以这里使用HashSet会导致往结果集里面重复添加，因此使用HashMap
- 其他一些常规题型：[对称二叉树，需要分别使用左子树和右子树两个指针来判断](./tree/symmetry/symmetric_tree.go)，[翻转二叉树](./tree/reverse/InvertTree.java)。[将二叉树展开成链表](./tree/tolist/Leetcode114.java)。[判断是否为高度平衡的树](./tree/balance/Balance.java)，这道题目还是得加深记忆，记住这种做题模式，在求高度的过程总就能够得到是否为平衡树，因为只有是平衡树才输出树的高度。


### [排序算法](./sort/README.md)
- [冒泡排序](./sort/bubble/BubbleSort.java)：所谓冒泡，其实就是大的数往后走，每次只能确定一个数的位置，所以在实现中可以是用双层循环，外层循环只是用来控制每次遍历需要遍历的数组个数，内层循环的逻辑就是依次两两比较
- [选择排序](./sort/select/SelectSort.java)：选择排序的思想是选择[0, n]范围内最大的数，将这个最大的数放到n这个位置，然后再从[0, n-1]范围内选最大的数，将这个最大的数放到n-1的位置，依此类推...
- [插入排序](./sort/insert/InsertSort.java)：插入排序的核心思想是当将一个数插入到数组中时，假设这个数组是有序的。我们需要做的是找到这个数字的插入位置，然后插进去。~~这一个我自己写的时候写成了三层循环~~
```java
public void sort(int[] arr) {
    int n = arr.length;
    for (int i = 1; i < n; i++) {
        int curVal = arr[i];
        int pos = i - 1;
        // [0, pos]这个位置上
        while (pos >= 0 && arr[pos] > curVal) {
            arr[pos + 1] = arr[pos];
            pos--;
        }
        arr[pos + 1] = curVal;
    }
}
```
- [**快速排序**](./sort/quick/quick_sort.go)：快排是非常重要的一个排序，他的核心就是通过一个`partition`方法找到一个位置，这个位置的左边元素都比他小，右边元素都比他大，然后再通过递归的方式排好左边和右边；快排中需要注意的一个点是在最开始得将数组随机打乱，因为快排可能会退化成冒泡，比如654321，快排的应用：[TopK问题，partition的过程](./sort/quick/application/topK.go)
- [**归并排序**](./sort/merge/merge_sort.go)：归并排序也很重要，重点在merge函数上，merge函数的作用就是将两个有序数组合并，其应用也比较多，常用于快速计数
[堆排序](./sort/heap/HeapSort.java)：堆排序的思想是：如果是大根堆，那么我就要保证无论是添加元素还是删除元素，我的堆顶必须是目前堆中最大的元素，从上面的流程中也能够看出，堆在添加或删除元素时是需要动态调整的


### LRU和LFU
- [LRU](./lru_and_lfu/LRUCache.java)：最近最少使用算法，一种常见的页面替换算法，在面试中很常见，所有最好使用[LinkedHashMap](./lru_and_lfu/LRUCache.java)和[自己维护一个HashMap和双向链表](./lru_and_lfu/lru_cache.go)都能够熟练的写出来
- [LFU](./lru_and_lfu/LFUCache.java)：最近最不频繁使用，其实就是通过维护三个哈希表来解决这个问题

### 栈和队列
栈是先进后出，队列是先进先出
- [使用栈实现队列](./stack_and_queue/convert/Stack2Queue.java)：使用两个栈stack1和stack2，添加元素只往stack1中，弹出元素的时候需要判断一下stack2是否为空，如果不为空，那么就弹出stack2中的内容，如果为空，则把stack1中的内容全部放入到stack2中
- [使用队列实现栈](./stack_and_queue/convert/Queue2Stack.java)：使用一个队列就能够模拟栈，当想要弹出的时候，只需要将先弹出的n-1个数字再入栈，然后把第n个数弹出栈
- [单调栈](./stack_and_queue/monotonous/stack/Template.java)：单调栈用于解决的问题就是next greater element问题，因为栈的性质是先进后出，所有实现单调栈的时候是要倒序遍历数组的，所以当需要出栈的时候才是next greater element，对于单调栈的实现应该记牢，如下：
```java
int[] arr = new int[n];
for (int i = n - 1; i >= 0; i--) {
    while (!stack.isEmpty() && arr[i] > stack.peekFirst()) {
        stack.pop();
    }

    // 如果想求next grater element数组的话，可以直接在这里处理
    ans[i] = stack.isEmpty() : -1 : stack.peekFirst();
    stack.push(arr[i]);
}
// 虽然上面的代码中有一个for循环，有一个while循环，但实际上他们的时间复杂度还是O(n)，因为从整体上看，对于数组中的任何一个元素，最多是经历一遍出栈，经历一遍入栈
```
- [单调队列](./stack_and_queue/monotonous/queue/Template.java)：单调队列的实现我相对比较熟悉，单调队列的作用是既能够维护队列元素先进先出的顺序，又能够正确维护队列中所有元素的最值。思路总体上就是胖子压瘦子，代码如下：
```java
class MonotonousQueue {
    private Deque<Integer> q;

    public MonotonusQueue() {
        this.q = new ArrayList<>();
    }

    public void push(int x) {
        while (!q.isEmpty() && q.peekFirst() < x) {
            q.pollFirst();
        }
        q.addFirst(x);
    }

    // 之所以要在这里的pop方法中给定一个参数x，是因为这个在push的过程中x就已经被poll出去了
    public int pop(int x) {
        if (q.peekLast() == x) {
            q.pollLast();
        }
    }

    public int max() {
        return q.peekLast();
    }
}
```
单调队列的使用场景，[滑动窗口的最大值](./stack_and_queue/monotonous/queue/SlidingWindowMaximum.java)
- [括号问题有时候会借助栈来解决](./stack_and_queue/parentheses/)：比如[有效的括号](./stack_and_queue/parentheses/ValidParentheses.java)，但是通常对于只有单一类型的括号时，通常用不到栈，比如[平衡括号字符串的最少插入次数](./stack_and_queue/parentheses/BalanceParentheses.java)，使用一个变量来记录需要右括号的个数，另一个变量来记录遍历过程中一定需要的括号的数量，又比如[使括号有效的最少添加](./stack_and_queue/parentheses/MinimumAdd.java)，使用两个指针来记录左括号和右括号所需要的个数，这里需要注意的一点是不能仅仅是暴力的统计左括号和右括号的个数，在统计过程中需要考虑到遍历的顺序问题。

### 字符串
- [反转字符串](./string/reverse/)：如果仅仅是[最简单的反转一整个字符串](./string/reverse/ReverseString.java)，那就没啥好说的了，直接双指针一头一尾搞定。有一类题目是反转2k长度中的前k个位置，其中最有难度的就是[反转一个字符串中的单词](./string/reverse/ReverseWords.java)，分成三步解题，第一步先去掉字符串前后多余的空格，第二步去掉每个单词后面多余的空格，第三步反转整个字符串，然后再根据空格反转每个单词。此外，[反转/旋转字符串](./string/rotate/RotateWords.java)还有一种做法，那就是先反转局部，在反转整体（或者是先反转整体，在反转局部），这道题还有一种trick，那就是两个字符串拼接起来，通过n来截取
- [重复字符串](./string/repeat/)：[给定一个字符串，检查他是否由重复子串组成](./string/repeat/RepeatedSubstringPattern.java)，直接双循环来做，[去除重复的字母后得到具有最小字典序的字符串](./string/repeat/SmallestSubsequence.java)，这道题目可以借助单调队列的思想，只是出队只在一端，而且不能单纯的比较队首元素和当前字符，还需要看一下队首元素是否只有一个，如果是的话，则不能弹出。因为这道题目要求保留字符之间的相对顺序
- [KMP算法](./string/kmp/KMP.java)：kmp的目的就是为了判断一个字符串是否出现在另一个字符串中，重点是next数组怎么求，这里得背过了
- [比较好的题目：为运算表达式设置优先级](./string/Leetcode241.java)

### 随机问题
- [根据RandA和RandB生成RandC](./random/ImplementRand10UsingRand7.java)：比如说使用Rand7实现Rand10，具体应该怎么做呢？Rand7能够等概率的生成1-7这7个数，如果(Rand7 - 1) * 7，那么就能够等概率生成0，7，14，21，28，35，42这7个数，给这7个数加上一个Rand7，那么就能够等概率生成1-49这49个数，所以可以通过这样的方式来实现Rand10
  ```java
    int generate() {
        int x = (Rand7 - 1) * 7 + Rand7;
        while (x > 40) {
            x = (Rand7 - 1) * 7 + Rand7; // 大于40的时候重新生成一下
        }
        return x % 10 + 1;
    }
  ```
  所以，根据上面这个例子我们就能够总结出一个公式，根据RandA怎么实现RandB（A < B），公式为
  $(RandA - 1) \times RandA + RandA$，为了避免过多的浪费，所以我们需要找到一个数$n$，n是最接近 $(RandA - 1) \times RandA + RandA$且是RandB最大值的整数倍

- 扩展1：那么如果根据RandA和RandB生成RandAB呢，其中RandA能够等概率生成[1,A]范围内的数，RandB能够等概率生成[1,B]范围内的数，且`A != B`，我们需要达到的目标是等概率生成[1-A*B]范围内的数，这是公式为$(RandA - 1) \times B + RandB$或者$(RandB - 1) \times A + RandA$
- 扩展2：给定一个随机函数RandAB，它能够等概率生成[A,B]范围内的数，如何根据这个函数生成RandCD，即等概率生成[C,D]范围内的数。按照上面的思路继续，`RandA - A`能够等概率生成[0, B-A]范围内的数，如果再令`RandA-A/(B-A)`，那么生成的便是[0,1]范围内的数，再乘上`D-C`，那么生成的便是[0, D-C]范围内的数，再加上C，即等概率得到[C,D]范围内的数。综上，公式为$RandCD = \frac{RandAB - A}{B - A} \times (D - C) + C$

- [洗牌算法](random/Shuffle.java)：主要用途就是用于打乱数组，比如在快排开始之前，先打乱一下数组，其核心思路是对于一个位置`i`，都需要在区间[i, n-1]的范围内生成一个位置，用来交换，需要注意的是，这种思路只能够用在数组中
  ```java
  void shuffle(int[] arr) {
    Random rand = new Random();
    for (int i = 0; i < arr.length; i++) {
        int idx = rand.nextInt(n-i) + i;
        if (i != idx) {
            swap(arr, i, idx);
        }
    }
  }
  ``` 
- [随机采样算法](./random/ReserviorSample.java)：上面说到洗牌算法只能够用在数组上，而随机采样算法则能够用到链表中，即从链表中随机选出一个节点。具体逻辑如下：
  ```java
  int getRandom() {
    ListNode cur = head;
    int i = 0;
    ListNode res = null;
    Random rand = new Random();

    while (cur != null) {
        i++;
        // 生成一个[0, i)范围内的数
        int flag = rand.nextInt(i);
        if (0 == flag) {
            res = cur;
        }
        cur = cur.next;
    }

    return res.val;
  }
  ```
- [按照权重随机选择](./random/RandomPickWithWeight.java)：将一个数组中的值作为权重，按照权重来作为概率随机选择数字，解决这种问题的思路是求一下该数组的前缀和，通过产生一个随机数，根据随机数所在的区间来选择数据，具体如下：
  ```java
  Random rand = new Random();
  int[] preSum = new int[n + 1]; // 求前缀和的过程省略
  int select() {
    int r = rand.nextInt(preSum[n - 1]) + 1;
    // 找到r所在的区间
    int left = 0, right = n;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (preSum[mid] == r) {
            right = mid - 1;
        } else if (preSum[mid] < r) {
            left = mid + 1;
        } else if (preSum[mid] > r) {
            right = mid - 1;
        }
    }

    return left - 1;  // 因为preSum是比数组多了一个，所以这里需要-1
  }
  ```





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