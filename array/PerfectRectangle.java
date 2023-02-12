package array;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * leetcode 391 hard 完美矩形
 * 
 * 给你一个数组 rectangles ，其中 rectangles[i] = [xi, yi, ai, bi] 表示一个坐标轴平行的矩形。
 * 这个矩形的左下顶点是 (xi, yi) ，右上顶点是 (ai, bi) 。
 * 
 * 如果所有矩形一起精确覆盖了某个矩形区域，则返回 true ；否则，返回 false 。
 * 
 * 这些矩形必须严丝合缝，不能有空隙，不能有重叠
 */
public class PerfectRectangle {
    /**
     * 从面积的维度上来考虑，如果最终能够构成完美矩形，那么这些小矩形的总面积就应该等于完美矩形的面积
     * @param rectangles
     * @return
     */
    @Deprecated
    public boolean isRectangleCoverFirstVersion(int[][] rectangles) {
        int lowerLeftX = Integer.MAX_VALUE;
        int lowerLeftY = Integer.MAX_VALUE;
        int upperRightX = Integer.MIN_VALUE;
        int upperRightY = Integer.MIN_VALUE;
        int actualArea = 0, expectArea = 0;

        for (int[] corner : rectangles) {
            lowerLeftX = Math.min(lowerLeftX, corner[0]);
            lowerLeftY = Math.min(lowerLeftY, corner[1]);
            upperRightX = Math.max(upperRightX, corner[2]);
            upperRightY = Math.max(upperRightY, corner[3]);

            actualArea += (corner[2] - corner[0]) * (corner[3] - corner[1]);
        }
        expectArea = (upperRightY - lowerLeftY) * (upperRightX - lowerLeftX);

        return actualArea == expectArea;
    } 

    /**
     * 第一版本的解法是错误的，因为只考虑到了面积的影响，比如在整个的完美矩形中间扣下一块，把这一块放到外面，同样面积是相等，但这明显不是完美矩形了
     * [[0,0,1,1],[0,1,3,2],[1,0,2,2]]，只考虑面积重叠和空缺都解决不了
     * 
     * 所以还需要考虑顶点的因素，即使面积相等，如果最后顶点不是4个同样也不是完美矩形
     * 但是这里有一个难点，如何判断单独的顶点，四种情况
     *           □    □□
     * □   □□   □□    □□          都是无空隙的
     * 1中是独立的顶点， 2中中间的那两个不是独立的顶点， 3中中间的左上是独立的顶点， 4中中间不是独立的顶点
     * 
     * 所以奇数个矩阵相邻形成的顶点是独立的，偶数则不是
     * @param rectangles
     * @return
     */
    @Deprecated
    public boolean isRectangleCoverSecondVersion(int[][] rectangles) {
        int lowerLeftX = Integer.MAX_VALUE;
        int lowerLeftY = Integer.MAX_VALUE;
        int upperRightX = Integer.MIN_VALUE;
        int upperRightY = Integer.MIN_VALUE;
        int actualArea = 0, expectArea = 0;

        HashSet<String> hs = new HashSet<>();


        for (int[] corner : rectangles) {
            lowerLeftX = Math.min(lowerLeftX, corner[0]);
            lowerLeftY = Math.min(lowerLeftY, corner[1]);
            upperRightX = Math.max(upperRightX, corner[2]);
            upperRightY = Math.max(upperRightY, corner[3]);

            actualArea += (corner[2] - corner[0]) * (corner[3] - corner[1]);

            // 矩阵的四个顶点坐标 [0, 1], [2, 3], [0, 3], [2, 1]
            List<String> points = new LinkedList<>();
            points.add(corner[0] + "," + corner[1]);
            points.add(corner[0] + "," + corner[3]);
            points.add(corner[2] + "," + corner[3]);
            points.add(corner[2] + "," + corner[1]);

            /** 这样写能够保证偶数次顶点的时候肯定会被删除掉，奇数次顶点会被保留 */
            for (String point : points) {
                if (hs.contains(point)) {
                    hs.remove(point);
                } else {
                    hs.add(point);
                }
            }

        }
        expectArea = (upperRightY - lowerLeftY) * (upperRightX - lowerLeftX);

        return actualArea == expectArea && hs.size() == 4;
    }

    /**
     * 在第二版本中，我们通过顶点的个数来判断最终是否为完美矩形，很不幸，也是没能AC，原因在于我们不能够简单地判断顶点的个数
     * 当顶点个数为4且面积相等的时候一定是完美矩形吗，答案是否定的，比如 [[0,0,1,1],[0,0,2,1],[1,0,2,1],[0,2,2,3]]
     * 因此，我们还需要判断，最终剩下的四个点到底是不是完美矩形的四个顶点
     * @param rectangles
     * @return
     */
    public boolean isRectangleCoverThirdVersion(int[][] rectangles) {
        int lowerLeftX = Integer.MAX_VALUE;
        int lowerLeftY = Integer.MAX_VALUE;
        int upperRightX = Integer.MIN_VALUE;
        int upperRightY = Integer.MIN_VALUE;
        int actualArea = 0, expectArea = 0;

        HashSet<String> hs = new HashSet<>();


        for (int[] corner : rectangles) {
            lowerLeftX = Math.min(lowerLeftX, corner[0]);
            lowerLeftY = Math.min(lowerLeftY, corner[1]);
            upperRightX = Math.max(upperRightX, corner[2]);
            upperRightY = Math.max(upperRightY, corner[3]);

            actualArea += (corner[2] - corner[0]) * (corner[3] - corner[1]);

            // 矩阵的四个顶点坐标 [0, 1], [2, 3], [0, 3], [2, 1]
            List<String> points = new LinkedList<>();
            points.add(corner[0] + "," + corner[1]);
            points.add(corner[0] + "," + corner[3]);
            points.add(corner[2] + "," + corner[3]);
            points.add(corner[2] + "," + corner[1]);

            /** 这样写能够保证偶数次顶点的时候肯定会被删除掉，奇数次顶点会被保留 */
            for (String point : points) {
                if (hs.contains(point)) {
                    hs.remove(point);
                } else {
                    hs.add(point);
                }
            }

        }
        expectArea = (upperRightY - lowerLeftY) * (upperRightX - lowerLeftX);

        if (actualArea != expectArea || hs.size() != 4) {
            return false;
        }

        String[] expectPoints = new String[]{
            lowerLeftX + "," + lowerLeftY,
            lowerLeftX + "," + upperRightY,
            upperRightX + "," + upperRightY,
            upperRightX + "," + lowerLeftY
        };

        for (String ePoint : expectPoints) {
            if (!hs.contains(ePoint)) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2};
        int[] b = new int[]{3, 4};
        HashSet<int[]> hs = new HashSet<>();
        hs.add(a);
        hs.add(b);
        System.out.println(hs.size());

        System.out.println("============================");
        
        HashSet<String> h = new HashSet<>();
        h.add(String.valueOf(1) + "," + 2);
        h.add(1 + "," + 2);
        System.out.println(h.size());
    }
}
