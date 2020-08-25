package math.numberTheory;

/**
 * 441
 *
 * ======
 *
 * Task.
 *
 * You have a total of n coins that you want to form in a staircase shape, where
 * every k-th row must have exactly k coins.
 *
 * Given n, find the total number of full staircase rows that can be formed.
 *
 * n is a non-negative integer and fits within the range of a 32-bit signed
 * integer.
 *
 * Example 1:
 *
 * n = 5
 *
 * The coins can form the following rows:
 * ¤
 * ¤ ¤
 * ¤ ¤
 *
 * Because the 3rd row is incomplete, we return 2.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ArrangingCoins {
    /**
     * We want to count the sum of arithmetic progression
     * 1+2+3+...+x = n - we want to find x
     * because it corresponds to the level
     * the sum = (a1 + an) * n / 2
     * in our case s = (1 + x) * x / 2
     * as the result we have x = -0.5 + sqrt(2n+0.25)
     */
    public static class Solution1 {
        public int arrangeCoins(int n) {
            return (int) Math.floor(-0.5 + Math.sqrt((double) 2 * n + 0.25));
        }
    }

    public static class Solution2 {
        public int arrangeCoins(int n) {
            if (n == 0) return 0;
            if (n == 1) return 1;
            int ans = -1;
            int sum = 0;
            int i = 1;
            while (sum <= n) {
                ans++;
                sum += i++;
            }
            return ans;
        }
    }
}