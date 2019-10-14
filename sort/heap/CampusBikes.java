/*
 * 1057
 * Google
 */
class Solution
{
	public int[] assignBikes(int[][] workers, int[][] bikes)
	{
		PriorityQueue<BikeWorkerPair> pq = new PriorityQueue<>((a, b) -> {
			if (a.dist == b.dist)
			{
				if (a.worker.ind == b.worker.ind)
				{
					return a.bike.ind - b.bike.ind;
				}

				return a.worker.ind - b.worker.ind;
			}

			return a.dist - b.dist;
		});

		for (int i = 0; i < workers.length; i++)
		{
			for (int j = 0; j < bikes.length; j++)
			{
				Worker w = new Worker(workers[i][1], workers[i][0], i);
				Bike b = new Bike(bikes[j][1], bikes[j][0], j);

				pq.add(new BikeWorkerPair(b, w));
			}
		}

		int[] ans = new int[workers.length];
		boolean[] seen = new boolean[workers.length];
		boolean[] seenBikes = new boolean[bikes.length];

		int c = 0;
		while (!pq.isEmpty() && c < workers.length)
		{
			BikeWorkerPair bwp = pq.poll();

			int workerInd = bwp.worker.ind;

			if (seen[workerInd])
			{
				continue;
			}
			if (seenBikes[bwp.bike.ind])
			{
				continue;
			}

			seen[workerInd] = true;
			seenBikes[bwp.bike.ind] = true;
			ans[workerInd] = bwp.bike.ind;
			c++;
		}

		return ans;
	}

	private int countD(Bike b, Worker w)
	{
		return Math.abs(w.x - b.x) + Math.abs(w.y - b.y);
	}

	class BikeWorkerPair
	{
		Bike bike;
		Worker worker;
		int dist;
		BikeWorkerPair(Bike b, Worker w)
		{
			bike = b; worker = w;
			dist = countD(b, w);
		}
	}

	class Obj
	{
		int x;
		int y;
		int ind;
		Obj(int x, int y, int ind)
		{
			this.x = x;
			this.y = y;
			this.ind = ind;
		}
	}

	class Bike extends Obj
	{
		Bike(int x, int y, int ind)
		{
			super(x, y, ind);
		}
	}

	class Worker extends Obj
	{
		Worker(int x, int y, int ind)
		{
			super(x, y, ind);
		}
	}
}

