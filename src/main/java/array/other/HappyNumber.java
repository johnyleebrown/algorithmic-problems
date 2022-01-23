package array.other;

import java.util.HashSet;
import java.util.Set;

/**
 * 202
 *
 * ======
 *
 * Task.
 *
 * Write an algorithm to determine if a number is "happy". A happy number is a
 * number defined by the following process: Starting with any positive integer,
 * replace the number by the sum of the squares of its digits, and repeat the
 * process until the number equals 1 (where it will stay), or it loops endlessly
 * in a cycle which does not include 1. Those numbers for which this process
 * ends in 1 are happy numbers.
 *
 * ======
 *
 * Source: Leetcode
 */
public class HappyNumber {
	/**
	 * If we can't reach one - there will be a cycle - that means we will be
	 * having repeated numbers.
	 */
	class Solution {
		public boolean isHappy(int n) {
			Set<Integer> seen = new HashSet<>();
			while (n != 1) {
				if (!seen.add(n)) {
					return false;
				}
				n = getDigitSquaresSum(n);
			}
			return true;
		}

		private int getDigitSquaresSum(int n) {
			int sum = 0;
			while (n > 0) {
				int x = n % 10;
				sum += x * x;
				n /= 10;
			}
			return sum;
		}
	}
}
