/*
 * @Author: yangxcc
 * @version: 1.0
 * @Date: 2023-05-22 20:05:46
 * @LastEditTime: 2023-05-22 20:14:35
 */
package skill;

/**
 * leetcode 593 middle 有效的正方形
 * 
 * 给定2D空间中四个点的坐标 p1, p2, p3 和 p4，如果这四个点构成一个正方形，则返回 true 。
 * 点的坐标 pi 表示为 [xi, yi] 。 输入没有任何顺序 。
 * 一个 有效的正方形 有四条等边和四个等角(90度角)。
 * 
 * 
 * 题解中的思路：
 * 1. 如果两条斜边的中点相同：则说明以该两条斜边组成的四边形为「平行四边形」。
 * 2. 在满足「条件一」的基础上，如果两条斜边的长度相同：则说明以该两条斜边组成的四边形为「矩形」。
 * 3. 在满足「条件二」的基础上，如果两条斜边的相互垂直：则说明以该两条斜边组成的四边形为「正方形」。
 * 
 * 作者：力扣官方题解
 * 链接：https://leetcode.cn/problems/valid-square/solutions/1703871/you-xiao-de-zheng-fang-xing-by-leetcode-94t5m/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 * 
 * 
 * 但是我们只使用边的长度就能够得到是否为正方向，其实这道题的难点就在于我们不知道谁是对角线上的点
 * 所以我们固定一个对角线上的点
 */
public class Leetcode593 {
    public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
        // 假设p1是对角线上的一个点
        return check(p1, p2, p3, p4) || check(p1, p3, p2, p4) || check(p1, p2, p4, p3);
    }

    /**
     * 假设p1和p3是对角线上的点
     * 
     * @param p1
     * @param p2
     * @param p3
     * @param p4
     * @return
     */
    private boolean check(int[] p1, int[] p2, int[] p3, int[] p4) {
        int len1 = getLen(p1, p2);
        int len2 = getLen(p1, p4);
        int len3 = getLen(p2, p3);
        int len4 = getLen(p3, p4);

        int crossLen1 = getLen(p1, p3);
        int crossLen2 = getLen(p2, p4);

        return len1 == len2 && len2 == len3 && len3 == len4 && len1 != 0 &&
                crossLen1 == crossLen2 && crossLen1 != len1 && crossLen1 != 0;
    }

    /**
     * 得到两点之间的距离
     * 
     * @param p1
     * @param p2
     * @return
     */
    private int getLen(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
