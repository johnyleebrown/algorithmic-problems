package sort.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 503
 *
 * ======
 *
 * Task.
 *
 * Given a circular array (the next element of the last element is the first
 * element of the array), print the Next Greater Number for every element. The
 * Next Greater Number of a number x is the first greater number to its
 * traversing-order next in the array, which means you could search circularly
 * to find its next greater number. If it doesn't exist, output -1 for this
 * number.
 *
 * ======
 *
 * Source: Leetcode
 */
public class NextGreaterElementII {
	/**
	 * Circularly using Decreasing MQ to input all elements twice. So if we
	 * didn't get any results after first iteration, do it again.
	 */
	class Solution {
		public int[] nextGreaterElements(int[] nums) {
			int n = nums.length;
			int[] result = new int[n];
			DecreasingMQ q = new DecreasingMQ();

			int times = 2;
			while (--times >= 0) {
				for (int i = n - 1; i >= 0; i--) {
					result[i] = q.push(nums[i]);
				}
			}

			return result;
		}

		private class DecreasingMQ {
			private Deque<Integer> q = new ArrayDeque<>();

			public int push(int val) {
				while (!q.isEmpty() && val >= q.peekLast()) {
					q.removeLast();
				}

				int nearestMax = -1;

				if (!q.isEmpty()) {
					nearestMax = q.peekLast();
				}

				q.addLast(val);

				return nearestMax;
			}
		}
	}
}
