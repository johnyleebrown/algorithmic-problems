package sort.merge;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 56
 */
public class MergeIntervals {
		public static class Solution {
				public int[][] merge(int[][] a) {
						Arrays.sort(a, (b, c) -> (b[0] - c[0]));
						List<int[]> ansList = new ArrayList<>();
						int n = a.length;
						int i = 0;
						while (i < n) {
								if (i + 1 < n && a[i][1] >= a[i + 1][0]) {
										int s = a[i][0];
										int e = a[i][1];
										while (i + 1 < n && e >= a[i + 1][0]) {
												e = Math.max(e, a[i + 1][1]);
												i++;
										}
										ansList.add(new int[]{s, e});
								}
								else {
										ansList.add(a[i]);
								}
								i++;
						}
						int[][] ans = new int[ansList.size()][2];
						for (int j = 0; j < ansList.size(); j++) {
								ans[j] = ansList.get(j);
						}
						return ans;
				}
		}
}