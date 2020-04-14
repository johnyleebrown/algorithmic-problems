package regular.array;

/*
 * 1007
 */
public class MinimumDominoRotationsForEqualRow {
		public static class Solution {
				public int minDominoRotations(int[] a, int[] b) {
						int n = a.length;
						int ans = n + 1;
						for (int i = 1; i <= 6; i++) {
								boolean cantRotate = false;
								int countA = 0;
								int countB = 0;
								for (int j = 0; j < n; j++) {
										if (a[j] != i && b[j] != i) {
												cantRotate = true;
												break;
										}
										else {
												if (a[j] == i && b[j] != i) {
														countB++;
												}
												else if (b[j] == i && a[j] != i) {
														countA++;
												}
										}
								}
								if (!cantRotate) {
										ans = Math.min(ans, Math.min(countA, countB));
								}
						}
						return ans == n + 1 ? -1 : ans;
				}
		}
}
