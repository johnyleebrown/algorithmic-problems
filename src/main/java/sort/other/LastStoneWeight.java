package sort.other;

import java.util.PriorityQueue;

/**
 * 1046
 *
 * ======
 *
 * Task.
 *
 * We have a collection of stones, each stone has a positive integer weight.
 *
 * Each turn, we choose the two heaviest stones and smash them together. Suppose
 * the stones have weights x and y with x <= y.  The result of this smash is:
 *
 * If x == y, both stones are totally destroyed; If x != y, the stone of weight
 * x is totally destroyed, and the stone of weight y has new weight y-x.
 *
 * At the end, there is at most 1 stone left.  Return the weight of this stone
 * (or 0 if there are no stones left.)
 *
 * ======
 *
 * Source: Leetcode
 */
public class LastStoneWeight {
	public static class Solution {
		public int lastStoneWeight(int[] ar) {
			PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
			for (int i : ar) pq.add(i);
			while (!pq.isEmpty()) {
				if (pq.size() >= 2) {
					int a = pq.poll();
					int b = pq.poll();
					if (a != b) pq.add(Math.abs(a - b));
				}
				else {
					return pq.poll();
				}
			}
			return 0;
		}
	}
}