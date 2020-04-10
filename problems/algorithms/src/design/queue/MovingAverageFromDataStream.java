package design.queue;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 346
 */
public class MovingAverageFromDataStream {
	private Queue<Integer> q;
	private double sum = 0;
	private int size;

	public MovingAverageFromDataStream(int s) {
		q = new LinkedList<>();
		size = s;
	}

	public double next(int val) {
		if (q.size() == size) {
			sum = sum - q.poll();
		}

		q.offer(val);
		sum += val;
		return sum / q.size();
	}
}