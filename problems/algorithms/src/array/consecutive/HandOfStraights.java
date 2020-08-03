package array.consecutive;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 846
 *
 * ======
 *
 * Task.
 *
 * Alice has a hand of cards, given as an array of integers.
 *
 * Now she wants to rearrange the cards into groups so that each group is size W, and consists of
 * W consecutive cards.
 *
 * Return true if and only if she can.
 *
 * ======
 *
 * Source: Leetcode
 */
public class HandOfStraights {
	/**
	 * 1,2,3,6,2,3,4,7,8
	 * 1 2 3 4 5 6 7 8
	 * 1 2 2 1 0 1 1 1
	 *
	 * We are trying to cut from consecutive numbers the weight of ith number.
	 * In other words - removing height of a mountain.
	 */
	public static class Solution1 {
		public boolean isNStraightHand(int[] hand, int w) {
			Map<Integer, Integer> counts = new TreeMap<>();
			for (int i : hand) {
				counts.put(i, counts.getOrDefault(i, 0) + 1);
			}
			for (int i : counts.keySet()) {
				if (counts.get(i) == 0) continue;
				int rem = counts.get(i);
				for (int j = 0; j < w; j++) {
					int x = i + j;
					if (!counts.containsKey(x) || counts.get(x) - rem < 0)
						return false;
					counts.put(x, counts.get(x) - rem);
				}
			}
			return true;
		}
	}

	/**
	 * Trying to remove up to W consecutive elements from pq.
	 */
	class Solution2 {
		public boolean isNStraightHand(int[] hand, int W) {
			PriorityQueue<Integer> pq = new PriorityQueue<>();
			for (int i : hand) {
				pq.add(i);
			}
			while (pq.size() != 0) {
				int start = pq.poll();
				for (int j = 1; j < W; j++) {
					if (!pq.remove(start + j)) {
						return false;
					}
				}
			}
			return true;
		}
	}
}