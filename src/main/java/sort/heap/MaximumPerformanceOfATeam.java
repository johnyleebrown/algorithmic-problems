package sort.heap;

import util.tester.Tester;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 1383
 *
 * ======
 *
 * Task.
 *
 * There are n engineers numbered from 1 to n and two arrays: speed and
 * efficiency, where speed[i] and efficiency[i] represent the speed and
 * efficiency for the i-th engineer respectively. Return the maximum performance
 * of a team composed of at most k engineers, since the answer can be a huge
 * number, return this modulo 10^9 + 7.
 *
 * The performance of a team is the sum of their engineers' speeds multiplied by
 * the minimum efficiency among their engineers.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaximumPerformanceOfATeam {
	/**
	 * When checking redo for indexes. Thinking here is that we need most
	 * efficient engineers, so we traverse them in decreasing order.
	 */
	public static class Solution {
		private static final int MOD = 1_000_000_007;

		public int maxPerformance(int n, int[] speed, int[] efficiency, int k) {
			List<Engineer> mostEfficientEngineers = new ArrayList<>();
			for (int i = 0; i < speed.length; i++)
				mostEfficientEngineers.add(new Engineer(speed[i], efficiency[i]));
			Collections.sort(mostEfficientEngineers, (a, b) -> b.efficiency - a.efficiency);

			PriorityQueue<Integer> mostEfficientAndFastEngineers = new PriorityQueue<>();
			long sum = 0;
			long ans = 0;

			for (Engineer engineer : mostEfficientEngineers) {
				sum += engineer.speed;

				mostEfficientAndFastEngineers.add(engineer.speed);
				if (mostEfficientAndFastEngineers.size() > k)
					sum -= mostEfficientAndFastEngineers.poll();

				// this works even if we removed the current eng from the queue
				// because it will be smaller as traverse decreasing ef
				ans = Math.max(ans, sum * engineer.efficiency);
			}

			return (int) (ans % MOD);
		}

		private class Engineer {
			private int speed;
			private int efficiency;

			public Engineer(int speed, int efficiency) {
				this.speed = speed;
				this.efficiency = efficiency;
			}
		}

		public Solution() {
		}
	}

	public static void main(String[] args) {
		new Tester(new Solution())
				.add(6, new int[]{2, 10, 3, 1, 5, 8}, new int[]{5, 4, 3, 9, 7, 2}, 2).expect(60)
				.run();
	}
}
