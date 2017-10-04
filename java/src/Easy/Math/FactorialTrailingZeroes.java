package Easy.Math;

/**
 * 172
 * Given an integer n, return the number of trailing zeroes in n!.
 */
public class FactorialTrailingZeroes {

    // A trailing zero is always produced by prime factors 2 and 5.
    // How many multiples of 5 are there..
    // Count of 5s in prime factors of n! = floor(n/5) + floor(n/25) + floor(n/125) + ....
    // O(logn)
    public class Solution {
        public int trailingZeroes(int n) {
            int r = 0;
            while (n > 0) {
                n /= 5;
                r += n;
            }
            return r;
        }
    }
}
