package medium.dp;

/**
 * 813
 * <p>
 * We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?
 * note that our partition must use every number in A, and that scores are not necessarily integers.
 * <p>
 * Example:
 * Input: A = [9,1,2,3,9] K = 3. Output: 20
 * Explanation: The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
 * We could have also partitioned A into [9, 1], [2], [3, 9], for example. That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 * <p>
 * 1 <= A.length <= 100.
 * 1 <= A[i] <= 10000.
 * 1 <= K <= A.length.
 * Answers within 10^-6 of the correct answer will be accepted as correct.
 */
public class LargestSumOfAverages {
    /**
     * Recurrence: dp(i, k) = max(average(i, N), max_{j > i}(average(i, j) + dp(j, k-1))).
     * <p>
     * BU
     * Time complexity: O(n^3), max(k) = n
     * Space complexity: O(n)
     */
    public static double solution(int[] A, int K) {
        int n = A.length;
        double[] helper = new double[n + 1];
        for (int i = 0; i < n; i++) // running sum
            helper[i + 1] = helper[i] + A[i];

        double[] dp = new double[n];
        for (int i = 0; i < n; i++) // running average
            dp[i] = (helper[n] - helper[i]) / (n - i);

        for (int k = 0; k < K - 1; k++)
            for (int i = 0; i < n; i++)
                for (int j = i + 1; j < n; j++)
                    dp[i] = Math.max(dp[i], (helper[j] - helper[i]) / (j - i) + dp[j]);


        return dp[0];
    }
    /*
    helper: [0.0, 9.0, 10.0, 12.0, 15.0, 24.0]
    dp:     [4.8, 3.75, 4.666666666666667, 6.0, 9.0]
     */

    /**
     * Recurrence: dp(i, k) = max(average(i, N), max_{j > i}(average(i, j) + dp(j, k-1))).
     * <p>
     * TD
     * Time complexity: O(n^3), max(k) = n
     * Space complexity: O(n^2)
     */
    public double largestSumOfAverages(int[] A, int K) {
        int N = A.length;
        double[][] memo = new double[N + 1][N + 1];
        double cur = 0;

        for (int i = 0; i < N; ++i) {
            cur += A[i];
            memo[i + 1][1] = cur / (i + 1);
        }

        return search(N, K, A, memo);
    }

    public double search(int N, int K, int[] A, double[][] memo) {
        if (memo[N][K] > 0) return memo[N][K];
        double cur = 0;

        for (int i = N - 1; i > 0; i--) {
            cur += A[i];
            memo[N][K] = Math.max(memo[N][K], search(i, K - 1, A, memo) + cur / (N - i));
        }

        return memo[N][K];
    }
}
