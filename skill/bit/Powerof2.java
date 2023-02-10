package skill.bit;

/**
 * leetcode 231 simple 2的幂
 * leetcode 326 simple 3的幂
 * leetcode 342 simple 4的幂
 */
public class Powerof2 {
    public boolean isPowerOfTwo(int n) {
        if (n <= 0) {
            return false;
        }

        return (n & (n - 1)) == 0;
    }
}

/**
 * 除此之外，还有3的幂，4的幂
 * 
 */

class Power {

    public boolean isPowerOfThree(int n) {
        if (n <= 0) {
            return false;
        }

        /**
         * 最开始这里是这么写的，但是对于45这样的case就不行
         * while (n / 3 != 0) {
         * n /= 3;
         * }
         */
        // 一直整除，直到不能整除了
        while (n % 3 == 0) {
            n /= 3;
        }

        return n == 1;
    }

    public boolean isPowerOfFour(int n) {
        if (n <= 0) {
            return false;
        }

        if (n == 1) {
            return true;
        }

        // 比如16.返回的字符串就是10000
        String str = Integer.toBinaryString(n);
        // 目的就是看看n的二进制表示中是不是只有一个1，而且这个1还在奇数位置上
        if (n >= 4 && (n & (n - 1)) == 0 && (str.length() % 2) != 0) {
            return true;
        }

        return false;
    }

    /**
     * 其实不用这么麻烦，通过3的幂那道题可以推广，任何数的幂
     * n是否为x的幂，x > 0
     */
    public boolean isPowerOfX(int n, int x) {
        if (n <= 0) {
            return false;
        }

        while (n % x == 0) {
            n /= x;
        }

        return n == 1;
    }
}