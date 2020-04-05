package math.bits;

/**
 * 477
 */
public class TotalHammingDistance {
	public static class Solution {
		public int totalHammingDistance(int[] a) {
			int total = 0;
			int n = a.length;
			for (int j = 0; j < 32; j++) {
				int bitCount = 0;
				for (int num : a) {
					bitCount += (num >> j) & 1;
				}
				total += bitCount * (n - bitCount);
			}
			return total;
		}
	}
}
