package a0.two.regular;

/**
 * 11
 *
 * ======
 *
 * Task.
 *
 * Given n non-negative integers a1, a2, ..., an , where each represents a point
 * at coordinate (i, ai). n vertical lines are drawn such that the two endpoints
 * of line i is at (i, ai) and (i, 0). Find two lines, which together with
 * x-axis forms a container, such that the container contains the most water.
 */
public class ContainerWithMostWater {
	public int maxArea(int[] a) {
		int ans = 0;
		int l = 0;
		int r = a.length - 1;

		while (l < r) {
			int area = Math.min(a[l], a[r]) * (r - l);
			ans = Math.max(ans, area);

			if (a[l] < a[r]) {
				l++;
			}
			else {
				r--;
			}
		}

		return ans;
	}
}
