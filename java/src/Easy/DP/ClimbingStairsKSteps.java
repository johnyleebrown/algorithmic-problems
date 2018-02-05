package Easy.DP;

/**
 * Count the number of ways to climb the n-th step
 * if it is possible to climb from 1 to k steps at a time
 */
public class ClimbingStairsKSteps {
    /**
     * Time complexity: O(n*k)
     * Space complexity: O(n)
     */
    public static int solution(int n, int k) {
        int[] a = new int[n + 1];
        a[0] = 1;
        for (int i = 1 ; i <= n ; i++) {
            int sum = 0;
            // so we don't have to count negative climbs
            // as they are equal to zero
            int max = Math.min(i, k);
            for (int j = 1 ; j <= max ; j++)
                sum += a[i - j];
            a[i] = sum;
        }
        return a[n];
    }
}
