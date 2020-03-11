package math.detectRecurrence;

import util.tester.Tester;

/**
 * 1362
 *
 * ======
 *
 * Task.
 *
 * Given an integer num, find the closest two integers in absolute difference
 * whose product equals num + 1 or num + 2.
 *
 * Return the two integers in any order.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ClosestDivisors
{
	/**
	 * Brute-force version would be to go from 1 to n and find divisors. But we
	 * can notice that after x=sqrt(n) divisors are the same as before x. So we
	 * have to loop from x to 1. Second optimization would be to start from the
	 * top, because, closer to the middle, the smaller the distance between the
	 * divisors, so we start there and choose the first one we encounter.
	 */
	private static class Solution
	{
		public int[] closestDivisors(int num)
		{
			int a = num + 1;
			int b = num + 2;
			int minDist = Integer.MAX_VALUE;
			int[] res = new int[2];

			// use sqrt because divisors are duplicating after the middle
			// start from max so we could find the answer faster
			for (int i = (int) Math.sqrt(a); i >= 1; i--)
			{
				// break when find first because we start from the middle
				// hence the best result will be the first one near it
				if (a % i == 0)
				{
					res[0] = i;
					res[1] = a / i;
					minDist = Math.abs(res[0] - res[1]);
					break;
				}
			}
			for (int i = (int) Math.sqrt(b); i >= 1; i--)
			{
				if (b % i == 0 && Math.abs(i - b / i) < minDist)
				{
					res[0] = i;
					res[1] = b / i;
					break;
				}
			}
			return res;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(8).expect(new int[]{3,3})
				.add(123).expect(new int[]{5,25})
				.add(999).expect(new int[]{40,25})
				.add(688427155).expect(new int[]{2409,285773})
				.expectAnyOrder()
				.run();
	}
}
