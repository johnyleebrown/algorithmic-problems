package queue.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 581
 */
public class ShortestUnsortedContinuousSubarray {

	/**
	 * We have increasing mq from 0 to n-1 and decr from n-1 to 0. In incr we
	 * are looking for the minimum removed index - this element will be the
	 * leftmost element that is not in it's place - we will understand that by
	 * removing bigger elements with smaller ones. Similarly for decr queue.
	 */
	class Solution {

		public int findUnsortedSubarray(int[] nums) {
			int n = nums.length;
			MQ imq = new IMQ();
			for (int i = 0; i < n; i++) {
				imq.push(new int[]{nums[i], i});
			}
			MQ dmq = new DMQ();
			for (int i = n - 1; i >= 0; i--) {
				dmq.push(new int[]{nums[i], i});
			}
			if (imq.ind == null && dmq.ind == null) {
				return 0;
			}
			return dmq.ind - imq.ind + 1;
		}

		abstract class MQ {

			Deque<int[]> q = new ArrayDeque<>();
			Integer ind;

			abstract void push(int[] cur);

			abstract int getInd();
		}

		private class IMQ extends MQ {

			public void push(int[] cur) {
				while (!q.isEmpty() && q.peekLast()[0] > cur[0]) {
					ind = Math.min(getInd(), q.peekLast()[1]);
					q.removeLast();
				}
				q.addLast(cur);
			}

			protected int getInd() {
				if (ind == null) {
					return Integer.MAX_VALUE;
				}
				return ind;
			}
		}

		private class DMQ extends MQ {

			public void push(int[] cur) {
				while (!q.isEmpty() && q.peekLast()[0] < cur[0]) {
					ind = Math.max(q.peekLast()[1], getInd());
					q.removeLast();
				}
				q.addLast(cur);
			}

			protected int getInd() {
				if (ind == null) {
					return Integer.MIN_VALUE;
				}
				return ind;
			}
		}
	}
}