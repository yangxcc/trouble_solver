/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-04-23 21:44:00
 * @LastEditTime: 2023-04-23 21:54:53
 */
package daily;

import java.util.Arrays;

/**
 * leetcode 1105 middle 填充书架
 * 
 * 😢 没做出来 failure
 * 
 * 给定一个数组 books ，其中 books[i] = [thicknessi, heighti] 表示第 i 本书的厚度和高度。
 * 你也会得到一个整数 shelfWidth 。
 * 按顺序 将这些书摆放到总宽度为 shelfWidth 的书架上。
 * 
 * 先选几本书放在书架上（它们的厚度之和小于等于书架的宽度 shelfWidth ），然后再建一层书架。重复这个过程，直到把所有的书都放在书架上。
 * 需要注意的是，在上述过程的每个步骤中，摆放书的顺序与你整理好的顺序相同。
 * 
 * 例如，如果这里有 5 本书，那么可能的一种摆放情况是：第一和第二本书放在第一层书架上，第三本书放在第二层书架上，第四和第五本书放在最后一层书架上。
 * 每一层所摆放的书的最大高度就是这一层书架的层高，书架整体的高度为各层高之和。
 * 
 * 以这种方式布置书架，返回书架整体可能的最小高度。
 */
public class FillingBookcaseShelves {
    int[][] books;
    int shelfWidth;
    int[] memo;

    public int minHeightShelves(int[][] books, int shelfWidth) {
        int n = books.length - 1;

        this.books = books;
        this.shelfWidth = shelfWidth;
        this.memo = new int[n + 1];

        Arrays.fill(memo, -1);

        return dp(n);
    }

    /**
     * 放置[0, i]本书书架的最小高度
     */
    private int dp(int i) {
        if (i < 0) {
            return 0;
        }

        int ans = Integer.MAX_VALUE;
        int maxHeight = 0, restWidth = shelfWidth;

        if (memo[i] != -1) {
            return memo[i];
        }

        // 假设j是书架最后一行的第一本书
        for (int j = i; j >= 0; j--) {
            restWidth -= books[j][0];
            if (restWidth < 0) {
                break;
            }
            maxHeight = Math.max(maxHeight, books[j][1]);
            ans = Math.min(ans, dp(j - 1) + maxHeight);
        }

        memo[i] = ans;
        return ans;
    }
}
