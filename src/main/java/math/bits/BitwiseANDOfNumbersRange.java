package math.bits;

/**
 * 201
 *
 * ======
 *
 * Task.
 *
 * Given a range [m, n] where 0 <= m <= n <= 2147483647, return the bitwise AND
 * of all numbers in this range, inclusive.
 *
 * ======
 *
 * Source: Leetcode
 */
public class BitwiseANDOfNumbersRange {
    /**
     * Shift until we have a common part.
     */
    public static class Solution {
        public int rangeBitwiseAnd(int m, int n) {
            int i = 0;
            for (; m != n; i++) {
                m >>= 1;
                n >>= 1;
            }
            return n << i;
        }
    }
}