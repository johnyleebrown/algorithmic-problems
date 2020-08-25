package math.bits;

/**
 * 191
 */
public class NumberOf1Bits {
	public class Solution {
		public int hammingWeight(int n) {
			int c = 0;
			for (int i = 1; i <= 32; i++)
				if ((n & (1 << i)) != 0)
					c++;
			return c;
		}
	}

	public class Solution2 {
		public int hammingWeight(int n) {
			int sum = 0;
			while (n != 0) {
				sum++;
				n &= (n - 1);
			}
			return sum;
		}
	}
}
