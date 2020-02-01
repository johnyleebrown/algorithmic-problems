package queue.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 581
 *
 * ======
 *
 * Task.
 *
 * Given an integer array, you need to find one continuous subarray that if you only sort this subarray in ascending
 * order, then the whole array will be sorted in ascending order, too.
 *
 * You need to find the shortest such subarray and output its length.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ShortestUnsortedContinuousSubarray
{
	/**
	 * Using MQ to detect change of rising/falling slope.
	 */
	class Solution
	{
		public int findUnsortedSubarray(int[] nums)
		{
			int n = nums.length;
			IMQ imq = new IMQ(n);
			for (int i = 0; i < n; i++)
			{
				imq.push(new Item(nums[i], i));
			}
			int left = imq.getLeftBoundary();
			DMQ dmq = new DMQ(-1);
			for (int i = n - 1; i >= 0; i--)
			{
				dmq.push(new Item(nums[i], i));
			}
			int right = dmq.getRightBoundary();
			return left > right ? 0 : right - left + 1;
		}

		private class IMQ
		{
			Deque<Item> q = new ArrayDeque<>();
			int leftBoundary;

			IMQ(int leftBoundary)
			{
				this.leftBoundary = leftBoundary;
			}

			int getLeftBoundary()
			{
				return leftBoundary;
			}

			void push(Item newItem)
			{
				while (!q.isEmpty() && newItem.val < q.peekLast().val)
				{
					leftBoundary = Math.min(leftBoundary, q.peekLast().ind);
					q.removeLast();
				}
				q.addLast(newItem);
			}
		}

		private class DMQ
		{
			Deque<Item> q = new ArrayDeque<>();
			int rightBoundary;

			DMQ(int rightBoundary)
			{
				this.rightBoundary = rightBoundary;
			}

			int getRightBoundary()
			{
				return rightBoundary;
			}

			void push(Item newItem)
			{
				while (!q.isEmpty() && newItem.val > q.peekLast().val)
				{
					rightBoundary = Math.max(rightBoundary, q.peekLast().ind);
					q.removeLast();
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
}