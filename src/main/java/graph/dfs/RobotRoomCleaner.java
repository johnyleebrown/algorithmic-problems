package graph.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * 489
 */
public class RobotRoomCleaner {

  class Solution {

    // 0,1,2,3 - u,r,b,l - i,j
    int[][] dirs = new int[][]{{1, 0}, {0, 1}, {-1, 0}, {0, -1}};
    Robot r;

    public void cleanRoom(Robot robot) {
      r = robot;
      Set<Long> seen = new HashSet<>();
      dfs(0, 0, 0, seen);
    }

    private void dfs(int curDir, int i, int j, Set<Long> seen) {
      if (isSeen(i, j, seen)) return;
      r.clean();
      for (int k = curDir, c = 0; c < 4; k = (k + 1) % 4, c++) {
        if (r.move()) {
          int newI = dirs[k][0] + i;
          int newJ = dirs[k][1] + j;
          dfs(k, newI, newJ, seen);
          moveBack();
        }
        r.turnRight();
      }
    }

    private boolean isSeen(int i, int j, Set<Long> seen) {
      long hash = i * 201L + j * 101L;
      return !seen.add(hash);
    }

    private void moveBack() {
      r.turnLeft();
      r.turnLeft();
      r.move();
      r.turnLeft();
      r.turnLeft();
    }

    class Robot {

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