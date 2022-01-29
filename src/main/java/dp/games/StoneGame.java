package dp.games;

/**
 * 877. Stone Game
 *
 * Alex and Lee play a game with piles of stones.  There are an even number of piles arranged in a
 * row, and each pile has a positive integer number of stones piles[i].
 *
 * The objective of the game is to end with the most stones.  The total number of stones is odd, so
 * there are no ties.
 *
 * Alex and Lee take turns, with Alex starting first.  Each turn, a player takes the entire pile of
 * stones from either the beginning or the end of the row.  This continues until there are no more
 * piles left, at which point the person with the most stones wins.
 *
 * Assuming Alex and Lee play optimally, return True if and only if Alex wins the game.
 *
 *
 *
 * Example 1:
 *
 * Input: piles = [5,3,4,5] Output: true Explanation: Alex starts first, and can only take the first
 * 5 or the last 5. Say he takes the first 5, so that the row becomes [3, 4, 5]. If Lee takes 3,
 * then the board is [4, 5], and Alex takes 5 to win with 10 points. If Lee takes the last 5, then
 * the board is [3, 4], and Alex takes 4 to win with 9 points. This demonstrated that taking the
 * first 5 was a winning move for Alex, so we return true.
 *
 *
 * Constraints:
 *
 * 2 <= piles.length <= 500 piles.length is even. 1 <= piles[i] <= 500 sum(piles) is odd.
 *
 * https://leetcode.com/problems/stone-game/
 */
public class StoneGame {

  public static class Solution1 {

    Result[][] m;

    public boolean stoneGame(int[] piles) {
      m = new Result[500][500];
      Result ans = f(0, piles.length - 1, piles, true);
      return ans.first > ans.second;
    }

    Result f(int l, int r, int[] piles, boolean first) {
      if (l >= r) {
        return new Result();
      }
      if (m[l][r] != null) {
        return m[l][r];
      }
      Result takeLeft = f(l + 1, r, piles, !first);
      Result takeRight = f(l, r - 1, piles, !first);
      Result res;
      if (first) {
        if (takeLeft.first + piles[l] > takeRight.first + piles[r]) {
          res = new Result(takeLeft.first + piles[l], takeLeft.second);
        } else {
          res = new Result(takeRight.first + piles[r], takeRight.second);
        }
      } else {
        if (takeLeft.second + piles[l] > takeRight.second + piles[r]) {
          res = new Result(takeLeft.first, takeLeft.second + piles[l]);
        } else {
          res = new Result(takeRight.first, takeRight.second + piles[r]);
        }
      }
      m[l][r] = res;
      return res;
    }

    static class Result {

      int first, second;

      Result() {
      }

      Result(int first, int second) {
        this.first = first;
        this.second = second;
      }
    }
  }
}
