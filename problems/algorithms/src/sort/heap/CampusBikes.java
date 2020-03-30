package sort.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 1057
 */
public class CampusBikes {
	/**
	 * Using counting sort. For all possible distances place pairs to the array
	 * of lists. And loop through workers and innerly through bikes. This way we
	 * will always eliminate workers in ASC order and the bikes to assign will
	 * be approached in ASC order as well.
	 *
	 * O(nk), where k = 2000.
	 */
	class Solution2 {
		public int[] assignBikes(int[][] workers, int[][] bikes) {
			ArrayList<int[]>[] dist = new ArrayList[2001];

			int wlen = workers.length;
			int blen = bikes.length;

			boolean[] seenBikes = new boolean[blen];

			int[] res = new int[wlen];
			Arrays.fill(res, -1);

			for (int w = 0; w < wlen; w++) {
				for (int b = 0; b < blen; b++) {
					int d = Math.abs(workers[w][0] - bikes[b][0]) +
							Math.abs(workers[w][1] - bikes[b][1]);

					if (dist[d] == null) {
						dist[d] = new ArrayList();
					}

					dist[d].add(new int[]{b, w});
				}
			}

			int c = 0;

			for (int d = 0; d <= 2000; d++) {
				if (c == blen) {
					break;
				}

				if (dist[d] == null) {
					continue;
				}

				for (int[] pair : dist[d]) {
					if (c == blen) {
						break;
					}

					if (res[pair[1]] != -1 || seenBikes[pair[0]]) {
						continue;
					}

					res[pair[1]] = pair[0];
					seenBikes[pair[0]] = true;
					c++;
				}
			}

			return res;
		}
	}

	/**
	 * Using priority queue with the conditions from the problem statement. Then
	 * we add all possible pairs of bikes and workers. Then we fill
	 * workers.length positions.
	 *
	 * O(n*m*log(n*m) + n*log(n)).
	 */
	class Solution1 {
		public int[] assignBikes(int[][] workers, int[][] bikes) {
			PriorityQueue<BikeWorkerPair> pq = new PriorityQueue<>((a, b) ->
			{
				if (a.dist == b.dist) {
					if (a.worker.ind == b.worker.ind) {
						return a.bike.ind - b.bike.ind;
					}

					return a.worker.ind - b.worker.ind;
				}

				return a.dist - b.dist;
			});

			for (int i = 0; i < workers.length; i++) {
				for (int j = 0; j < bikes.length; j++) {
					Worker w = new Worker(workers[i][1], workers[i][0], i);
					Bike b = new Bike(bikes[j][1], bikes[j][0], j);

					pq.add(new BikeWorkerPair(b, w));
				}
			}

			int[] ans = new int[workers.length];
			boolean[] seen = new boolean[workers.length];
			boolean[] seenBikes = new boolean[bikes.length];

			int c = 0;
			while (!pq.isEmpty() && c < workers.length) {
				BikeWorkerPair bwp = pq.poll();

				int workerInd = bwp.worker.ind;

				if (seen[workerInd]) {
					continue;
				}
				if (seenBikes[bwp.bike.ind]) {
					continue;
				}

				seen[workerInd] = true;
				seenBikes[bwp.bike.ind] = true;
				ans[workerInd] = bwp.bike.ind;
				c++;
			}

			return ans;
		}

		private int countD(Bike b, Worker w) {
			return Math.abs(w.x - b.x) + Math.abs(w.y - b.y);
		}

		class BikeWorkerPair {
			Bike bike;
			Worker worker;
			int dist;

			BikeWorkerPair(Bike b, Worker w) {
				bike = b;
				worker = w;
				dist = countD(b, w);
			}
		}

		class Obj {
			int x;
			int y;
			int ind;

			Obj(int x, int y, int ind) {
				this.x = x;
				this.y = y;
				this.ind = ind;
			}
		}

		class Bike extends Obj {
			Bike(int x, int y, int ind) {
				super(x, y, ind);
			}
		}

		class Worker extends Obj {
			Worker(int x, int y, int ind) {
				super(x, y, ind);
			}
		}
	}
}