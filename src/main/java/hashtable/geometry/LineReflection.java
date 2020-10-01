package hashtable.geometry;

import java.util.HashSet;
import java.util.Set;

/**
 * 356
 *
 * ======
 *
 * Task.
 *
 * Given n points on a 2D plane, find if there is such a line parallel to y-axis that reflect the
 * given points symmetrically, in other words, answer whether or not if there exists a line that
 * after reflecting all points over the given line the set of the original points is the same that
 * the reflected ones.
 *
 * Note that there can be repeated points.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LineReflection {
	/**
	 * Realized that sum of reflected coordinates will be the same for all given coordinates.
	 * So what we can do is use set to check if we have a reflected coordinate for each one.
	 * The sum is easy to find - another thing we realize is that points that are the most apart
	 * will be the points whose sum we can take.
	 */
	public static class Solution {
		private static final long mx = 2 * (long) 1e8;

		public boolean isReflected(int[][] ar) {
			int min = Integer.MAX_VALUE;
			int max = Integer.MIN_VALUE;

			Set<String> set = new HashSet<>();
			for (int[] a : ar) {
				set.add(a[0] + "," + a[1]);
				min = Math.min(min, a[0]);
				max = Math.max(max, a[0]);
			}

			int sum = min + max;
			for (int[] a : ar) {
				if (!set.contains(sum - a[0] + "," + a[1])) {
					return false;
				}
			}

			return true;
		}
	}
}