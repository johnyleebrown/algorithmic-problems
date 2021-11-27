package dp.coin_change;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * $INSERT_PROBLEM_NUMBER $INSERT_LINK
 */
public class SmallestSufficientTeam {

  /**
   * max sum - smallest number of coins задача набрать мин людей на amount amount в данном
   * случае это сет где все 1-цы нужен перебор от 0 выбранных до всех выбранных это amount
   * coins это люди человек это скил
   */
  public static class Solution {

    public int[] smallestSufficientTeam(String[] req_skills,
        List<List<String>> people) {
      // max sum - smallest number of coins
      // задача набрать мин людей на amount
      // amount в данном случае это сет где все 1-цы
      // нужен перебор от 0 выбранных до всех выбранных
      // это amount
      // coins это люди
      // человек это скил
      int n = req_skills.length;
      Map<String, Integer> skillsPos = new HashMap<>();
      for (int i = 0; i < n; i++) {
        skillsPos.put(req_skills[i], i);
      }
      int max = ((~0) >>> (32 - n));
      // amount, number people
      Map<Integer, Integer> map = new HashMap<>();
      for (int i = 0; i < people.size(); i++) {
        List<String> l = people.get(i);
        int x = l.size() == 0 ? 0 : getMaskForSkillSet(l, skillsPos);
        map.put(i, x);
      }
      int p = people.size();
      int[][] dp = new int[max + 1][p + 1];
      for (int i = 1; i <= max; i++) {
        dp[i][0] = Integer.MAX_VALUE;
      }
      for (int i = 1; i <= max; i++) {
        for (int j = 1; j <= p; j++) {
          if (people.get(j - 1).size() == 0) {
            dp[i][j] = dp[i][j - 1];
          } else {
            int mask = map.get(j - 1);
            int unset = unsetMask(i, mask);

            if (contains(i, mask) && dp[unset][j] != Integer.MAX_VALUE) {
              dp[i][j] = Math.min(dp[i][j - 1], dp[unset][j] + 1);
            } else {
              dp[i][j] = dp[i][j - 1];
            }
          }

        }
      }
      // for (int i = 0; i <= max; i++) {
      //     System.out.println();
      //     for (int j = 0; j <= p; j++) {
      //         if (dp[i][j] == Integer.MAX_VALUE) {
      //             System.out.print("max, ");
      //         } else System.out.print(dp[i][j]+", ");
      //     }
      // }
      int totalSize = dp[max][p];
      // trace
      int i = max, j = p;
      int[] ansAr = new int[totalSize];
      int k = 0;
      while (i >= 0 && j >= 0 && dp[i][j] != 0) {
        if (people.get(j - 1).size() == 0) {
          j--;
        } else {
          int mask = map.get(j - 1);
          int unset = unsetMask(i, mask);
          if (contains(i, mask) && dp[unset][j] != Integer.MAX_VALUE) {
            if (dp[i][j - 1] < dp[unset][j] + 1) {
              j--;
            } else {
              i = unset;
              ansAr[k++] = j - 1;
            }
          } else {
            j--;
          }
        }

      }
      return ansAr;
    }

    int unsetMask(int a, int mask) {
      return a ^ mask;
    }

    boolean contains(int a, int b) {
      return (b & a) == b;
    }

    int getMaskForSkillSet(List<String> skillSet,
        Map<String, Integer> skillsPos) {
      int res = 0;
      for (String sk : skillSet) {
        int pos = skillsPos.get(sk);
        res |= (1 << pos);
      }
      return res;
    }
  }
}