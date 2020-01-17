package math;

/**
 * 812
 *
 * You have a list of points in the plane. Return the area of the largest triangle that can be formed by any 3 of the points.
 */
public class LargestTriangleArea {
    /**
     * f(p1, p2, p3) takes three points and return its area.
     * loop on all combinations of three points.
     *
     * Area = 1/2 * abs((xA - xC) * (yB - yA) - (xA - xB) * (yC - yA)) =
     *      = 1/2 * abs(xA*yB + xB*yC + xC*yA - xA*yC - xC*yB - xB*yA)
     *
     * Time complexity: O(n^3)
     * Space complexity: O(1)
     */
    public double largestTriangleArea(int[][] p) {
        double res = 0;

        for (int[] i : p) {
            for (int[] j : p) {
                for (int[] k : p) {
                    res = Math.max(res, 0.5 * Math.abs(
                                    i[0] * j[1]
                                    + j[0] * k[1]
                                    + k[0] * i[1]
                                    - j[0] * i[1]
                                    - k[0] * j[1]
                                    - i[0] * k[1]));
                }
            }
        }

        return res;
    }
}
