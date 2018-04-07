package Medium.DP;

/**
 * 808
 *
 * There are two types of soup: type A and type B. Initially we have N ml of each type of soup.
 * There are four kinds of operations:
 * Serve 100 ml of soup A and 0 ml of soup B
 * Serve 75 ml of soup A and 25 ml of soup B
 * Serve 50 ml of soup A and 50 ml of soup B
 * Serve 25 ml of soup A and 75 ml of soup B
 * When we serve some soup, we give it to someone and we no longer have it.
 * Each turn, we will choose from the four operations with equal probability 0.25.
 * If the remaining volume of soup is not enough to complete the operation, we will
 * serve as much as we can.  We stop once we no longer have some quantity of both
 * types of soup. Note that we do not have the operation where all 100 ml's of soup
 * B are used first. Return the probability that soup A will be empty first, plus
 * half the probability that A and B become empty at the same time.
 *
 * Example: Input: N = 50 Output: 0.625
 * Explanation: If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time. For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.
 *
 * 0 <= N <= 10^9.
 * Answers within 10^-6 of the true value will be accepted as correct.
 */
public class SoupServings {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(n^2)
     */
    public static double solution(int N) {
        double[][] dp = new double[1000][1000];
        int n = (N + 25 - 1) / 25;
        if (n > 800) return 1.0;  // (5600, 0.9999990943903253)
        dp[0][0] = 0.5; // 50/50
        for (int i = 1; i < 1000; i++) dp[0][i] = 1.0;

        for (int i = 1; i < 1000; i++) {
            for (int j = 1; j < 1000; j++) {
                dp[i][j] = 0.0;
                dp[i][j] += dp[Math.max(0, i - 4)][Math.max(0, j - 0)] / 4; // Serve 100 ml of soup A and 0 ml of soup B
                dp[i][j] += dp[Math.max(0, i - 3)][Math.max(0, j - 1)] / 4; // Serve 75 ml of soup A and 25 ml of soup B
                dp[i][j] += dp[Math.max(0, i - 2)][Math.max(0, j - 2)] / 4; // Serve 50 ml of soup A and 50 ml of soup B
                dp[i][j] += dp[Math.max(0, i - 1)][Math.max(0, j - 3)] / 4; // Serve 25 ml of soup A and 75 ml of soup B
            }
        }

        return dp[n][n];
    }
}
