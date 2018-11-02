package Medium.Array;

/**
 * 930
 *
 * In an array A of 0s and 1s, how many non-empty subarrays have sum S?
 *
 *
 *
 * Example 1:
 *
 * Input: A = [1,0,1,0,1], S = 2
 * Output: 4
 * Explanation:
 * The 4 subarrays are bolded below:
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 * [1,0,1,0,1]
 */
public class BinarySubarraysWithSum {
    /**
     * Time complexity: O(n^2)
     * Space complexity: O(1)
     */
    class Solution1 {
        public int numSubarraysWithSum(int[] A, int S) {
            int c = 0;
            for (int i = 0; i < A.length; i++) {
                int newS = 0, j = i;
                while (newS <= S && j < A.length) {
                    newS += A[j++];
                    if (newS == S) c++;
                }
            }
            return c;
        }
    }
}
