package Easy.DP;

/**
 * 70
 * You are climbing a stair case. It takes n steps to reach to the top.
 * Each time you can either climb 1 or 2 steps.
 * In how many distinct ways can you climb to the top?
 */
public class ClimbingStairs {

    // O(n) ; O(n)
    public class Solution {
        public int climbStairs(int n) {
            if (n == 1) return 1;
            int[] a = new int[n + 1];
            a[1] = 1;
            a[2] = 2;
            for (int i = 3; i <= n; i++) a[i] = a[i - 1] + a[i - 2];
            return a[n];
        }
    }

}
