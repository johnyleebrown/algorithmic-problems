package queue.monotonicQueue;

import queue.monotonicQueue.ds.DecreasingMonotonicQueue;

/**
 * 239
 *
 * ======
 *
 * Task.
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return
 * the max sliding window.
 */
public class SlidingWindowMaximum
{
	class Solution
	{
		public int[] maxSlidingWindow(int[] nums, int k)
		{
			if (nums.length == 0)
			{
				return new int[]{};
			}

			DecreasingMonotonicQueue q = new DecreasingMonotonicQueue();

			int i = 0;
			for (; i < k - 1; i++)
			{
				q.push(nums[i]);
			}

			int[] result = new int[nums.length - k + 1];
			for (; i < nums.length; i++)
			{
				q.push(nums[i]);
				result[i - k + 1] = q.getMax();
				q.removeFirst();
			}

			return result;
		}
	}
}
