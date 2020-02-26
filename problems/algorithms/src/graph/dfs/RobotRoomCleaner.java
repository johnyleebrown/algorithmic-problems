package graph.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * 489
 */
class RobotRoomCleaner
{
	class Solution
	{
	    private class Robot
        {
            void clean()
            {
            }

            private boolean move()
            {
                return false;
            }

            private void turnRight()
            {
            }

            private void turnLeft()
            {
            }
        }
		private int[][] dirs = new int[][]{{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
		private Robot r;
		private Set<String> s = new HashSet<>();

		public void cleanRoom(Robot robot)
		{
			r = robot;
			cleanNextRoom(0, 0, 0);
		}

		private void cleanNextRoom(int direction, int x, int y)
		{
			if (!s.add(x + "," + y))
			{
				return;
			}
			r.clean();
			for (int i = 0; i < 4; i++)
			{
				// starting from our direction go full circle
				int nextDirection = (direction + i) % dirs.length;
				if (r.move())
				{
					int newx = x + dirs[nextDirection][0];
					int newy = y + dirs[nextDirection][1];
					cleanNextRoom(nextDirection, newx, newy);
					goBack();
				}
				r.turnRight();
			}
		}

		private void goBack()
		{
			r.turnRight();
			r.turnRight();
			r.move();
			r.turnLeft();
			r.turnLeft();
		}
	}


}