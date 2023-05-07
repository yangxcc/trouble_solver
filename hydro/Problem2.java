/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-07 14:30:09
 * @LastEditTime: 2023-05-07 20:49:21
 */
package hydro;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 题目来源：http://101.43.147.120/p/P1002
 * 题目描述：塔子哥要去学校接他的弟弟，但是他不想排队。他发现学校的学生放学回家有五个优先级，
 * 学习成绩越好回家时间越快，1 表示学习成绩最好，5 表示学习成绩最差。
 * 现在他知道如果他弟弟是当前学习成绩最好的学生，那么他可以插队到其他人前面去接他弟弟。
 * 给定一个学生的编号和他的学习成绩的时间表，输出每次被接回家的学生时的学生编号。
 * 如果有多个同级优先级的学生，按照到达顺序被接回家。
 * 
 * 输入描述：第一行输入一个正整数n，表示输入的序列中的事件数量 1≤n≤500
 * 接下来的n行，每行第一个字符为a或p
 * 当字符为a时，后面会有两个的正整数num和x，表示到来的学生编号为num，学习成绩为x
 * 当字符为p时，表示当前学习成绩优先级最高的学生被接回家
 * 
 * 输出描述：输出包含若干行，对于每个p ， 输出一行，仅包含一个正整数num , 表示学习成绩最好的学生被接回家的学生的编号。
 */
public class Problem2 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] == b[2]) {
                return Integer.compare(a[1], b[1]);
            } else {
                return Integer.compare(a[2], b[2]);
            }
        });

        for (int i = 0; i < n; i++) {
            char ch = in.next().charAt(0);
            if (ch == 'a') {
                pq.add(new int[]{i, in.nextInt(), in.nextInt()});
            } else {
                System.out.println(pq.poll()[1]);
            }
        }
    }

    /**
     * 思路是对的，使用优先级队列，不知道为什么就是通过不了用例
     */
    private static void process() {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        in.nextLine();
        int[][] record = new int[n][];
        for (int i = 0; i < n; i++) {
            String str = in.nextLine();
            if (str.charAt(0) == 'p') {
                record[i] = new int[] { i, 0, 0 };
            } else {
                record[i] = new int[] { i, str.charAt(2) - '0', str.charAt(4) - '0' };
            }
        }

        for (int[] ele : record) {
            System.out.println(Arrays.toString(ele));
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[2] == b[2]) {
                return a[0] - b[0];
            }
            return a[2] - b[2];
        });
        // 碰到0就输出
        for (int[] pair : record) {
            if (pair[1] == 0) {
                System.out.println(pq.poll()[1]);
            } else {
                pq.add(pair);
            }
        }
    }
}
