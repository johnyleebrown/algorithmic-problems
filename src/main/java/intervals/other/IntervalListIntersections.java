package intervals.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 986
 */
public class IntervalListIntersections {
    class Solution {
        public int[][] intervalIntersection(int[][] A, int[][] B) {
            List<int[]> ans = new ArrayList<>();
            int i = 0, j = 0;
            while (i < A.length && j < B.length) {
                int start = Math.max(A[i][0], B[j][0]);
                int end = Math.min(A[i][1], B[j][1]);
                if (start <= end) {
                    ans.add(new int[]{start, end});
                }
                if (A[i][1] > B[j][1]) {
                    j++;
                } else {
                    i++;
                }
            }

            int[][] res = new int[ans.size()][2];
            for (int k = 0; k < ans.size(); k++) {
                res[k] = ans.get(k);
            }

            return res;
        }
    }
}
