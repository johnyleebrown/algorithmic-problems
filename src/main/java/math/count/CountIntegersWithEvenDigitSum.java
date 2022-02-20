package math.count;

/**
 * 2180
 */
public class CountIntegersWithEvenDigitSum {

	public static class Solution1 {

		public int countEven(int num) {

			int temp = num;
			int digitSum = 0;
			while (temp > 0) {
				digitSum += temp % 10;
				temp = temp / 10;
			}

			int res;
			if (digitSum % 2 == 0) {
				res = 5 * (num / 10) + (num % 10) / 2;
			} else {
				res = 5 * (num / 10) + (num % 10 + 1) / 2 - 1;
			}

			return res;
		}
	}

	/**
	 * SF
	 */
	public static class Solution2 {

		public int countEven(int num) {
			int c = 0;
			for (int i = 1; i <= num; i++) {
				int k = i, sum = 0;
				while (k > 0) {
					sum += k % 10;
					k /= 10;
				}
				if (sum % 2 == 0) c++;
			}
			return c;
		}
	}
}
