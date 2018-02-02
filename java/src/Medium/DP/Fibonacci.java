package Medium.DP;

/**
 * Return the n-th fib number
 */
public class Fibonacci {
    /**
     * Time complexity: O(2^n)
     * Recurrence: T(n) = T(n - 1) + T(n - 2) + O(1)
     */
    public static int solution(int n) {
        if (n <= 2) return 1;
        return solution(n - 2) + solution(n - 1);
    }

    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solutionDP(int n) {
        return fib(new int[n + 1], n);
    }

    private static int fib(int[] dp, int n) {
        if (dp[n] != 0) return dp[n];
        if (n <= 2) return 1;
        int x = fib(dp, n - 2) + fib(dp, n - 1);
        dp[n] = x;
        return x;
    }

    /**
     * Bottom up
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solutionBU(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1 ; i <= n ; i++) {
            if (i <= 2) dp[i] = 1;
            else dp[i] = dp[i - 2] + dp[i - 1];
        }
        return dp[n];
    }

}
