/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-20 20:11:24
 * @LastEditTime: 2023-05-20 21:04:13
 */
package hydro;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1029
 * 
 * 问题描述：塔子哥最近在做一个工程，工程里面有很多的指令，假设指令A ：a=1；指令 B：b=2×a ；
 * A 指令define了变量a，B 指令 use 了变量 a , 我们称 A 为 PRO(producer) ，B 为 CON(consumer)
 * ，他们之间的延时称为 latency
 * 若 A 和 B 之间有多条依赖，取最大的 Iatency , PRIORITY(A) 表示指令 A 的优先级，其计算公式如下：
 * PRIORITY(A)=PRIORITY(B)+latency ；若 A 有多个 CON(B1,B2) ，其 lategcy 分别为 (L1,L2) ：
 * PRIORITY(A)=max(PRIORITY(B1)+L1,PRIORITY(B2)+L2) ；
 * 若 A 没有 CON : PRIORITY(A)=0 ；现有若干条指令(数据依赖没有环)，编号分别为 1,2,...,n
 * 现需要根据其 PRIORITY 对其进行排序，若两条指令的PRIORITY 相同，则根据其编号先后进行排序。
 * 
 * 输入描述：第一行：指令数 n (最大不超过 100 )， 关系行数 m (最大不超过500)
 * 第 2一m+1 行：PRO−CON 关系，如 (1 2 4) 表示编号 1 和编号 2 指令存在 PRO−CON 关系，其 latency=4
 * 
 * 输出描述：输出最终的排序编号
 */
public class Problem27 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), m = in.nextInt();
        List<int[]>[] graph = new ArrayList[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] out = new int[n + 1];

        while (m > 0) {
            int from = in.nextInt();
            int to = in.nextInt();
            int val = in.nextInt();

            graph[to].add(new int[] { from, val });
            out[from]++;
            m--;
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            if (out[i] == 0) {
                pq.add(i);
            }
        }

        int[] priority = new int[n + 1];
        while (!pq.isEmpty()) {
            int cur = pq.poll();
            for (int[] ele : graph[cur]) {
                int from = ele[0];
                int val = ele[1];
                priority[from] = Math.max(priority[from], priority[cur] + val);
                // out[from]--;
                if (--out[from] == 0) {
                    pq.add(from);
                }
            }
        }

        // 这里得是integer，因为下面sort的时候得使用包装类
        Integer[] idx = new Integer[n];
        for (int i = 0; i <= n; i++) {
            idx[i] = i;
        }

        Arrays.sort(idx, (a, b) -> {
            if (priority[a + 1] == priority[b + 1]) {
                return a - b;
            }
            return priority[b + 1] - priority[a + 1];
        });

        for (int i = 0; i < n; i++) {
            System.out.println(idx[i] + 1 + " ");
        }

        in.close();
    }
}
