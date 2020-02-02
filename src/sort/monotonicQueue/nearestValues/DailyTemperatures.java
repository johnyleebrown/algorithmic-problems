package sort.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 739
 *
 * ======
 *
 * Task.
 *
 * Given a list of daily temperatures T, return a list such that, for each day in the input, tells you how many days you
 * would have to wait until a warmer temperature. If there is no future day for which this is possible, put 0 instead.
 *
 * For example, given the list of temperatures T = [73, 74, 75, 71, 69, 72, 76, 73], your output should be [1, 1, 4, 2,
 * 1, 1, 0, 0].
 *
 * ======
 *
 * Source: Leetcode
 */
public class DailyTemperatures
{
	/**
	 * Same as 496.
	 */
	class Solution
	{
		public int[] dailyTemperatures(int[] T)
		{
			DMQ q = new DMQ();
			int n = T.length;
			int[] result = new int[n];

			for (int i = n - 1; i >= 0; i--)
			{
				result[i] = q.push(new Item(T[i], i));
			}

			return result;
		}

		public class DMQ
		{
			private Deque<Item> q = new ArrayDeque<>();

			public int push(Item newItem)
			{
				while (!q.isEmpty() && newItem.val >= q.peekLast().val)
				{
					q.removeLast();
				}

				int diff = 0;
				if (!q.isEmpty())
				{
					diff = q.peekLast().ind - newItem.ind;
				}

				q.addLast(newItem);
				return diff;
			}
		}

		private class Item
		{
			int val, ind;

			Item(int val, int ind)
			{
				this.val = val;
				this.ind = ind;
			}
		}
	}

	class Solution2
	{
		public int[] dailyTemperatures(int[] T)
		{
			int n = T.length;
			int[] nearest = new int[n]; // nearest biggest indexes from right to left
			int[] result = new int[n];

			for (int i = n - 1; i >= 0; i--)
			{
				int j = i + 1;
				while (j < n && T[j] <= T[i])
				{
					j = nearest[j];
				}
				nearest[i] = j;

				result[i] = nearest[i] == n ? 0 : nearest[i] - i;
			}

			return result;
		}
	}
}