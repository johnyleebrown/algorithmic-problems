package twoPointers.slidingWindow.count;

/**
 * 930
 *
 * ======
 *
 * Task.
 *
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 */
public class BinarySubarraysWithSum {
    static class Solution {
        public int numSubarraysWithSum(int[] A, int S) {
            return numSubarraysWithSumAtMostS(A, S) - numSubarraysWithSumAtMostS(A, S - 1);
        }

        public int numSubarraysWithSumAtMostS(int[] A, int S) {
            if (S < 0) {
                return 0;
            }

            int l = 0;
            int sum = 0;
            int result = 0;

            for (int r = 0; r < A.length; r++) {
                sum += A[r];
                while (sum > S) {
                    sum -= A[l];
                    l++;
                }
                result += r - l + 1;
            }

            return result;
        }
    }
}
