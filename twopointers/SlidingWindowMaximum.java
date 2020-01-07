import java.util.PriorityQueue;

/**
 * 239
 *
 * Task.
 *
 * Given an array nums, there is a sliding window of size k which is moving from the very left of the array to the very
 * right. You can only see the k numbers in the window. Each time the sliding window moves right by one position. Return
 * the max sliding window.
 */
public class SlidingWindowMaximum
{
	/**
	 * Priority queue.
	 */
	class Solution1
	{
		public int[] maxSlidingWindow(int[] nums, int k)
		{
			if (nums.length == 0)
			{
				return new int[]{};
			}

			PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
			int r = 0;
			for (; r < k - 1; r++)
			{
				pq.add(nums[r]);
			}
			int l = 0;
			int[] result = new int[nums.length - k + 1];
			for (; r < nums.length; r++)
			{
				pq.add(nums[r]);
				result[l] = pq.peek();
				pq.remove(new Integer(nums[l]));
				l++;
			}
			return result;
		}
	}
}
