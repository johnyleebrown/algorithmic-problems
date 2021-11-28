package unionFind.other;

import java.util.HashMap;
import java.util.Map;

/**
 * 947
 */
public class MostStonesRemovedWithSameRowOrColumn {

  /**
   * In another words, we want to find a number of items we can remove so only unique cc's
   * are left. So we find number of connected componenets. This number is the number of
   * unique parents, to count that we use a set size. We choose i th coordinate as a key
   * for components identification.
   */
  class Solution {

    private int c;
    private Map<String, String> p = new HashMap<>();

    public int removeStones(int[][] stones) {
      for (int[] stone : stones) {
        String i = "i" + stone[0];
        String j = "j" + stone[1];

        // merging components and decreasing number of them
        if (p.containsKey(i) && p.containsKey(j)) {
          unionExistingComponents(i, j);
        }
        // no need to increment number of components as we are adding
        // to existing one
        else if (p.containsKey(i)) {
          addToComponent(j, i);
        }
        // no need to increment number of components as we are adding
        // to existing one
        else if (p.containsKey(j)) {
          addToComponent(i, j);
        }
        // adding a cell to a new component
        else {
          p.put(i, i);
          p.put(j, i);

          c++;
        }
      }

      return stones.length - c;
    }

    private void addToComponent(String what, String where) {
      String parent = find(where);
      p.put(what, parent);
    }

    private void unionExistingComponents(String i, String j) {
      String pi = find(i);
      String pj = find(j);

      // if components are different we will merge them
      if (!pi.equals(pj)) {
        p.put(p.get(pi), pj);
        c--;
      }
    }

    private String find(String x) {
      while (p.get(x) != x) {
        x = p.get(x);
      }

      return x;
    }
  }
}
