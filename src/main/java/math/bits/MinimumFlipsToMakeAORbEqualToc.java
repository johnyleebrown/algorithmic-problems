package math.bits;

/**
 * 1318
 *
 * ======
 *
 * Task.
 *
 * Given 3 positives numbers a, b and c. Return the minimum flips required in
 * some bits of a and b to make ( a OR b == c ). (bitwise OR operation). Flip
 * operation consists of change any single bit 1 to 0 or change the bit 0 to 1
 * in their binary representation.
 */
public class MinimumFlipsToMakeAORbEqualToc {
	static class Solution1 {
		public int minFlips(int a, int b, int c) {
			int result = 0;
			for (int i = 0; i < 32; ++i) {
				int ci = (c & (1 << i)) != 0 ? 1 : 0;
				int ai = (a & (1 << i)) != 0 ? 1 : 0;
				int bi = (b & (1 << i)) != 0 ? 1 : 0;

				if (ci == 0) {
					result += ai + bi;
				}
				else if (ai == 0 && bi == 0) {
					result++;
				}
			}

			return result;
		}
	}

	/**
	 * Straightforward.
	 */
	static class Solution2 {
		public int minFlips(int a, int b, int c) {
			int result = 0;
			StringBuilder binA = new StringBuilder(Integer.toBinaryString(a)).reverse();
			StringBuilder binB = new StringBuilder(Integer.toBinaryString(b)).reverse();
			StringBuilder binC = new StringBuilder(Integer.toBinaryString(c)).reverse();

			for (int i = 0; i < Math.max(Math.max(binA.length(), binB.length()), binC.length()); i++) {
				int bitA = i < binA.length() ? binA.charAt(i) - '0' : 0;
				int bitB = i < binB.length() ? binB.charAt(i) - '0' : 0;
				int bitC = i < binC.length() ? binC.charAt(i) - '0' : 0;

				if ((bitA | bitB) != bitC) {
					if (bitA == 0 && bitB == 1 && bitC == 0) result += 1;
					else if (bitA == 1 && bitB == 0 && bitC == 0) result += 1;
					else if (bitA == 1 && bitB == 1 && bitC == 0) result += 2;
					else if (bitA == 0 && bitB == 0 && bitC == 1) result += 1;
				}
			}

			return result;
		}
	}
}
