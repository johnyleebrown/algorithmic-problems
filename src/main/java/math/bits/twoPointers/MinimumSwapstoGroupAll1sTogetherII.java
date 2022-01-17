package math.bits.twoPointers;

import java.math.BigInteger;

/**
 * 2134
 */
public class MinimumSwapstoGroupAll1sTogetherII {

	public static void main(String[] args) {
		Solution2 s = new Solution2();
		// 1
		System.out.println(s.minSwaps(new int[]{0, 1, 0, 1, 1, 0, 0}));
		// 2
		System.out.println(s.minSwaps(new int[]{0, 1, 1, 1, 0, 0, 1, 1, 0}));
		// 0
		System.out.println(s.minSwaps(new int[]{1, 1, 0, 0, 1}));
		// 14
		System.out.println(s.minSwaps(
				new int[]{0, 1, 1, 0, 1, 1, 1, 1, 0, 1, 0, 1, 0, 0, 1, 0, 0, 0, 0, 0, 1, 1, 0, 1,
						0, 0, 0, 1, 1, 0, 1, 1, 0, 1, 1, 0, 1, 0, 1, 0, 0, 1, 1, 1, 0, 0, 0, 1, 0, 1,
						1, 0, 1, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 1, 0, 0}));
	}

	/**
	 * Bit math solution for cases when size of nums is not large.
	 * For other cases we can use big integer.
	 *
	 * group ones on the right, move them by 1 step, calculate the difference with original
	 *
	 * [0,1,0,1,1,0,0]
	 * [0,0,1,1,1,0,0]
	 * [0,0,0,0,1,1,1]
	 * [0,0,0,1,1,1,0]
	 */
	public static class Solution {

		public int minSwaps(int[] nums) {
			int ones = 0, n = nums.length, origin = 0;
			for (int i = n - 1, j = 0; i >= 0; i--, j++) {
				if (nums[i] == 1) {
					ones++;
					origin |= (1 << j);
				}
			}
			int zeros = n - ones;

			int base = 0;
			for (int i = 0; i < ones; i++) {
				base |= (1 << i);
			}

			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < zeros + 1; i++) {
				if (i != 0) base <<= 1;
				int diff = getDiff(base, origin, ones);
				ans = Math.min(ans, diff);
			}

			for (int i = 0; i < ones; i++) {
				base <<= 1;
				base |= (1 << i);
				int diff = getDiff(base, origin, ones);
				ans = Math.min(ans, diff);
			}

			return ans;
		}

		private int getDiff(int base, int origin, int ones) {
			return ones - countSetBits(base & origin);
		}

		public int countSetBits(int num) {
			int count = 0, temp = 1;
			for (int i = 0; i < 31; i++) {
				if ((num & temp) != 0) {
					count++;
				}
				temp <<= 1;
			}
			return count;
		}
	}

	/**
	 * BigInteger
	 */
	public static class Solution2 {

		public int minSwaps(int[] nums) {
			int ones = 0;
			int n = nums.length;
			BigInteger origin = BigInteger.ZERO;

			for (int i = n - 1, j = 0; i >= 0; i--, j++) {
				if (nums[i] == 1) {
					ones++;
					origin = origin.or(BigInteger.ONE.shiftLeft(j));
				}
			}
			int zeros = n - ones;

			BigInteger base = BigInteger.ZERO;
			for (int i = 0; i < ones; i++) {
				base = base.or(BigInteger.ONE.shiftLeft(i));
			}

			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < zeros + 1; i++) {
				if (i != 0) base = base.shiftLeft(1);
				int diff = getDiff(base, origin, ones);
				ans = Math.min(ans, diff);
			}

			for (int i = 0; i < ones; i++) {
				base = base.shiftLeft(1);
				base = base.or(BigInteger.ONE.shiftLeft(i));
				int diff = getDiff(base, origin, ones);
				ans = Math.min(ans, diff);
			}

			return ans;
		}

		private int getDiff(BigInteger base, BigInteger origin, int ones) {
			return ones - countSetBits(base.and(origin));
		}

		public int countSetBits(BigInteger num) {
			int count = 0;
			BigInteger temp = BigInteger.ONE;
			for (int i = 0; i < 31; i++) {
				if (!num.and(temp).equals(BigInteger.ZERO)) {
					count++;
				}
				temp = temp.shiftLeft(1);
			}
			return count;
		}
	}
}
