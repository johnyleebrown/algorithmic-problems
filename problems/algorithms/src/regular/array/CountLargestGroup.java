package regular.array;

/**
 * 1399
 *
 * ======
 *
 * Task.
 *
 * Given an integer n. Each number from 1 to n is grouped according to the sum
 * of its digits.
 *
 * Return how many groups have the largest size.
 *
 * ======
 *
 * Source: Leetcode
 */
public class CountLargestGroup {
	public static class Solution {
		public int countLargestGroup(int n) {
			int[] sumsOfDigits = new int[9 * 4 + 1];
			int max = 0;
			for (int i = 1; i <= n; i++) {
				int x = getDigitSum(i);
				sumsOfDigits[x]++;
				max = Math.max(max, sumsOfDigits[x]);
			}

			int res = 0;
			for (int i = 1; i <= 9 * 4; i++) {
				if (sumsOfDigits[i] == max) {
					res++;
				}
			}

			return res;
		}

		private int getDigitSum(int n) {
			int sum = 0;
			while (n > 0) {
				sum += n % 10;
				n /= 10;
			}
			return sum;
		}
	}
}