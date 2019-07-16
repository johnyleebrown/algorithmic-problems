package Array;

/**
 * Given n non-negative integers a1, a2, ..., an,
 * where each represents a point at coordinate (i, ai).
 * n vertical lines are drawn such that the
 * two endpoints of line i is at (i, ai) and (i, 0).
 * Find two lines, which together with x-axis forms a container,
 * such that the container contains the most water.
 *
 * Note: You may not slant the container and n is at least 2.
 */

public class ContainerWithMostWater {

    // Time : O(n^2), Space : O(1)
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length - 1; i++) {
            for (int j = i + 1; j < height.length; j++) {
                max = Math.max(max, Math.min(height[i],height[j])*(j-i));
            }
        }
        return max;
    }

    /**
     * Two pointers
     *
     * Assuming that a relatively longer line obtained by moving the shorter line's pointer
     * might overcome the reduction in area caused by decrease of the width.
     */
    public int maxArea2(int[] height) {
        int max = 0, i = 0, j = height.length - 1;
        while (i < j) {
            max = Math.max(max, Math.min(height[i], height[j]) * (j - i));
            if (height[i] < height[j]) i++;
            else j--;
        }
        return max;
    }
}
