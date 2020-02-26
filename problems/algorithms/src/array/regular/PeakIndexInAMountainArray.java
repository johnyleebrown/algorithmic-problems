package array.regular;

/**
 * 852
 *
 * Let's call an array A a mountain if the following properties hold:
 * A.length >= 3
 * There exists some 0 < i < A.length - 1 such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1]
 * Given an array that is definitely a mountain, return any i such that A[0] < A[1] < ... A[i-1] < A[i] > A[i+1] > ... > A[A.length - 1].
 */
public class PeakIndexInAMountainArray {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public int peakIndexInMountainArray(int[] A) {
        int i = 0;
        for (int j = 1; j < A.length; ++j) {
            if (A[j] > A[i]) i = j;
        }
        return i;
    }
}
