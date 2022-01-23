package array.other;

/**
 * 1394
 */
public class FindLuckyIntegerInAnArray {

	public static class Solution {

		public int findLucky(int[] a) {
			int n = a.length;
			int[] c = new int[500 + 1];
			for (int i = 0; i < n; i++) {
				c[a[i]]++;
			}
			Integer l = null;
			for (int i = 0; i < n; i++) {
				if (c[a[i]] == a[i]) {
					if (l == null) {
						l = a[i];
					} else {
						l = Math.max(l, a[i]);
					}
				}
			}
			return l == null ? -1 : l;
		}
	}
}
