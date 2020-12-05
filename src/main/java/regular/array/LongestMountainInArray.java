package regular.array;

/**
 * 845
 *
 * Let's call any (contiguous) subarray B (of A) a mountain if the following
 * properties hold:
 * B.length >= 3
 * There exists some 0 < i < B.length - 1 such that
 * B[0] < B[1] < ... B[i-1] < B[i] > B[i+1] > ... > B[B.length - 1]
 * (Note that B could be any subarray of A, including the entire array A.)
 * Given an array A of integers, return the length of the longest mountain.
 * Return 0 if there is no mountain.
 */
public class LongestMountainInArray {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int longestMountain(int[] A) {
        int max = Integer.MIN_VALUE;

        for (int i = 0; i < A.length; i++) {
            int countUp = 0;
            while (i + 1 < A.length && A[i + 1] > A[i]) {
                countUp++;
                i++;
            }
            if (countUp == 0) continue;
            int countDown = 0;
            while (i + 1 < A.length && A[i + 1] < A[i]) {
                countDown++;
                i++;
            }
            if (countDown == 0) continue;
            i--; // overlapping
            max = Math.max(max, countUp + 1 + countDown);
        }

        return max == Integer.MIN_VALUE ? 0 : max;
    }
}
