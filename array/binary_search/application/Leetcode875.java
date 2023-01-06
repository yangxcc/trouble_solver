/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-01-06 21:26:30
 * @LastEditTime: 2023-01-06 21:54:18
 */
package array.binary_search.application;

/**
 * leetcode 875 middle 爱吃香蕉的珂珂
 * 
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。
 * 每个小时，她将会选择一堆香蕉，从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。  
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * 
 */
public class Leetcode875 {
    public static int minEatingSpeed(int[] piles, int h) {
        int minEatSpeed = 1, maxEatSpeed = 0;
        for (int p : piles) {
            maxEatSpeed = Math.max(maxEatSpeed, p);
        }

        while (minEatSpeed <= maxEatSpeed) {
            int mid = minEatSpeed + (maxEatSpeed - minEatSpeed) / 2;
            if (findH(piles, mid) == h) {
                maxEatSpeed = mid - 1;
            } else if (findH(piles, mid) < h) {
                // 吃的太快了，findH太小了，让mid小点
                maxEatSpeed = mid - 1;
            } else if (findH(piles, mid) > h) {
                minEatSpeed = mid + 1;
            }
        }

        System.out.println(findH(piles, minEatSpeed));
        return minEatSpeed;
    }

    public static long findH(int[] piles, int eatSpeed) {
        // 将h取值为long，避免值溢出
        long h = 0;
        for (int p : piles) {
            // 优化一下
            h += p / eatSpeed;
            if (h % eatSpeed != 0) {
                h++;
            }
            // if (p <= eatSpeed) {
            //     h++;
            // } else {
            //     while (p > 0) {
            //         p -= eatSpeed;
            //         h++;
            //     }
            // }
        }
        return h;
    }

    public static void main(String[] args) {
        int[] piles = new int[]{805306368,805306368,805306368};
        int x = minEatingSpeed(piles, 1000000000);
        System.out.println(x);
    }
}
