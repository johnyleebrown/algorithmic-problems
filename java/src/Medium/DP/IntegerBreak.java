package Medium.DP;

/**
 * 343
 * Given a positive integer n, break it into the sum
 * of at least two positive integers and maximize the
 * product of those integers. Return the maximum
 * product you can get.
 */
public class IntegerBreak {
    /**
     * Time complexity: O(n^2 / 2)
     * Space complexity: O(n)
     */
    public static int solution(int n) {
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j < i; j++) {
                int a = Math.max(j, dp[j]) * Math.max(i - j, dp[i - j]);
                dp[i] = Math.max(dp[i], a);
            }
        }
        return dp[n];
    }
}
