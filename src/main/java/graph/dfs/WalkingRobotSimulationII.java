package graph.dfs;

/**
 * 2069. Walking Robot Simulation II
 *
 * https://leetcode.com/problems/walking-robot-simulation-ii/
 */
public class WalkingRobotSimulationII {

  /**
   * Can optimize to have an array of precalculated sides. side = {w - 1, w + h
   * - 2, 2 * w + h - 3, 2 * w + 2 * h - 4};
   */
  public static class Robot {

    String[] dirNames = new String[]{"North", "East", "South", "West"};
    int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};//x,y
    int dir = 1, x, y, width, height, perimeter, num;

    public Robot(int width, int height) {
      this.height = height;
      this.width = width;
      int area = width * height;
      perimeter =
          area - (width > 2 ? width - 2 : 0) * (height > 2 ? height - 2 : 0);
    }

    public void move(int num) {
      this.num += num;
    }

    private void move() {
      // didn't move
      if (num == 0) {
        return;
      }
      // exclude cycles
      num %= perimeter;
      // came to the same spot
      if (num == 0) {
        // edge case
        if (x == 0 && y == 0) {
          dir = 2;
        }
      } else {
        while (num > 0) {
          // go to wall, turn
          int stepsToWall = getStepsCount();
          int steps = Math.min(num, stepsToWall);
          moveHelper(steps);
          num -= steps;
          if (num < 0) {
            num = 0;
          }
          if (num > 0) {
            calculateNextDirCC();
          }
        }
      }
    }

    int getStepsCount() {
      if (dir == 0) {
        return (height - 1) - y;
      } else if (dir == 1) {
        return (width - 1) - x;
      } else if (dir == 2) {
        return y;
      } else {
        return x;
      }
    }

    void moveHelper(int dist) {
      x += dirs[dir][0] * dist;
      y += dirs[dir][1] * dist;
    }

    public int[] getPos() {
      move();
      return new int[]{x, y};
    }

    public String getDir() {
      move();
      return dirNames[dir];
    }

    void calculateNextDirCC() {
      dir = (dir - 1) % 4;
      if (dir < 0) {
        dir += 4;
      }
    }
  }
}