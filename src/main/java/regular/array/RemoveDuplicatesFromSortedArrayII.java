package regular.array;

/**
 * 80
 *
 * ======
 *
 * Task.
 *
 * Given a sorted array nums, remove the duplicates in-place such that duplicates appeared at most
 * twice and return the new length.
 *
 * Do not allocate extra space for another array, you must do this by modifying the input array
 * in-place with O(1) extra memory.
 *
 * ======
 *
 * Source: Leetcode
 */
public class RemoveDuplicatesFromSortedArrayII {
	public static class Solution {
		public int removeDuplicates(int[] ar) {
			int n = ar.length;
			if (n == 0) return 0;

			int i = 0;
			int j = 0;

			while (j < n) {

				// we can always write to i at least 1 char from j
				ar[i++] = ar[j++];

				// try to write second char
				if (j < n && ar[j - 1] == ar[j]) {
					ar[i++] = ar[j++];
				}

				// move till the end of repetitive chars if they exist
				while (j < n && ar[j - 1] == ar[j]) {
					j++;
				}
			}

			return i;
		}
	}
}