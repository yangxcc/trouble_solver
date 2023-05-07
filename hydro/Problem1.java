/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-07 14:06:09
 * @LastEditTime: 2023-05-07 14:26:49
 */
package hydro;

import java.util.HashMap;
import java.util.Scanner;

/**
 * 题目来源：http://101.43.147.120/p/P1001
 * 题目描述：如果一个数组中的某个子段（子数组）,其中出现次数最多的元素出现大于等于x次， 被称为x-子段
 * 先给定一个数组a和x，试问a有多少字段是x-子段
 * 
 * 输入描述：第一行输入两个整数 n小于等于10000和x小于等于n， 1≤a_i≤n；第二行输入n个整数
 * 输出描述：输出给定的数组中有多少子数组是x-字段
 */

public class Problem1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), x = in.nextInt();
        int[] arr = new int[n];
        in.nextLine();
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }

        // System.out.println(process(arr));

        /**
         * 一个更好的办法，使用滑动窗口，这个其实最开始想到时候滑动窗口了，但是没想起来具体怎么弄，😔
         * 但是就是在统计数字出现的频次那里卡住了，实际上就可以使用数组来统计，而没必要非要去使用hashmap
         */
        int ans = 0, left = 0, right = 0;
        // 因为题目中已经说了，数组中的元素的最大值不超过10000，所以可以这么设置
        int[] count = new int[10001];
        while (right < n) {
            while (count[arr[right]] >= x) {
                ans += n - right;
                count[arr[left]]--;
                left++;
            }
            right++;
        }
        System.out.println(ans);
    }

    /**
     * 我最开始的方法，通过用例数7/11，其中3个是没通过，1个超时
     * 暴力统计
     */
    private static int process(int[] arr, int n, int x) {
        int ans = 0;
        for (int i = 0; i < n; i++) {
            HashMap<Integer, Integer> val2Freq = new HashMap<>();
            int maxFreq = 0;
            for (int j = i; j < n; j++) {
                // 统计[i,j]范围内出现频率最多的元素
                if (val2Freq.containsKey(arr[j])) {
                    int freq = val2Freq.get(arr[j]);
                    if (freq + 1 > maxFreq) {
                        maxFreq = freq + 1;
                    }
                    val2Freq.put(arr[j], freq + 1);
                    if (maxFreq >= x) {
                        if (j == n - 1 && i == 0) {
                            ans = 1;
                            break;
                        }
                        ans += n - j; // 最开始这里写错了，写成了j-i+1，不知道怎么想的
                        break;
                    }
                } else {
                    val2Freq.put(arr[j], 1);
                    if (maxFreq == 0) {
                        maxFreq = 1;
                    }
                    if (maxFreq >= x) {
                        ans += j - i + 1;
                        break;
                    }
                }
            }
        }

        return ans;
    }
}
