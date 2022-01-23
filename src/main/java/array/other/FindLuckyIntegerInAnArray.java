package array.other;

/**
 * 1394
 *
 * ======
 *
 * Task.
 *
 * Given an array of integers arr, a lucky integer is an integer which has a
 * frequency in the array equal to its value.
 *
 * Return a lucky integer in the array. If there are multiple lucky integers
 * return the largest of them. If there is no lucky integer return -1.
 *
 * ======
 *
 * Source: Leetcode
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
					}
					else {
						l = Math.max(l, a[i]);
					}
				}
			}
			return l == null ? -1 : l;
		}
	}
}
