package Medium.Array;

/**
 * Given an array A, we may rotate it by a non-negative integer K
 * so that the array becomes A[K], A[K+1], A{K+2], ... A[A.length - 1],
 * A[0], A[1], ..., A[K-1].  Afterward, any entries that are less than
 * or equal to their index are worth 1 point.
 * For example, if we have [2, 4, 1, 3, 0], and we rotate by K = 2, it
 * becomes [1, 3, 0, 2, 4].  This is worth 3 points because 1 > 0 [no points],
 * 3 > 1 [no points], 0 <= 2 [one point], 2 <= 3 [one point], 4 <= 4 [one point].
 * Over all possible rotations, return the rotation index K that corresponds
 * to the highest score we could receive.  If there are multiple answers,
 * return the smallest such index K.
 */
public class SmallestRotationWithHighestScore {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(int[] A) {
        int[] res = new int[A.length];

        for (int f = 0; f < A.length; f++) {
            int pp = A[f];
            int start = pp;
            int end = A.length;

            if (pp >= end) continue;
            start -= f;
            end -= f;
            if (start >= 0) {
                res[start]++;
                if (end < A.length) res[end]--;
            } else {
                res[0]++;
                res[start + A.length]++;
                if (end < A.length) res[end]--;
            }
        }

        for (int k = 1; k < A.length; k++) res[k] += res[k - 1];
        int min = 0;
        for (int t = A.length - 1; t >= 1; t--) if (res[t] > res[min]) min = t;

        return (A.length - min) % A.length;
    }
}
