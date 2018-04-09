package Medium.Dhelper;

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
     * Time complexity: O(k * n^2)
     * Space complexity: O(n)
     */
    public static double solution(int[] A, int K) {
        int n = A.length;
        double[] helper = new double[n + 1];
        for (int i = 0; i < n; ++i) helper[i + 1] = helper[i] + A[i];

        double[] dp = new double[n];
        for (int i = 0; i < n; ++i) dp[i] = (helper[n] - helper[i]) / (n - i);

        for (int k = 0; k < K - 1; ++k)
            for (int i = 0; i < n; ++i)
                for (int j = i + 1; j < n; ++j)
                    dp[i] = Math.max(dp[i], (helper[j] - helper[i]) / (j - i) + dp[j]);

        return dp[0];
    }
}
