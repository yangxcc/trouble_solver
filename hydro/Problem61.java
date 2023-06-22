package hydro;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1176
 * 
 * 问题描述：给定一个数组,从中选出若干个不相交的子数组，满足各个子数组的和都相等.这样称为一次合法筛选。
 * 塔子哥现在想选出尽量多的子数组，求这个最多的个数。前提是满足合法筛选哦~
 * 
 * 输入描述：第一行输入为数组长度 N，1≤N≤1000。
 * 第二行为N个用空格分开的整数 (−100000≤Ci≤100000)。
 * 
 * 输出描述：一行,一个整数 M，表示满足要求的最多的组内子序列的数目。
 * 
 */
public class Problem61 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // 枚举所有的区间和 以及 区间
        HashMap<Integer, List<int[]>> memo = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += arr[i];
                List<int[]> tmp = memo.getOrDefault(sum, new ArrayList<>());
                tmp.add(new int[] { i, j });
                memo.put(sum, tmp);
            }
        }

        int ans = 0;
        // 合并每个sum对应的列表，找到最多的子数组
        for (int key : memo.keySet()) {
            List<int[]> qujian = memo.get(key);
            Collections.sort(qujian, (a, b) -> {
                return a[0] - b[0];
            });
            int leftBound = qujian.get(0)[0];
            int rightBound = qujian.get(0)[1];

            int count = 1;
            for (int i = 1; i < qujian.size(); i++) {
                int left = qujian.get(i)[0];
                int right = qujian.get(i)[1];

                if (rightBound >= left) {
                    // 有交集
                    rightBound = Math.min(right, rightBound);
                } else {
                    rightBound = right;
                    count++;
                }
            }

            ans = Math.max(ans, count);
        }

        System.out.println(ans);
        in.close();
    }
}
