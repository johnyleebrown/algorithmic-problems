package regular.array;

/**
 * 26
 */
public class RemoveDuplicatesFromSortedArray {
	public static class Solution {
		public int removeDuplicates(int[] ar) {
			int n = ar.length;
			if (n == 0) return 0;
			int i = 0;
			int j = 0;
			while (j < n) {
				while (j + 1 < n && ar[j + 1] == ar[j]) {
					j++;
				}
				ar[i] = ar[j];
				i++;
				j++;
			}
			return i;
		}
	}
}
