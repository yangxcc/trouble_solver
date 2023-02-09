package skill;

import java.util.Arrays;

/**
 * leetcode 204 middle 计算质数
 * 
 * 给定整数 n ，返回 所有小于非负整数 n 的质数的数量 。
 */
public class Leetcode204 {
    public int countPrimes(int n) {
        int ans = 0;
        for (int i = 1; i <= n; i++) {
            if (isPrime(i)) {
                ans++;
            }
        }
        return ans;
    }

    // 如果不考虑时间复杂度，很容就能够想到这种思路，能够看出这种做法时间复杂度为O(n^2)，而且在leetcode中也不能AC
    public boolean isPrime(int x) {
        for (int i = 2; i * i < x; i++) {
            if (x % i == 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 高效实现素数计数
     * @param n
     * @return
     */
    public int countPrimesBetter(int n) {
        // isPrime[i]表示的是第i个元素是否为素数
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);

        for (int i = 2; i * i < n; i++) {
            if (isPrime[i]) {
                for (int j = 2 * i; j < n; j += i) { 
                    /**
                     * 这里的for循环其实还可以进行优化，比如n=25, i=5时，5*2=10, 5*3=15等数字在i=2和i=3的时候
                     * 就已经被设置好了，存在一些重复计算，优化就是从2*i开始 变成从 i*i开始
                     */
                    // 例如，2是素数，那么2*2,2*3,2*4...都不是素数
                    isPrime[j] = false;
                }
            }
        }

        int ans = 0;
        for (int i = 2; i < n; i++) { 
            if (isPrime[i]) {
                ans++;
            }
        }

        return ans;
    }
}
