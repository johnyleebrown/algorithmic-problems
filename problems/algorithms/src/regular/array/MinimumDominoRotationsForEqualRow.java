package regular.array;

/**
 * 1007
 */
public class MinimumDominoRotationsForEqualRow {
    /**
     * Another solution: check if tops is either A[0] or B[0], and same for
     * bottoms, if not both return -1.
     */
    public static class Solution {
        public int minDominoRotations(int[] tops, int[] bottoms) {
            int n = tops.length;
            int ans = n + 1;
            for (int digit = 1; digit <= 6; digit++) {
                boolean cantCollectCurrentDigit = false;
                int topRotations = 0;
                int bottomRotations = 0;
                for (int j = 0; j < n; j++) {
                    if (tops[j] != digit && bottoms[j] != digit) {
                        cantCollectCurrentDigit = true;
                        break;
                    }

                    if (tops[j] == digit && bottoms[j] != digit) {
                        bottomRotations++;
                    } else if (bottoms[j] == digit && tops[j] != digit) {
                        topRotations++;
                    }
                }
                if (!cantCollectCurrentDigit) {
                    ans = Math.min(ans, Math.min(topRotations, bottomRotations));
                }
            }
            return ans == n + 1 ? -1 : ans;
        }
    }
}
