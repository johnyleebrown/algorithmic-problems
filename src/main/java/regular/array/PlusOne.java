package regular.array;

/**
 * 66
 */
public class PlusOne {
		public static class Solution {
				public int[] plusOne(int[] a) {
						int l = 1;
						int n = a.length;
						for (int i = n - 1; i >= 0; i--) {
								if (l == 1) {
										if (a[i] == 9) {
												a[i] = 0;
										}
										else {
												a[i]++;
												l = 0;
												break;
										}
								}
								else {
										break;
								}
						}
						if (l == 1) {
								int[] ans = new int[n + 1];
								ans[0] = 1;
								for (int i = 0; i < n; i++) {
										ans[i + 1] = a[i];
								}
								return ans;
						}
						return a;
				}
		}
}
