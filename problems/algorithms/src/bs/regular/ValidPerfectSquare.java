package bs.regular;

/**
 * 367
 *
 * ======
 *
 * Task.
 *
 * Given a positive integer num, write a function which returns True if num is a
 * perfect square else False.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ValidPerfectSquare {
    public static class Solution {
        public boolean isPerfectSquare(int num) {
            if (num < 1) return false;
            if (num == 1) return true;
            long lo = 1;
            long hi = num / 2;
            while (hi - lo >= 0) {
                long mid = lo + (hi - lo) / 2;
                long x = mid * mid;
                if (x == num) return true;
                if (x < num) {
                    lo = mid + 1;
                } else {
                    hi = mid - 1;
                }
            }
            return false;
        }
    }
}