package queue.monotonicQueue;

import queue.monotonicQueue.ds.IncreasingMonotonicQueueNearestValues;
import queue.monotonicQueue.ds.impl.IncreasingMonotonicQueueNearestValuesImpl;

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
	static class Solution
	{
		public int largestRectangleArea(int[] heights)
		{
			int n = heights.length;

			IncreasingMonotonicQueueNearestValues qIncreasingFromLeft = new IncreasingMonotonicQueueNearestValuesImpl(n, -1);
			for (int i = 0; i < n; i++)
			{
				qIncreasingFromLeft.push(heights[i], i);
			}

			IncreasingMonotonicQueueNearestValues qIncreasingFromRight = new IncreasingMonotonicQueueNearestValuesImpl(n, n);
			for (int i = n - 1; i >= 0; i--)
			{
				qIncreasingFromRight.push(heights[i], i);
			}

			int result = 0;
			for (int i = 0; i < n; i++)
			{
				int r = qIncreasingFromRight.getNearestValueLessThanAtIndex(i);
				int l = qIncreasingFromLeft.getNearestValueLessThanAtIndex(i);
				int width = r - l - 1;
				int area = heights[i] * width;
				result = Math.max(result, area);
			}

			return result;
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