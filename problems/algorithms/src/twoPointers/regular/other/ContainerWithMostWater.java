package twoPointers.regular.other;

/**
 * 11
 */
public class ContainerWithMostWater {
    /**
     * We starts with the widest container, l = 0 and r = n - 1. Let's say the
     * left one is shorter: h[l] < h[r]. Then, this is already the largest
     * container the left one can possibly form. There's no need to consider it
     * again. Therefore, we just throw it away and start again with l = 1 and r
     * = n -1.
     */
    public static class Solution {
        public int maxArea(int[] a) {
            int ans = 0;
            int l = 0;
            int r = a.length - 1;

            while (l < r) {
                int area = Math.min(a[l], a[r]) * (r - l);
                ans = Math.max(ans, area);

                if (a[l] < a[r]) {
                    l++;
                } else {
                    r--;
                }
            }

            return ans;
        }
    }
}
