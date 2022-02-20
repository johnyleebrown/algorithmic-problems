package math.numberTheory.gcd;

/**
 * 780
 */
public class ReachingPoints {

	/**
	 * Similar to finding gcd. This method is an optimization of dp dfs.
	 *
	 * O(logN) where N = max(tx,ty).
	 */
	public static class Solution {

		public boolean reachingPoints(int sx, int sy, int tx, int ty) {
			if (tx < sx || ty < sy) return false;
			// 2,3 -> 2,11
			if (tx == sx && (ty - sy) % tx == 0) return true;
			// 3,2 -> 11,2
			if (ty == sy && (tx - sx) % ty == 0) return true;
			return tx > ty
					? reachingPoints(sx, sy, tx % ty, ty)
					: reachingPoints(sx, sy, tx, ty % tx);
		}
	}
}
