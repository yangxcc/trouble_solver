package skill.bit;

/**
 * leetcode 136 simple 只出现过一次的数字
 * 
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 */
public class SingleNumber {
    /**
     * 根据异或运算的性质，0 ^ n = n, n ^ n = 0
     * 
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int ans = 0;
        for (int n : nums) {
            ans = ans ^ n;
        }

        return ans;
    }

    /**
     * nums数组中有两个元素出现了奇数次，其余元素都出现了偶数次
     * 
     * @param nums
     * @return
     */
    public int[] TwoSingleNumber(int[] nums) {
        int eor = 0;
        for (int n : nums) {
            eor = eor ^ n;
        }
        /**
         * 现在这个ans一定是两个数的乘积，ans = a * b
         * 因为a != b，所以ans肯定不等于0
         * 因此，ans的二进制表示中肯定至少存在一个1，而1的这个位置上a和b分别对应着一个1，一个0
         */
        int mostRightOne = eor & (~eor + 1); // 固定写法
        // 这个mostRightOne得出来的结果只有一个位置是1，其余的地方都是0，出现1的那个位置就是eor最右边的那个1
        int singleOne = 0;
        for (int num : nums) {
            if ((num & mostRightOne) == 0) {
                // 找到了该位置是0的那些数
                singleOne ^= num;
            }
        }

        int singleTwo = eor ^ singleOne;

        return new int[] { singleOne, singleTwo };
    }
}
