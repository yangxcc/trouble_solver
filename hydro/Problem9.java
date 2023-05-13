package hydro;

import java.util.*;

/**
 * 题目来源：http://101.43.147.120/p/P1009
 * 题目描述：现在塔子哥给出两个整数a和b , 你现在有k 次操作，每次要么选择a，使其自增1，要么选择b使其自增1.
 * 现在塔子哥想知道，执行完 k 次操作后， a 和 b 的最大公约数最大可以是多少?
 * 输入描述：输入包含 T 组测试用例，第一行一个整数 T
 * 每行一组组测试用例，输入三个整数 a、b、k， 1<=T<=20, 1<=a,b,k<=100000
 * 输出描述：输出T行，每行一个整数，代表一个测试用例的答案
 */
public class Problem9 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int T = in.nextInt();
        in.nextLine();

        while (T > 0) {
            int a = in.nextInt(), b = in.nextInt(), k = in.nextInt();
            int ans = 0;
            for (int i = 1; i <= k - 1; i++) {
                ans = Math.max(ans, gcb(a + i, b + k - i));
            }
            System.out.println(ans);
        }
    }

    // 求a和b的最大公约数
    private static int gcb(int a, int b) {
        int rest = 1;
        do {    
            rest = a % b;
            a = b;
            b = rest;
        } while (b != 0);

        return a;
    }   
}

/**
 * 有的时候问题不要想太复杂了，最开始用的是dp，超时，加上备忘录之后，空间爆了
 */

// public class Main {
//     public static void main(String[] args) {
//         Scanner in = new Scanner(System.in);
//         int T = in.nextInt();
//         in.nextLine();
//         for (int i = 0; i < T; i++) {
//             int a = in.nextInt(), b = in.nextInt(), k = in.nextInt();
//             System.out.println(process(a, b, k));
//             in.nextLine();
//         }
//     }

//     private static int process(int a, int b, int k) {
//         if (k == 0) {
//             // 计算a，b的最大公约数
//             int r = 1;
//             do {    
//                 r = a % b;
//                 a = b;
//                 b = r;
//             } while (b != 0);

//             return a;
//         } else {
//             int ans = Integer.MIN_VALUE;
//             ans = Math.max(process(a + 1, b, k - 1), process(a, b + 1, k - 1));

//             return ans;
//         }
//     }
// }