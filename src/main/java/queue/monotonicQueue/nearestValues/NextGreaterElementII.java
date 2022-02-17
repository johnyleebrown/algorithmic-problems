package queue.monotonicQueue.nearestValues;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

/**
 * 503
 */
public class NextGreaterElementII {

	/**
	 * Circularly using Decreasing MQ to input all elements twice. So if we didn't get any
	 * results
	 * after first iteration, do it again.
	 */
	public static class Solution {

		public int[] nextGreaterElements(int[] ar) {
			MQImpl mq = new MQImpl(ar.length);
			for (int i = 0; i < ar.length; i++) {
				mq.push(new Pair(ar[i], i));
			}
			for (int i = 0; i < ar.length; i++) {
				mq.push(new Pair(ar[i], i));
			}
			return mq.ans;
		}

		interface MQ {

			void push(Pair e);
		}

		private class MQImpl implements MQ {

			Deque<Pair> q = new ArrayDeque<>();
			int[] ans;

			MQImpl(int n) {
				ans = new int[n];
				Arrays.fill(ans, -1);
			}

			public void push(Pair e) {
				// rem
				while (!q.isEmpty() && q.peekLast().val < e.val) {
					Pair p = q.peekLast();
					ans[p.ind] = e.val;
					q.removeLast();
				}
				// add
				q.addLast(e);
			}
		}

		private class Pair {

			int val, ind;

			Pair(int v, int i) {
				val = v;
				ind = i;
			}
		}
	}

	class Solution2 {

		public int[] nextGreaterElements(int[] nums) {
			int n = nums.length;
			Stack<Integer> st = new Stack<>();
			int[] ans = new int[n];
			for (int i = n - 1; i >= 0; i--) {
				while (!st.isEmpty() && st.peek() <= nums[i]) {
					st.pop();
				}
				ans[i] = st.isEmpty() ? Integer.MIN_VALUE : st.peek();
				st.push(nums[i]);
			}
			for (int i = n - 1; i >= 0; i--) {
				while (!st.isEmpty() && st.peek() <= nums[i]) {
					st.pop();
				}
				if (!st.isEmpty()) {
					ans[i] = Math.max(st.peek(), ans[i]);
				} else if (ans[i] == Integer.MIN_VALUE) {
					ans[i] = -1;
				}
				st.push(nums[i]);
			}
			return ans;
		}
	}
}
