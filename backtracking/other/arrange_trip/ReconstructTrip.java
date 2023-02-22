package backtracking.other.arrange_trip;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 332 middle 重新安排行程
 * 
 * 给你一份航线列表 tickets ，其中 tickets[i] = [fromi, toi] 表示飞机出发和降落的机场地点。
 * 请你对该行程进行重新规划排序。
 * 
 * 所有这些机票都属于一个从 JFK（肯尼迪国际机场）出发的先生，所以该行程必须从 JFK 开始。
 * 如果存在多种有效的行程，请你按字典排序返回最小的行程组合。
 * 
 * 例如，行程 ["JFK", "LGA"] 与 ["JFK", "LGB"] 相比就更小，排序更靠前。
 * 假定所有机票至少存在一种合理的行程。且所有的机票 必须都用一次 且 只能用一次。
 * 
 * 按照的是字典序排列的
 */
public class ReconstructTrip {
    List<String> ans;

    public List<String> findItinerary(List<List<String>> tickets) {
        int n = tickets.size();
        boolean[] visited = new boolean[n];

        Collections.sort(tickets, (a, b) -> {
            return a.get(1).compareTo(b.get(1));
        });

        LinkedList<String> path = new LinkedList<>();
        path.add("JFK");
        backtrack(tickets, visited, path);
        return ans;
    }

    public boolean backtrack(List<List<String>> tickets, boolean[] visited, LinkedList<String> path) {
        if (path.size() == tickets.size() + 1) {
            ans = new LinkedList<>(path);
            return true;
        }

        for (int i = 0; i < tickets.size(); i++) {
            if (!visited[i] && tickets.get(i).get(0).equals(path.getLast())) {
                path.addLast(tickets.get(i).get(1));
                visited[i] = true;

                // 必须得是boolean类型的返回值，不然AC不了
                if (backtrack(tickets, visited, path)) {
                    return true;
                }

                path.removeLast();
                visited[i] = false;
            }
        }

        return false;
    }
}
