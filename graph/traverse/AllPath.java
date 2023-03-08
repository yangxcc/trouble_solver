/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-02 09:35:39
 * @LastEditTime: 2023-03-03 19:32:40
 */
package graph.traverse;

import java.util.ArrayList;
import java.util.List;;

/**
 * leetcode 797 middle 所有可能的路径
 * 
 * 给你一个有 n 个节点的 有向无环图（DAG），请你找出所有从节点 0 到节点 n-1 的路径并输出（不要求按特定顺序）
 * graph[i] 是一个从节点 i 可以访问的所有节点的列表（即从节点 i 到节点 graph[i][j]存在一条有向边）。
 * 
 * 因为题目中规定了是无环图，所以visited数组是用不到的了
 */
public class AllPath {
    List<List<Integer>> ans;
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        ans = new ArrayList<>();
        dfs(graph, 0, new ArrayList<>());
        return ans;
    }

    public void dfs(int[][] graph, int start, List<Integer> path) {
        path.add(start);

        if (start == graph.length - 1) {
            ans.add(new ArrayList<>(path));
            // 一定不要忘记这里也要退出去！！
            path.remove(path.size() - 1);
            return;
        }

        for (int neighbor : graph[start]) {
            dfs(graph, neighbor, path);
        }

        path.remove(path.size() - 1);
    }
}