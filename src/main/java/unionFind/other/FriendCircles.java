package unionFind.other;

/**
 * 547
 */
public class FriendCircles {

  public static class Solution {

    private int circles;
    private int[] parents, rank;

    public int findCircleNum(int[][] M) {
      int n = M.length, m = M[0].length;
      if (n == 0 || m == 0) return 0;
      circles = n;
      parents = new int[n];
      rank = new int[n];
      for (int i = 0; i < n; i++) {
        parents[i] = i;
      }

      for (int i = 0; i < n; i++) {
        for (int j = 0; j < m; j++) {
          if (j == i) continue;
          if (M[i][i] == M[i][j] && M[i][i] == 1) union(i, j);
        }
      }

      return circles;
    }

    private void union(int p, int q) {
      int pP = find(p), qP = find(q);
      if (pP == qP) return;
      if (rank[pP] > rank[qP]) parents[pP] = qP;
      else {
        parents[qP] = pP;
        if (rank[pP] == rank[qP]) rank[pP]++;
      }

      circles--;
    }

    private int find(int p) {
      while (p != parents[p]) {
        p = parents[parents[p]];
      }
      return p;
    }
  }
}
