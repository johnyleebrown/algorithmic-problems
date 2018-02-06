package Easy.DP;

import java.util.Arrays;

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

    // find a path of steps used for min climbing, where 1 <= step <= k
    public static int[] solutionExtra1(int k, int[] values, int n) {
        int[] a = new int[n + 1];
        a[0] = 1;
        int[] from = new int[n];
        for (int i = 1 ; i <= n ; i++) {
            int max = Math.min(i, k);
            // init
            a[i] = a[i - 1] + values[i - 1];
            from[i - 1] = i - 1;
            for (int j = 1 ; j <= max ; j++) {
                if (a[i] > a[i - j] + values[i - 1]) {
                    a[i] = a[i - j] + values[i - 1];
                    from[i - 1] = i - j;
                }
            }
        }
        return from;
    }
}
