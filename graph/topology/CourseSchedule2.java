/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-02 13:52:06
 * @LastEditTime: 2023-02-02 14:32:02
 */
package graph.topology;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode 210 middle 课程表II
 * 
 * 返回你为了学完所有课程所安排的学习顺序。
 * 可能会有多个正确的顺序，你只要返回 任意一种 就可以了。如果不可能完成所有课程，返回 一个空数组 。
 */

// 此题目的目的就是为了让我们求一个拓扑排序
// 拓扑排序的定义：图能够转化成所有节点都在一条线上，而且节点之间边的方向都是统一的
// 从定义中，也能够看出，只要是图中无环，那么就存在拓扑排序 
// 拓扑排序是 后序遍历结果的逆
public class CourseSchedule2 {
    boolean hasCycle;
    boolean[] onPath;
    boolean[] visited;
    List<Integer> postOrder;

    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] ans = new int[numCourses];
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        postOrder = new ArrayList<>();

        HashMap<Integer,List<Integer>> graph = buildGraph(prerequisites, numCourses);

        for (int i = 0; i < numCourses; i++) {
            dfs(graph, i);
            // 有环，无法拓扑
            if (hasCycle) {
                return new int[]{};
            }
        }

        Collections.reverse(postOrder);
        for (int i = 0; i < numCourses; i++) {
            ans[i] = postOrder.get(i);
        }
        return ans;
    }

    public void dfs(HashMap<Integer, List<Integer>> graph, int start) {
        if (onPath[start]) {
            hasCycle = true;
            return;
        }

        if (visited[start] || hasCycle) {
            return;
        }

        visited[start] = true;
        onPath[start] = true;

        for (int neighbor : graph.get(start)) {
            dfs(graph, neighbor);
        }

        // 从后序位置添加
        postOrder.add(start);
        onPath[start] = false;
    }


    public HashMap<Integer, List<Integer>> buildGraph(int[][] graph, int nodeNums) {
        HashMap<Integer, List<Integer>> ans = new HashMap<>();
        for (int i = 0; i < nodeNums; i++) {
            ans.put(i, new ArrayList<>());
        }
    
        for (int[] edge : graph) {
            int from = edge[1];
            int to = edge[0];
            ans.get(from).add(to);
        }
    
        return ans;
    }


    int[] indgree;
    public int[] findOrderBFS(int numCourses, int[][] prerequisites) {
        indgree = new int[numCourses];
        HashMap<Integer,List<Integer>> graph = buildGraph(prerequisites, numCourses);
        for (int[] edge : prerequisites) {
            indgree[edge[0]]++;
        }

        Deque<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < numCourses; i++) {
            if (indgree[i] == 0) {
                q.addLast(i);
            }
        }

        int count = 0;
        int[] path = new int[numCourses];
        while (!q.isEmpty()) {
            int cur = q.pollLast();
            path[count] = cur;
            for (int neighbor : graph.get(cur)) {
                if (--indgree[neighbor] == 0) {
                    q.addLast(neighbor);
                }
            }
            count++;
        }

        boolean hasCycle = count != numCourses;
        if (hasCycle) {
            return new int[]{};
        } 

        return path;
    }
}
