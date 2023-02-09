/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-11-18 15:04:44
 * @LastEditTime: 2023-02-09 21:19:41
 */
package greedy.gas_station;

/**
 * leetcode 134 middle 加油站
 * 
 * 在一条环路上有 n 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。
 * 你从其中的一个加油站出发，开始时油箱为空。
 * 给定两个整数数组 gas 和 cost ，如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1 。
 * 如果存在解，则 保证 它是 唯一 的。
 */
public class GasStation {
    /**
     * 贪心算法给予一个基础：如果从第i个加油站出发，恰好能够到达第j个加油站，那么从[i, j]这个区间内的任何一个加油站出发都不能到达j
     * 这是因为从i到j-1每一次的剩余油量都是大于0的，到了j恰好变为0，如果从中间站出发，到了j会变成负数
     */
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += gas[i] - cost[i]; // gas[i] - cost[i]表示的是经过i加油站的油量变化
        }
        if (sum < 0) {
            return -1;
        }

        int start = 0;
        int restGas = 0;
        for (int i = 0; i < n; i++) {
            restGas += gas[i] - cost[i];
            if (restGas < 0) {
                start = i + 1; // [0, i]之间的加油站都不能作为起点了
                restGas = 0;
            }
        }

        return start == n ? 0 : start;
    }
}