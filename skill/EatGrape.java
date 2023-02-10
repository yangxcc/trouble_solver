/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-02-10 14:07:39
 * @LastEditTime: 2023-02-10 14:43:03
 */
package skill;

/**
 * 题目链接：https://www.nowcoder.com/questionTerminal/14c0359fb77a48319f0122ec175c9ada
 * 有三种葡萄，每种分别有 a, b, c 颗，现在有三个⼈，
 * 第⼀个⼈只吃第⼀种和第⼆种葡萄，
 * 第⼆个⼈只吃第⼆种和第三种葡萄，
 * 第三个⼈只吃第⼀种和第三种葡萄。
 * 现在给你输⼊ a, b, c 三个值，请你适当安排，让三个⼈吃完所有的葡萄，算法返回吃的最多的⼈最少要吃多少颗葡萄。
 * 
 * 首先，我们要考虑怎么能让吃的最多的人吃的最少 ---> (a+b+c)/3向上取整，即(a+b+c+2)/3，尽可能的平均分配
 */


/**
 * ACM模式
 */
import java.util.Scanner;
import java.util.Arrays;

public class EatGrape {
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int T = sc.nextInt();
        for (int i = 0; i < T; i++) {
            long[] arr = input();
            System.out.println(MaxEat(arr));
        }
    }
    
    public static long MaxEat(long[] arr) {
        Arrays.sort(arr);
        long sum = 0;
        for (long x : arr) {
            sum += x;
        }
        
        if (arr[0] + arr[1] > arr[2]) {
            // 三边还能够构成三角形，说明三人能平分这些糖果（分别取三条边的中点）
            return (sum + 2) / 3;
        }
        
        // 三条边构不成三角形了，但是差距很大，两条边的和还不如最长边的一半，所以我们选择让一个人吃完两种较少的糖果，剩下的最多的糖果两个人吃，吃的最多的向上取整就好了
        if (2 * (arr[0] + arr[1]) < arr[2]) {
            return (arr[2] + 1) / 2;
        }
        
        // 三条边构不成三角形了，但是差距不大，组成的一个四边形同样也能够被平分（把最长的那条边折一下，然后去三条边的中点）
        return (sum + 2) / 3;
    }
    
    public static long[] input() {
        long[] arr = new long[3];
        
        for (int j = 0; j < 3; j++) {
            if (sc.hasNextLong()) {
                arr[j] = sc.nextLong();
            }
        }
        
        return arr;
    }
}