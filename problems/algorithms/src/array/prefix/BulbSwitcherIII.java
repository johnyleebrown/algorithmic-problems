package array.prefix;

/**
 * 1375
 *
 * ======
 *
 * Task.
 *
 * There is a room with n bulbs, numbered from 1 to n, arranged in a row from
 * left to right. Initially, all the bulbs are turned off.
 *
 * At moment k (for k from 0 to n - 1), we turn on the light[k] bulb. A bulb
 * change color to blue only if it is on and all the previous bulbs (to the
 * left) are turned on too.
 *
 * Return the number of moments in which all turned on bulbs are blue.
 *
 * ======
 *
 * Source: Leetcode
 */
public class BulbSwitcherIII
{
	/**
	 * Checked if prefix sum would work, so SF. We increment count if at moment
	 * i sum of turned on bulbs is the same as target.
	 */
	public static class Solution
	{
		public int numTimesAllBlue(int[] a)
		{
			int prefixSum = 0;
			int prefixTarget = 0;
			int ans = 0;
			for (int i = 0; i < a.length; i++)
			{
				prefixSum += a[i];
				prefixTarget += i + 1;
				if (prefixSum == prefixTarget)
					ans++;
			}
			return ans;
		}
	}
}
