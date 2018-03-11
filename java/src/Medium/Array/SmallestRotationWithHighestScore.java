package Medium.Array;

/**
 * 798
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
     * Interval Stabbing
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(int[] A) {
        int n = A.length;
        int[] bad = new int[n];

        for (int i = 0; i < n; ++i) {
            int left = (i - A[i] + 1 + n) % n;
            int right = (i + 1) % n;
            bad[left]--;
            bad[right]++;
            if (left > right) bad[0]--;
        }

        int best = -n;
        int ans = 0;
        int cur = 0;

        for (int i = 0; i < n; ++i) {
            cur += bad[i];
            if (cur > best) {
                best = cur;
                ans = i;
            }
        }

        return ans;
    }

    // O(n^2)
    public static int solution2(int[] A) {
        int n = A.length;
        int res = 0;
        int maxScore = Integer.MIN_VALUE;

        for (int k = 0; k < n; k++) {
            if (k != 0) {
                int first = A[0];
                for (int i = 1; i < n; i++) A[i - 1] = A[i];
                A[n - 1] = first;
            }

            int score = 0;
            for (int i = 0; i < n; i++) if (A[i] <= i) score++;

            if (score > maxScore) {
                res = k;
                maxScore = score;
            }
        }
        return res;
    }


    public static void main(String[] args) {
//        System.out.println(solution3(new int[]{2, 3, 1, 4, 0}));
//        System.out.println(solution3(new int[]{1, 3, 0, 2, 4}));
    }
}
