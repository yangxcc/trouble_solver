/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-06-10 20:34:50
 * @LastEditTime: 2023-06-10 21:12:53
 */
package hydro;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 问题来源：https://codefun2000.com/p/P1138
 * 
 * 问题描述：塔子哥写下了一排n个数(n≤2)，依次用加号连接。例如，塔子哥可能写下了如下的式子1+4+7+4+2+3+1共7个数以及6个加号。
 * 假设塔子哥每次选择一个加号，将它改变成加减乘除中的一个(每次操作不对产生任何影响)，问题是需要计算整个式子的后续操作产生影响。
 * 
 * 输入描述：第一行一个整数n 。接下来一行n个整数a1,a2,....,an依次表示塔子哥初始写下的连加算式中的每一个数。
 * 接下来一个整数m ，表示塔子哥做了m次算数训练
 * 接下来2m个以空格分开数字和符号t1,o1,t2,o2,... ，其中ti为数字，oi是'+','-','*','/'(即加减乘除，不含引号)中的一个符号，表示第i次操作选定第
 * ti个加号，将其改变为了oi。对于所有的的数据2≤N≤50000，1≤M≤50000,1≤ai≤500,1≤ti<N,oiϵ(+,−,∗,/)
 * 
 * 输出描述：输出行m个整数，分别表示每次操作答案，结果四舍五入到第一位小数。
 * 
 * 
 * 这道题不难，但是通过这道题得学会一种更快处理输入的方法，因为这道题如果使用常规的Scanner中的方法，会超时
 * 这里使用BufferedReader，需要注意，使用它里面的方法的时候要处理异常，还需要注意BufferedReader是在java.io包中
 */
public class Problem48 {
    public static void main(String[] args) throws Exception{
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        // BufferReader只有一个方法 readLine
        int n = Integer.parseInt(in.readLine());
        double[] arr = Arrays.stream(in.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
        int sum = 0;
        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
        }

        int m = Integer.parseInt(in.readLine());
        String[] ele = in.readLine().split(" ");
        for (int i = 0; i < m; i++) {
            double tmpSum = sum;
            // 第几个加号
            int idx = Integer.parseInt(ele[2 * i]);
            String symbol = ele[2 * i + 1];
            if (symbol.equals("-")) {
                tmpSum = tmpSum - 2 * arr[idx];
            } else if (symbol.equals("*")) {
                tmpSum = tmpSum - arr[idx - 1] - arr[idx] + (arr[idx - 1] * arr[idx]);
            } else if (symbol.equals("/")) {
                tmpSum = tmpSum - arr[idx - 1] - arr[idx] + (arr[idx - 1] / arr[idx]);
            }

            System.out.printf("%.1f ", tmpSum);
        }
        

        in.close();
    }
}
