package math.geometry;

/**
 * 593
 *
 * ======
 *
 * Task.
 *
 * Given the coordinates of four points in 2D space, return whether the four
 * points could construct a square.
 *
 * The coordinate (x,y) of a point is represented by an integer array with two
 * integers.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ValidSquare {
    /**
     * We calculate all possible lengths between 2 points. We check if there are
     * 2 equal big lengths (diagonals) and 4 equal smaller lengths (sides).
     */
    public static class Solution {
        public boolean validSquare(int[] p1, int[] p2, int[] p3, int[] p4) {
            int[] lengths = new int[]{dist(p1, p2), dist(p1, p3), dist(p1, p4), dist(p2, p3), dist(p2, p4), dist(p3, p4)};
            int max = Integer.MIN_VALUE;
            for (int l : lengths) {
                max = Math.max(max, l);
            }
            int countMax = 0;
            Integer otherLength = null;
            for (int l : lengths) {
                if (l == max) countMax++;
                else if (otherLength == null) otherLength = l;
                else if (l != otherLength) return false;
            }
            return countMax == 2;
        }

        int dist(int[] p1, int[] p2) {
            int x = p1[0] - p2[0];
            int y = p1[1] - p2[1];
            return x * x + y * y;
        }
    }
}