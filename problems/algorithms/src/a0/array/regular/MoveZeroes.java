package a0.array.regular;

/**
 * 283
 */
public class MoveZeroes {
	public static class Solution {
		public void moveZeroes(int[] a) {
			int l = 0;
			int r = 0;
			int n = a.length;
			while (r < n) {
				if (a[r] != 0) {
					if (r != l) {
						a[l] = a[r];
						a[r] = 0;
					}
					l++;
				}
				r++;
			}
		}
	}
}
