package graph.shortestPaths.bfs.matrix;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 1345
 *
 * ======
 *
 * Task.
 *
 * Given an array of integers arr, you are initially positioned at the first
 * index of the array.
 *
 * In one step you can jump from index i to index:
 *
 * i + 1 where: i + 1 < arr.length. i - 1 where: i - 1 >= 0. j where: arr[i] ==
 * arr[j] and i != j.
 *
 * Return the minimum number of steps to reach the last index of the array.
 *
 * Notice that you can not jump outside of the array at any time.
 *
 * ======
 *
 * Source: Leetcode
 */
public class JumpGameIV {
	/**
	 * SF.
	 */
	class Solution {
		public int minJumps(int[] a) {
			int n = a.length;
			Map<Integer, List<Integer>> m = new HashMap<>();
			for (int i = 0; i < n; i++) {
				m.putIfAbsent(a[i], new LinkedList<>());
				m.get(a[i]).add(i);
			}
			boolean[] seen = new boolean[n];
			List<Integer> q = new LinkedList<>();
			q.add(0);
			seen[0] = true;
			int res = 0;
			while (!q.isEmpty()) {
				int size = q.size();
				while (--size >= 0) {
					int i = q.remove(0);
					if (i == n - 1) {
						return res;
					}
					if (i + 1 < n && !seen[i + 1]) {
						q.add(i + 1);
						seen[i + 1] = true;
					}
					if (i - 1 >= 0 && !seen[i - 1]) {
						q.add(i - 1);
						seen[i - 1] = true;
					}
					for (int j : m.get(a[i])) {
						if (j != i && !seen[j]) {
							q.add(j);
							seen[j] = true;
						}
					}
					m.get(a[i]).clear();
				}
				res++;
			}
			return res;
		}
	}
}