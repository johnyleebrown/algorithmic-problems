package intervals.other;

import java.util.List;

/**
 * @company fb
 * @url https://leetcode.com/discuss/interview-experience/1151028/facebook-phone-screen-london-mar-2021-reject
 * @pattern merge
 */
public class RedTapeBlueTape {

  public static class Solution1 {

    public boolean solve(int[] redTape, List<int[]> blueTapes) {
      // merge blue tape intervals
      if (blueTapes.size() == 0) {
        return false;
      }
      if (blueTapes.size() == 1) {
        return !(
            blueTapes.get(0)[0] == redTape[0]
            && blueTapes.get(0)[1] == redTape[1]);
      }
      int i = 0;
      int[] cur = blueTapes.get(i);
      int left = cur[0], right = cur[1];
      while (i + 1 < blueTapes.size() && blueTapes.get(i + 1)[0] <= right) {
        right = Math.max(blueTapes.get(++i)[1], right);
      }
      if (i != blueTapes.size() - 1) {
        return true;
      }
      return !(left == redTape[0] && right == redTape[1]);
    }
  }
}