package array.area;

/**
 * 223
 *
 * ======
 *
 * Task.
 *
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 *
 * Each rectangle is defined by its bottom left corner and top right corner as
 * shown in the figure.
 *
 * ======
 *
 * Source: Leetcode
 */
public class RectangleArea
{
	/**
	 * Area + area - overlap. For overlap use min, max;
	 */
	private static class Solution
	{
		public int computeArea(int A, int B, int C, int D, int E, int F, int G, int H)
		{
			int area = (C - A) * (D - B) + (G - E) * (H - F);
			int rightMin = Math.min(C, G);
			int leftMax = Math.max(A, E);
			int topMin = Math.min(D, H);
			int botMax = Math.max(B, F);
			if (rightMin > leftMax && topMin > botMax)
				area -= (rightMin - leftMax) * (topMin - botMax);
			return area;
		}
	}
}