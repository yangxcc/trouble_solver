package graph.cycle_detect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * leetcode 207 middle 课程表
 * 
 * 你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
 * 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，
 * 其中 prerequisites[i] = [ai, bi] ，表示如果要学习课程 ai 则 必须 先学习课程 bi 。
 * 
 * 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 .
 * 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。
 */

// 如果图中无环，那么就能够正常上完所有的课程
public class CourseSchedule {
    boolean[] onPath; // 当前节点是否已经出于路径上
    boolean hasCycle;
    boolean[] visited; // 当前节点是否已经访问过了

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        onPath = new boolean[numCourses];
        visited = new boolean[numCourses];
        HashMap<Integer, List<Integer>> graph = buildGraph(prerequisites, numCourses);

        // 因为图中的每个节点不一定相连，所以这里对每个结点都遍历一下
        for (int i = 0; i < numCourses; i++) {
            dfs(graph, i);
        }

        return !hasCycle;
    }

    public void dfs(HashMap<Integer, List<Integer>> graph, int start) {
        if (onPath[start]) {
            hasCycle = true;
            return;
        }

        if (graph.get(start) == null) {
            return;
        }

        if (visited[start] || hasCycle) {
            return;
        }

        onPath[start] = true;
        visited[start] = true;
        for (int neighbor : graph.get(start)) {
            dfs(graph, neighbor);
        }
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

}
