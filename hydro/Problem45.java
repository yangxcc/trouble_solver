package hydro;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1088
 * 
 * 问题描述：塔子哥决定从长度为N彩带上截取一段，使得这段彩带上的颜色种类不超过K种。
 * 但是，他希望这段彩带尽量长，这样才能在蜡烛上缠绕出更加炫目的效果。为了尽快完成设计，
 * 他来找你求助，希望你能帮他设计出一种截取方法，使得截取出来的彩带尽可能长，并且颜色种类不超过K种。
 * 
 * 输入描述：第一行两个整数N,K，以空格分开，分别表示彩带有N厘米长，你截取的一段连续的彩带不能超过K种颜色。
 * 接下来一行N个整数，每个整数表示一种色彩，相同的整数表示相同的色彩。1≤N,K≤5000，彩带上的颜色数字介于[1,2000]之间
 * 
 * 输出描述：一行，一个整数，表示选取的彩带的最大长度。
 * 
 * 这道题目做出来了，使用滑动窗口
 */
public class Problem45 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        int[] arr = new int[n];
        in.nextLine();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        HashMap<Integer, Integer> window = new HashMap<>();
        int left = 0, right = 0;
        int count = 0;
        while (right < n) {
            int val = arr[right];
            right++;

            window.put(val, window.getOrDefault(val, 0) + 1);

            while (window.size() > k) {
                int leftVal = arr[left];
                left++;

                if (window.get(leftVal) == 1) {
                    window.remove(leftVal);
                } else {
                    // > 1
                    window.put(leftVal, window.get(leftVal) - 1);
                }
            }

            count = Math.max(count, right - left);
        }

        System.out.println(count);
        in.close();
    }
}
