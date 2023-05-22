/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-22 20:31:07
 * @LastEditTime: 2023-05-22 20:51:21
 */
package hydro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1033
 * 
 * 问题描述：塔子希望知道他手头的 N 个向量可以构成多少个正方形。
 * 
 * 输入描述：第一行输入为 N 代表坐标数量，N为正整数。N≤100 之后的 K 行输入为坐标 x y 以空格分隔，
 * x ，y 为整数，−10≤x,y≤10
 * 
 * 输出描述：输出可以构成的正方形数量
 * 
 */
public class Problem31 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[][] arr = new int[n][2];
        for (int i = 0; i < n; i++) {
            arr[i] = new int[] { in.nextInt(), in.nextInt() };
        }

        backtrack(arr, 0, new ArrayList<>());
        System.out.println(count);
        in.close();
    }

    static int count = 0;

    // 最开始都统计到了一个List<List<int[]>>中，但是这样AC 6/8，有两个内存溢出
    private static void backtrack(int[][] arr, int idx, List<int[]> path) {
        if (path.size() == 4) {
            if (valid(path)) {
                count++;
            }

            return;
        }

        for (int i = idx; i < arr.length; i++) {
            path.add(arr[i]);

            backtrack(arr, i + 1, path);

            path.remove(path.size() - 1);
        }
    }

    /**
     * leetcode 593 middle 有效的正方形
     * @param path
     * @return
     */
    private static boolean valid(List<int[]> path) {
        int[] point1 = path.get(0);
        int[] point2 = path.get(1);
        int[] point3 = path.get(2);
        int[] point4 = path.get(3);

        return check(point1, point2, point3, point4) || check(point1, point3, point2, point4)
                || check(point1, point2, point4, point3);
    }

    private static boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
        int len1 = getLen(p1, p2);
        int len2 = getLen(p1, p4);
        int len3 = getLen(p2, p3);
        int len4 = getLen(p3, p4);

        int crossLen1 = getLen(p1, p3);
        int crossLen2 = getLen(p2, p4);

        return len1 == len2 && len2 == len3 && len3 == len4 && len1 != 0 &&
                crossLen1 == crossLen2 && crossLen1 == len1 && crossLen1 != 0;
    }

    private static int getLen(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
