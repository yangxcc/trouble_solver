package hydro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 问题来源：http://101.43.147.120/p/P1017
 * 问题描述：如何找到一个最大的正整数，使得该正整数所有数位之和等于给定的正整数 x，且每个数位都不相等（任意一个数位不能是 0 ）？
 * 输入描述：一个正整数x，1<=x<=100
 * 输出描述：如果不存在合法解，请输出 -1，否则输出最大的满足条件的正整数。
 * 
 * 
 * 之前做出来了，现在没能做出来，之前就直接使用的是回溯暴力破解的，给定的正整数x相当于target，每个数位不相等换种说法就是组合问题(路径)中不包含重复元素
 * 
 * 但是更好的办法肯定不是回溯，而是贪心
 */
public class Problem16 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();

        // 回溯穷举
        backtrack(1, n, new ArrayList<>());
        if (paths.size() == 0) {
            System.out.println(-1);
            return;
        }

        int ans = -1;
        for (List<Integer> path : paths) {
            // System.out.println(path.toString());
            Collections.sort(path); // !!!
            int tmp = 0;
            for (int i = path.size() - 1; i >= 0; i--) {
                tmp = tmp * 10 + path.get(i);
            }
            ans = Math.max(ans, tmp);
        }

        System.out.println(ans);
    }

    static List<List<Integer>> paths = new ArrayList<>();

    private static void backtrack(int idx, int target, List<Integer> path) {
        if (target == 0) {
            paths.add(new ArrayList<>(path));
            return;
        }

        for (int i = idx; i <= 9; i++) {
            target -= i;
            path.add(i);

            backtrack(i + 1, target, path);

            target += i;
            path.remove(path.size() - 1);
        }
    }
}
