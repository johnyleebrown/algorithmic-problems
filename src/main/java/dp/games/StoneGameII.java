package dp.games;

/**
 * 1140
 *
 * ======
 *
 * Task.
 *
 * Alex and Lee continue their games with piles of stones.  There are a number of piles arranged in
 * a row, and each pile has a positive integer number of stones piles[i].  The objective of the game
 * is to end with the most stones.
 *
 * Alex and Lee take turns, with Alex starting first.  Initially, M = 1.
 *
 * On each player's turn, that player can take all the stones in the first X remaining piles, where
 * 1 <= X <= 2M.  Then, we set M = max(M, X).
 *
 * The game continues until all the stones have been taken.
 *
 * Assuming Alex and Lee play optimally, return the maximum number of stones Alex can get.
 *
 * ======
 *
 * Source: Leetcode
 */
public class StoneGameII {

  /**
   * every time we want to return the maximum we can get from the player it means that every player
   * plays optimally and we want to minimize their results from various indexes so since the
   * strategy is the same for both players => recurence function gon be same as well so recurrence
   * gon be smth like find min of next player min = Math.min(min, helper(a, i+x, Math.max(M,x)));
   * our maximum is sum of the part to the right minus their minimum: dp(i,M) = sums[i] - min
   */
  public static class Solution {

    public int solve() {
      return -1;
    }
  }
}