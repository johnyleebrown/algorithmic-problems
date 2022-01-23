package array.other;

/**
 * 795
 * We are given an array A of positive integers,
 * and two positive integers L and R (L <= R).
 * Return the number of (contiguous, non-empty) subarrays
 * such that the value of the maximum array element in
 * that subarray is at least L and at most R.
 */
public class NumberOfSubarraysWithBoundedMaximum {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int solution(int[] A, int L, int R) {
        return count(A, R) - count(A, L - 1);
    }

    public static int count(int[] A, int bound) {
        int ans = 0, cur = 0;
        for (int x : A) {
            cur = x <= bound ? cur + 1 : 0;
            ans += cur;
        }
        return ans;
    }
}
