package array.area;

/**
 * 223
 */
public class RectangleArea {

	/**
	 * Area + area - overlap. For overlap use min, max;
	 */
	private static class Solution {

		public int computeArea(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2,
				int by2) {

			int aArea = (ax2 - ax1) * (ay2 - ay1);
			int bArea = (bx2 - bx1) * (by2 - by1);
			int result = aArea + bArea;

			// overlap
			if (overlap(ax1, ay1, ax2, ay2, bx1, by1, bx2, by2)) {
				int up = Math.min(ay2, by2);
				int down = Math.max(ay1, by1);
				int h = up - down;
				int left = Math.max(ax1, bx1);
				int right = Math.min(ax2, bx2);
				int w = right - left;

				int common = h * w;
				result -= common;
			}

			return result;
		}

		private boolean overlap(int ax1, int ay1, int ax2, int ay2, int bx1, int by1, int bx2,
				int by2) {
			return bx1 < ax2 && by1 < ay2 && bx2 > ax1 && by2 > ay1;
		}
	}
}