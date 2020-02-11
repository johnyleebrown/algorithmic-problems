package sort.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 84
 *
 * ======
 *
 * Task.
 *
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1, find the area
 * of largest rectangle in the histogram.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LargestRectangleInHistogram
{
	/**
	 * Solution using queue. The biggest area involving a height at i is between nearest smallest value on the left, and
	 * nearest smallest value on the right.
	 */
	class Solution
	{
		public int largestRectangleArea(int[] heights)
		{
			MQ q = new MQ();
			for (int i = 0; i <= heights.length; i++)
			{
				int currentValue = i != heights.length ? heights[i] : 0;
				q.push(new Item(currentValue, i));
			}
			return q.maxArea;
		}

		private class MQ
		{
			private Deque<Item> q = new ArrayDeque<>();
			private int maxArea;

			public void push(Item newItem)
			{
				while (!q.isEmpty() && newItem.val < q.peekLast().val)
				{
					Item upperBoundary = q.removeLast();
					int leftBoundaryIndex = -1;
					if (!q.isEmpty())
					{
						leftBoundaryIndex = q.peekLast().ind;
					}
					int width = newItem.ind - leftBoundaryIndex - 1;
					int currentArea = width * upperBoundary.val;

					maxArea = Math.max(maxArea, currentArea);
				}

				q.addLast(newItem);
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

	/**
	 * Solution using arrays of nearest smaller values. The biggest area involving a height at i is between nearest
	 * smallest value on the left, and * nearest smallest value on the right.
	 */
	static class Solution2
	{
		public int largestRectangleArea(int[] heights)
		{
			int n = heights.length;

			int[] leftToRightNearestValuesLessThan = new int[n];
			for (int i = 0; i < n; i++)
			{
				int j = i - 1;
				while (j >= 0 && heights[i] <= heights[j])
				{
					j = leftToRightNearestValuesLessThan[j];
				}
				leftToRightNearestValuesLessThan[i] = j;
			}

			int[] rightToLeftNearestValuesLessThan = new int[n];
			for (int i = n - 1; i >= 0; i--)
			{
				int j = i + 1;
				while (j < n && heights[i] <= heights[j])
				{
					j = rightToLeftNearestValuesLessThan[j];
				}
				rightToLeftNearestValuesLessThan[i] = j;
			}

			int result = 0;
			for (int i = 0; i < n; i++)
			{
				int area = heights[i] * (rightToLeftNearestValuesLessThan[i] - leftToRightNearestValuesLessThan[i] - 1);
				result = Math.max(result, area);
			}

			return result;
		}
	}
}