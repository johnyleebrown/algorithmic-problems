package math;

import java.util.ArrayList;
import java.util.List;

public class MathUtil {

	static class Numbers {

		static int gcd(int a, int b) {
			if (b == 0) {
				return a;
			}
			return gcd(b, a % b);
		}

		/**
		 * Method to calculate all common divisors of two given numbers
		 */
		static int getCountCommonDivisors(int a, int b) {
			return getCommonDivisors(a, b).size();
		}

		static List<Integer> getCommonDivisors(int a, int b) {
			int gcd = gcd(a, b);
			List<Integer> ans = new ArrayList<>();
			for (int i = 1; i <= Math.sqrt(gcd); i++) {
				if (gcd % i == 0) {
					ans.add(i);
					if (gcd / i != i) ans.add(gcd / i);
				}
			}
			return ans;
		}
	}

	static class Bits {

		static boolean contains(long a, long b) {
			return (b & a) == b;
		}
	}
}
