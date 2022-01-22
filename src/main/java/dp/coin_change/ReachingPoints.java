package dp.coin_change;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 780
 */
public class ReachingPoints {

	/**
	 * Need to modify from source x,y to target x,y. Dfs with memo - SO. Check {@link }.
	 */
	public static class Solution {

		Map<Point, Boolean> dp = new HashMap<>();

		public boolean reachingPoints(int sx, int sy, int tx, int ty) {
			return dfs(Point.create(tx, ty), sx, sy);
		}

		boolean dfs(Point p, int sx, int sy) {
			if (p.x < sx || p.y < sy) {
				return false;
			}
			if (p.x == sx && p.y == sy) {
				return true;
			}
			if (dp.get(p) != null) {
				return dp.get(p);
			}
			boolean ans = dfs(p.withX(p.x - p.y), sx, sy) || dfs(p.withY(p.y - p.x), sx, sy);
			dp.put(p.from(p), ans);
			return ans;
		}

		static class Point {

			int x, y;

			Point(int x, int y) {
				this.x = x;
				this.y = y;
			}

			static Point create(int x, int y) {
				return new Point(x, y);
			}

			Point withX(int x) {
				return new Point(x, this.y);
			}

			Point withY(int y) {
				return new Point(this.x, y);
			}

			Point from(Point p) {
				return new Point(p.x, p.y);
			}

			public int hashCode() {
				return Objects.hash(x, y);
			}

			public boolean equals(Point other) {
				return other.x == this.x && other.y == this.y;
			}
		}
	}
}
