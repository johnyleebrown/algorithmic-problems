package Medium.DP;

/**
 * 790
 */
public class DominoAndTrominoTiling {
    /**
     * XX tile and  XX tile
     *              X
     *
     * Time complexity: O()
     * Space complexity: O()
     */
    public static int solution(int N) {
        int[] dp = new int[N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            int sum = 0;
            for (int j = 3; j <= i; j++)
                sum += dp[i - j];
            dp[i] += 2 * sum;
        }
        return dp[N];
    }
}
