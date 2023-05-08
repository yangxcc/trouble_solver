/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-08 13:42:58
 * @LastEditTime: 2023-05-08 18:37:58
 */
package hydro;

import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 问题来源：http://101.43.147.120/p/P1005
 * 问题描述：现在塔子哥有 n 个汽车，所有的汽车都在数轴上，每个汽车有1.位置pos 2.速度v ，
 * 它们都以在数轴上以向右为正方向作匀速直线运动。
 * 
 * 塔子哥可以进行任意次以下操作：选择两个汽车交换它们的初始位置，但不交换速度。
 * 塔子哥希望操作完毕后的汽车永远不会相撞，请你帮塔子哥输出交换后每个汽车的初始位置和初始速度。(需要按输入顺序的汽车编号输出)
 * 
 * 输入描述：第一行一个整数n。接下来 n 行，每行两个整数 pos, v
 * 初始情况下保证没有两个汽车的位置相同。1≤n≤10^5, -10^9 ≤pos,v≤10^9
 * 
 * 输出描述：输出 n 行，每行输出两个整数，代表交换后每个汽车的位置和速度
 * 合法解不止一个，输出任意合法解即可。
 */
public class Problem5 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        // 按照速度从小到大排序，int[]是位置和速度
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }

            return Integer.compare(a[1], b[1]);
        });

        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[i] = in.nextInt();
            pq.add(new int[] { i, in.nextInt() });
        }
        Arrays.sort(pos);

        int[][] ans = new int[n][2];
        int i = 0;
        while (!pq.isEmpty()) {
            int[] pair = pq.poll();
            int idx = pair[0], speed = pair[1];
            ans[idx] = new int[]{pos[i++],speed};
        }

        for (int[] ele : ans) {
            System.out.println(Arrays.toString(ele));
        }
    }
}
