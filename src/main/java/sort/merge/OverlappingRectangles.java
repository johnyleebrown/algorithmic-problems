package sort.merge;

import java.util.List;

/**
 * @company Miro
 * @desc Determine if there is any rectangle overlap.
 */
public class OverlappingRectangles {

	public static boolean figuresOverlap(List<Integer> f1, List<Integer> f2) {
		int f1_x1 = f1.get(0);
		int f1_y1 = f1.get(1);
		int f1_x2 = f1.get(2);
		int f1_y2 = f1.get(3);
		int f2_x1 = f2.get(0);
		int f2_y1 = f2.get(1);
		int f2_x2 = f2.get(2);
		int f2_y2 = f2.get(3);

		boolean f2_BL_lower_f1_UR = f2_x1 < f1_x2 && f2_y1 < f1_y2;
		boolean f2_UR_higher_f1_BL = f2_x2 > f1_x1 && f2_y2 > f1_y1;

		return f2_BL_lower_f1_UR && f2_UR_higher_f1_BL;
	}

	/**
	 * Similar to merge intervals.
	 */
	public static class Solution1 {

		/**
		 * @param coords list of rectangles coordinates like [(x1,y1,x2,y2),..] - where x1,y1
		 *               are the coords of bottom left corner and x2,y2 are the coords of the
		 *               upper right corner.
		 * @return true if there is any overlapping of figures
		 */
		public static boolean overlap(List<List<Integer>> coords) {

			for (int i = 0; i < coords.size() - 1; i++) {
				if (figuresOverlap(coords.get(i), coords.get(i + 1))) {
					return true;
				}
			}
			return false;
		}
	}

	public static class Solution2 {

		/**
		 * Brute force
		 */
		public static boolean overlap(List<List<Integer>> coords) {
			for (int i = 0; i < coords.size(); i++) {
				for (int j = 0; j < coords.size(); j++) {
					if (i != j) {
						if (figuresOverlap(coords.get(i), coords.get(j))) {
							System.out.println("f1: " + coords.get(i));
							System.out.println("f2: " + coords.get(j));
							return true;
						}
					}
				}
			}
			return false;
		}
	}
}
