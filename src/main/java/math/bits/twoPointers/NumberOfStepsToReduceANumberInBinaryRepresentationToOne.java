package math.bits.twoPointers;

/**
 * 1404
 */
public class NumberOfStepsToReduceANumberInBinaryRepresentationToOne {

	/**
	 * 2 pointers.
	 *
	 * We know that division /2 is right shift and addition of 1 is &1 as well
	 * that if the last digit of a binary number is 1, the number is odd;  if
	 * it’s 0, the number is even.
	 */
	public static class Solution {

		public int numSteps(String s) {
			int n = s.length();
			int l = 0;
			int r = n - 1;
			int steps = 0;
			StringBuilder sb = new StringBuilder(s);
			while (l < r) {
				if (sb.charAt(r) == '1') {
					boolean newl = addOne(l, r, sb);
					if (newl) {
						r++;
					}
				} else { // divide by 2 => shift right
					r--;
				}
				steps++;
			}
			return steps;
		}

		private boolean addOne(int l, int r, StringBuilder sb) {
			while (l <= r) {
				char cur = sb.charAt(r);
				if (cur == '1') {
					sb.setCharAt(r, '0');
					r--;
				} else if (cur == '0') {
					sb.setCharAt(r, '1');
					return false;
				} else {
					return false;
				}
			}
			sb.insert(l, '1');
			return true;
		}
	}
}