/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2022-10-01 12:45:23
 * @LastEditTime: 2023-04-16 10:10:57
 */
package array.slide_window;

import java.util.HashMap;

/**
 * leetcode 904. 水果成篮
 * 你正在探访一家农场，农场从左到右种植了一排果树。这些树用一个整数数组 fruits 表示，其中 fruits[i] 是第 i 棵树上的水果 种类 。
 * 你想要尽可能多地收集水果。然而，农场的主人设定了一些严格的规矩，你必须按照要求采摘水果：
 *     你只有 两个 篮子，并且每个篮子只能装 单一类型 的水果。每个篮子能够装的水果总量没有限制。
 *     你可以选择任意一棵树开始采摘，你必须从 每棵 树（包括开始采摘的树）上 恰好摘一个水果 。采摘的水果应当符合篮子中的水果类型。每采摘一次，你将会向右移动到下一棵树，并继续采摘。
 *     一旦你走到某棵树前，但水果不符合篮子的水果类型，那么就必须停止采摘。
 * 给你一个整数数组 fruits ，返回你可以收集的水果的 最大 数目。
 * 
 * 只包含两种元素的最长子数组
 */
public class FruitIntoBaskets{
    public int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> window = new HashMap<>();
        int left = 0, right = 0, ans = 0;
        
        while (right < fruits.length) {
            int num = fruits[right];
            right++;

            window.put(num, window.getOrDefault(num, 0)+1);

            while (window.size() > 2) {
                int le = fruits[left];
                left++;
                window.put(le, window.get(le)-1);
                if (window.get(le) == 0) {
                    window.remove(le);
                }
            }

            // 不能是==2，比如case [3,3,3,3,3,3]
            if (window.size() <= 2) {
                ans = Math.max(ans, right-left);
            }
        }

        return ans;
    }
}

class day0415 {
    public int totalFruit(int[] fruits) {
        HashMap<Integer, Integer> window = new HashMap<>();
        int ans = 0;
        int left = 0, right = 0;
        while (right < fruits.length) {
            int num = fruits[right];
            right++;

            window.put(num, window.getOrDefault(num, 0) + 1);

            while (window.size() > 2) {
                int n = fruits[left];
                left++;
                
                window.put(n, window.get(n) - 1);
                if (window.get(n) == 0) {
                    window.remove(n);
                }
            }

            ans = Math.max(ans, right - left);
        }

        return ans;
    }
}
