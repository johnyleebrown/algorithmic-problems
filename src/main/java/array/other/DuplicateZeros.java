package array.other;

/**
 * 1089
 *
 * ======
 *
 * Task.
 *
 * Given a fixed length array arr of integers, duplicate each occurrence of
 * zero, shifting the remaining elements to the right.
 *
 * Note that elements beyond the length of the original array are not written.
 *
 * Do the above modifications to the input array in place, do not return
 * anything from your function.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DuplicateZeros {
		public static class Solution {
				public void duplicateZeros(int[] a) {
						int i = 0;
						int n = a.length;
						while (i < n) {
								if (a[i] == 0) {
										int r = i;
										while (r < n && a[r] == 0) {
												r++;
										}
										int diff = r - i;
										int end = i + diff * 2;
										shift(a, end, diff);
										while (i < n && i < end) {
												a[i++] = 0;
										}
								}
								else i++;
						}
				}

				private void shift(int[] a, int start, int shift) {
						int n = a.length;
						for (int i = n - 1; i >= start; i--) {
								a[i] = a[i - shift];
						}
				}
		}
}