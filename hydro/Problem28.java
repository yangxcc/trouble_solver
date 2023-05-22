/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-22 13:53:21
 * @LastEditTime: 2023-05-22 14:20:41
 */
package hydro;

import java.util.Scanner;

/**
 * 问题来源：https://codefun2000.com/p/P1030
 * 
 * 问题描述：塔子哥是一名摄影师，他刚刚拍摄了一组照片，但是由于光线等原因，照片的整体色调偏暗。
 * 他想要将每个像素点的亮度值都加上一个适当的值，来提高整张照片的亮度。
 * 同时，他希望加上的值不会使照片的亮度变得过于明亮或过于昏暗，而是尽可能地接近中间值 128。
 * 给定一个长度为 n 的数组 img，表示一张图像的 n 个像素点，每个像素点的取值范围为 [0,255] 的正整数。
 * 塔子哥需要找到一个整数 k，将数组 img 中的每个元素都加上 k，得到一个新的数组 newImg，
 * 使得 newImg 的所有像素的平均值最接近中位值 128。最后，输出这个整数 k。
 * 
 * 输入描述：输入 n 个整数，中间用空格分隔。 1≤n≤100
 * 
 * 输出描述：满足条件的整数k
 * 
 * 
 * 这道题目不难，重要的是得读懂题目
 */
public class Problem28 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] elements = in.nextLine().split(" ");
        int n = elements.length;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(elements[i]);
        }

        // 因为像素点的最大值是255，所以k的最小取值是-127
        // 因为像素点的最小值是0，所以k的最大取值是128
        int ans = -1;
        double flag = Double.MAX_VALUE;
        for (int k = -127; k <= 128; k++) {
            double avg = getAvg(arr, k);
            if (Math.abs(avg - 128) < flag) {
                flag = Math.abs(avg - 128);
                ans = k;
            }
        }

        System.out.println(ans);
        in.close();
    }

    private static double getAvg(int[] arr, int k) {
        double sum = 0;
        for (int ele : arr) {
            if (ele + k < 0) {
                sum += 0;
            } else if (ele + k > 255) {
                sum += 255;
            } else {
                sum += (ele + k);
            }
        }

        return sum / arr.length;
    }
}
