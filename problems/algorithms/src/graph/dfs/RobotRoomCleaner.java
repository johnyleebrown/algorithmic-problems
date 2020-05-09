package graph.dfs;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 489
 */
public class RobotRoomCleaner {
	class Solution {
		int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
		Set<Point> s;

		public void cleanRoom(Robot r) {
			s = new HashSet<>();
			dfs(r, 0, 0, 0);
		}

		private void dfs(Robot r, int dir, int i, int j) {
			if (!s.add(new Point(i, j))) return;
			r.clean();
			for (int k = 0; k < 4; k++) {
				int actual = (dir + k) % 4;
				if (r.move()) {
					int ni = i + dirs[actual][0];
					int nj = j + dirs[actual][1];
					dfs(r, actual, ni, nj);
					goBack(r);
				}
				r.turnRight();
			}
		}

		void goBack(Robot r) {
			r.turnRight();
			r.turnRight();
			r.move();
			r.turnRight();
			r.turnRight();
		}

		private class Robot {
			void clean() {
			}

			private boolean move() {
				return false;
			}

			private void turnRight() {
			}

			private void turnLeft() {
			}
		}
	}
}