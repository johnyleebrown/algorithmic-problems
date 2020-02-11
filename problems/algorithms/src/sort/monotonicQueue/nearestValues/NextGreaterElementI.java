package sort.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 496
 *
 * ======
 *
 * Task.
 *
 * You are given two arrays (without duplicates) nums1 and nums2 where nums1’s elements are subset of nums2. Find all
 * the next greater numbers for nums1's elements in the corresponding places of nums2.
 *
 * The Next Greater Number of a number x in nums1 is the first greater number to its right in nums2. If it does not
 * exist, output -1 for this number.
 *
 * ======
 *
 * Source: Leetcode
 */
public class NextGreaterElementI
{
	/**
	 *
	 */
	class Solution
	{
		private Map<Integer, Integer> m = new HashMap<>(); // value, index of nums1

		public int[] nextGreaterElement(int[] nums1, int[] nums2)
		{
			for (int i = 0; i < nums1.length; i++)
			{
				m.put(nums1[i], i);
			}

			DecreasingMQ q = new DecreasingMQ(-1, nums1.length);
			for (int i = nums2.length - 1; i >= 0; i--)
			{
				q.push(nums2[i]);
			}

			return q.getNearestValues();
		}

		/**
		 * Decreasing Nearest Values MQ
		 */
		private class DecreasingMQ
		{
			private Deque<Integer> q;
			private int defaultNextValue;
			private int[] nearestValues;

			public DecreasingMQ(int defaultNextValue, int n)
			{
				q = new ArrayDeque<>();
				nearestValues = new int[n];
				this.defaultNextValue = defaultNextValue;
			}

			public void push(int val)
			{
				while (!q.isEmpty() && val >= q.peekLast())
				{
					q.removeLast();
				}

				customNearestValuesUpdate(val);

				q.addLast(val);
			}

			private void customNearestValuesUpdate(int val)
			{
				if (m.containsKey(val))
				{
					if (q.isEmpty())
					{
						nearestValues[m.get(val)] = defaultNextValue;
					}
					else
					{
						nearestValues[m.get(val)] = q.peekLast();
					}
				}
			}

			public int[] getNearestValues()
			{
				return nearestValues;
			}
		}
	}
}