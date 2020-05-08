package math.geometry;

/**
 * 1232
 *
 * ======
 *
 * Task.
 *
 * You are given an array coordinates, coordinates[i] = [x, y], where [x, y]
 * represents the coordinate of a point. Check if these points make a straight
 * line in the XY plane.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CheckIfItIsAStraightLine {
    /**
     * y=kx+b
     * (x1,y1) => y1=x1*k+b => b=y1-x1*k
     * (x2,y2) => y2=x2*k+b
     * y1-x1*k=y2-x2*k => x2*k-x1*k=y2-y1 => k=(y2-y1)/(x2-x1)
     */
    public static class Solution {
        public boolean checkStraightLine(int[][] ar) {
            if (ar.length == 2) return true;
            double k = ar[1][0] - ar[0][0] == 0 ? 1 : (ar[1][1] - ar[0][1]) / (ar[1][0] - ar[0][0]);
            double b = ar[0][1] - ar[0][0] * k;
            for (int i = 2; i < ar.length; i++) {
                if (((double) ar[i][1]) != k * ar[i][0] + b) {
                    return false;
                }
            }
            return true;
        }
    }
}