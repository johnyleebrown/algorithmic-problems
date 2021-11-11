package dp.linear.both;

/**
 * 1871. Jump Game VII https://leetcode.com/problems/jump-game-vii/
 */
public class JumpGameVII {

  /**
   * DFS Top-Down TLE
   */
  public static class Solution1 {

    public boolean canReach(String s, int minJump, int maxJump) {
      return canReachHelper(0, s, minJump, maxJump, new Boolean[s.length()]);
    }

    private boolean canReachHelper(int i, String s, int minJump, int maxJump,
        Boolean[] dp) {
      if (i == s.length() - 1) {
        return true;
      }
      if (dp[i] != null) {
        return dp[i];
      }
      boolean ans = false;
      for (int j = i + minJump; j <= Math.min(i + maxJump, s.length() - 1); j++) {
        if (s.charAt(j) != '0') {
          continue;
        }
        ans |= canReachHelper(j, s, minJump, maxJump, dp);
      }
      dp[i] = ans;
      return ans;
    }
  }
}