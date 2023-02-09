<!--
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-08 20:31:23
 * @LastEditTime: 2023-02-09 10:54:12
-->
扫雷游戏中地雷的位置就是随机的，这个过程是如何实现的呢？

首先，二维地图（坐标）可以转换成一维的，如下：
```java
class Array2D {
    int m; // 行
    int n; // 列
    boolean[] board; // true表示有地雷，false表示没地雷

    public Array2D() {
        this.board = new boolean[m * n];
    }

    // 将二维坐标对应成一维索引
    public int encode(int x, int y) {
        return x * n + y;
    }

    // 将一维索引转换成二维坐标
    public int[] decode(int index) {
        return new int[]{index / n, index % n};
    }
}
```
接下来我们要做的就是在`[0, m * n)`区间范围内找到随机k个位置，放置炸弹

但是我们能够仅仅简单的从这个一维数组中随机选出k个位置来吗？

答案是否定的，因为我们很难保证随机数不重复，尤其是k很接近`m*n`的时候

那么如何解决这个问题呢？

第一个解决方式就是通过[洗牌算法，又称随机乱置算法](Shuffle.java)，在数组的前k个位置上放上炸弹，然后再打乱数组
> 洗牌算法的证明过程：
> - 首先，数组打乱共有`n!`种可能性
> - 到shuffle算法中：
> - 对于nums[0]，我们把它随机换到索引[0, n)上，共有n种可能
> - 对于nums[1]，我们把它随机换到索引[1, n)上，共有n-1种可能
> - 对于nums[2]，我们把它随机换到索引[2, n)上，共有n-2种可能
> 根据数学归纳法，shuffle算法共有`n!`种可能性，所以是正确的
>

**洗牌算法生效的前提是使用数组这种数据结构，如果是在链表这种数据结构中选择k个元素，就不能使用洗牌算法了**

对于在链表中随机选k个节点这种（当然也可以在数组中选k个），可以通过[水塘抽样算法，reservior sample](ReserviorSample.java)，当`k==1`时，对于第i个元素按照`1/i`的概率选择该元素，`1 - 1/i`的概率保持原有的选择。
> 证明过程：$\frac{1}{i} \times (1 - \frac{1}{i}) \times (1 - \frac{1}{i + 1}) \times (1 - \frac{1}{i + 2}) \quad \cdots$
>
> 表示的是第i个元素就是最终选择的结果为$\frac{1}{n}$，是符合预期的
>

推广一下，对于在数组/链表中随机选择k个元素，第i个元素的保持现状概率为$1- k / i$，选择第i个元素的概率为$k / i$。
> 证明过程 $\frac{k}{i} \times (1 - \frac{k}{i} \times \frac{1}{k}) \times (1 - \frac{k}{i + 1} \times \frac{1}{k}) \times (1 - \frac{k}{i + 2} \times \frac{1}{k}) \quad \cdots$
> 
> 这里需要乘上$\frac{1}{k}$，因为需要将概率均摊到每个元素上

代码如下：
```java
public int[] getKRandomFromLinkedList(ListNode head, int k) {
    Random rand = new Random();
    ListNode cur = head;
    int[] res = new int[k];

    // 前k个元素先默认选上
    for (int i = 0; i < k && cur != null; i++) {
        res[i] = p.val;
        cur = cur.next;
    }

    int i = k;
    while (cur != null) {
        i++;
        int j = rand.nextInt(i);
        if (j < k) {
            res[j] = cur.val;
        }
        cur = cur.next;
    }

    return res;
}

/**
 * 在区间[lo, hi)内选择k个元素
 */
public int[] getKRandomFromArray(int lo, int hi, int k) {
    Random rand = new Random();

    int[] res = new int[k];
    // 先选上k个元素
    for (int i = lo; i < k; i++) {
        res[i] = lo + i;
    }

    int i = k;
    while (i < hi) {
        i++;
        int j = rand.nextInt(i);
        if (j < k) {
            res[j] = lo + i - 1;
        }
    }

    return res;
}
```


上面说了随机数的两种生成方式，洗牌算法和水塘抽烟算法，那么如何验证是否生成的足够随机呢？

有一种方式是使用蒙特卡洛验证法，其思想可以通过这个例子概括：一个正方形里面紧贴着一个圆，向这个正方形中大量打点，如果我们能够知道在园内和圆外的点数，就能估算出圆周率

```java
public static void main(String[] args) {
    // 在 [12, 22) 中随机选 3 个数
    int lo = 12, hi = 22, k = 3;
    // 记录每个元素被选中的次数
    int[] count = new int[hi - lo];
    // 重复 10 万次
    int N = 1000000;
    for (int i = 0; i < N; i++) {
        int[] res = sample(lo, hi, k);
        for (int elem : res) {
        // 对随机选取的元素进⾏记录
        count[elem - lo]++;
        }
    }
 System.out.println(Arrays.toString(count));
}
```
在算法正确的情况下，输出的数组中[12,22]区间内各个元素被选中的次数大致相同


还有一个问题，如何对带有权重的样本进行随机抽取，比如给你一个数组w，每个元素`w[i]`代表权重，写一个算法，按照权重随机抽取，比如`w=[1, 99]`，算法抽取到索引0的概率是1%，抽取到索引1的概率是99%，见[按权重随机选择](RandomPickWithWeight.java)