package math.bits;

/**
 * 476
 */
public class NumberComplement {
	/**
	 * Sum of n and it's complement is all ones. So we make all ones number
	 * first by shifting left and adding 1 len(num) times. Then just subtract.
	 */
	public class Solution {
		public int findComplement(int num) {
			int n = 0;
			while (n < num) {
				n = (n << 1) | 1;
			}
			return n - num;
		}
	}
}
