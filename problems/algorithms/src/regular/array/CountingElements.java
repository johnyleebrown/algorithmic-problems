package regular.array;

/**
 * Replace
 *
 * ======
 *
 * Task.
 *
 * Given an integer array arr, count element x such that x + 1 is also in arr.
 *
 * If there're duplicates in arr, count them seperately.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CountingElements {
	public static class Solution {
		public int countElements(int[] a) {
			int[] c = new int[1000 + 1];
			for (int i = 0; i < a.length; i++) {
				c[a[i]]++;
			}
			int ans = 0;
			for (int i = 0; i < 1000; i++) {
				if (c[i + 1] != 0) {
					ans += c[i];
				}
			}
			return ans;
		}
	}
}