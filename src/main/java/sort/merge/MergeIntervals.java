package sort.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56
 */
public class MergeIntervals {
    public static class Solution {
        public int[][] merge(int[][] ar) {
            int n = ar.length;
            if (n == 0) return new int[0][];
            Arrays.sort(ar, (a, b) -> a[0] - b[0]);
            List<int[]> ans = new ArrayList<>();
            int i = 0;
            while (i < n) {
                int end = ar[i][1];
                int j = i;
                while (j + 1 < n && end >= ar[j + 1][0]) {
                    end = Math.max(ar[j + 1][1], end);
                    j++;
                }
                ans.add(new int[]{ar[i][0], end});
                i = j + 1;
            }
            int[][] ret = new int[ans.size()][2];
            int k = 0;
            for (int[] x : ans) {
                ret[k++] = x;
            }
            return ret;
        }
    }
}