package regular.dfs;

import java.util.HashSet;
import java.util.Set;

/**
 * @company FB
 * @url https://leetcode.com/discuss/interview-question/633689/facebook-phonevideo-find-cheese
 */
public class FindCheese {

  enum Direction {
    UP, RIGHT, DOWN, LEFT
  }

  /**
   * SF
   */

  interface Mouse {

    /**
     * Moves to one of the directions (left, right, up, down) and returns false
     * if you can't move and true if you can.
     */
    public abstract boolean move(Direction direction);

    /**
     * Returns true if there is a cheese in the current cell.
     */
    public abstract boolean hasCheese();

    /**
     * Should return true and leave the mouse at that location or false if we
     * can't find cheese and return the mouse back to where it started.
     */
    public abstract boolean getCheese();

    String getPosition();
  }

  abstract static class AbstractMouse implements Mouse {

    @Override
    public boolean getCheese() {
      return dfs(null, new Position(0, 0), new HashSet<>());
    }

    private boolean dfs(Direction currentDirection, Position pos,
        Set<Long> seen) {
      if (!seen.add(getHash(pos))) {
        return false;
      }
      if (hasCheese()) {
        return true;
      }
      // choose destination
      for (Direction newDirection : Direction.values()) {
        // can move
        if (move(newDirection)) {
          Position newPosition = getPosition(newDirection, pos);
          // move
          boolean ans = dfs(newDirection, newPosition, seen);
          if (ans) {
            return true;
          }
          // go back
          moveBack(newDirection);
        }
      }
      return false;
    }

    private void moveBack(Direction direction) {
      Direction oppositeDir = getOppositeDir(direction);
      move(oppositeDir);
    }

    private Direction getOppositeDir(Direction direction) {
      switch (direction) {
        case RIGHT:
          return Direction.LEFT;
        case DOWN:
          return Direction.UP;
        case LEFT:
          return Direction.RIGHT;
        case UP:
        default:
          return Direction.DOWN;
      }
    }

    private long getHash(Position pos) {
      return pos.i * 100L + pos.j * 100L;
    }

    public Position getPosition(Direction direction, Position position) {
      switch (direction) {
        case RIGHT:
          return new Position(position.i, position.j + 1);
        case DOWN:
          return new Position(position.i - 1, position.j);
        case LEFT:
          return new Position(position.i, position.j - 1);
        case UP:
        default:
          return new Position(position.i + 1, position.j);
      }
    }

    class Position {

      int i, j;

      public Position(int i, int j) {
        this.i = i;
        this.j = j;
      }

      public Position setI(int i) {
        this.i = i;
        return this;
      }

      public Position setJ(int j) {
        this.j = j;
        return this;
      }
    }
  }

  public static class MouseImpl extends AbstractMouse {

    int[][] ma;
    int ci, cj, n, m;
    Position p;

    public MouseImpl(int n, int m, int ci, int cj) {
      ma = new int[n][m];
      this.n = n;
      this.m = m;
      this.ci = ci;
      this.cj = cj;
      p = new Position(0, 0);
    }

    @Override
    public boolean move(Direction direction) {
      Position newPos = getPosition(direction, p);
      if (!inBounds(newPos.i, newPos.j)) {
        return false;
      }
      p.i = newPos.i;
      p.j = newPos.j;
      return true;
    }

    private boolean inBounds(int i, int j) {
      return i >= 0 && j >= 0 && i < n && j < m;
    }

    @Override
    public boolean hasCheese() {
      return p.i == ci && p.j == cj;
    }

    @Override
    public String getPosition() {
      return String.format("[%s, %s]", p.j, p.j);
    }
  }

}