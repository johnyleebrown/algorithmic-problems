package sort._ds;

import java.util.Arrays;

/**
 * Max pq.
 */
public class PriorityQueue {
	private static final int INIT_CAP = 11;
	Integer[] q;
	int n;

	private PriorityQueue() {
		q = new Integer[INIT_CAP];
		n = 0;
	}

	/**
	 * Adding to the end and then swimming.
	 */
	public void add(int i) {
		if (n == q.length - 1) {
			resize(q.length * 2);
		}

		q[++n] = i;
		swim(n);
	}

	private void swim(int i) {
		while (i > 1 && less(i / 2, i)) {
			exch(i / 2, i);
			i = i / 2;
		}
	}

	private void resize(int cap) {
		Integer[] newq = new Integer[cap];
		for (int i = 1; i <= n; i++) {
			newq[i] = q[i];
		}
		q = newq;
	}

	/**
	 * Exch top with the last, remove last, sink first.
	 */
	public Integer poll() {
		if (n == 0) {
			throw new RuntimeException("Queue is empty");
		}

		Integer ret = q[1];
		exch(1, n--);
		sink(1);
		q[n + 1] = null;
		return ret;
	}

	public Integer peek() {
		if (n == 0) {
			throw new RuntimeException("Queue is empty");
		}

		return q[1];
	}

	/**
	 * Comparing j and j + 1 cuz we want a bigger element going to the top.
	 */
	private void sink(int i) {
		while (2 * i <= n) {
			int j = 2 * i;
			if (j < n && less(j, j + 1)) j++;
			if (!less(i, j)) break;
			exch(i, j);
			i = j;
		}
	}

	private void exch(int i, int j) {
		int t = q[i];
		q[i] = q[j];
		q[j] = t;
	}

	private boolean less(int i, int j) {
		return q[i] != null && q[j] != null && q[i] < q[j];
	}

	@Override
	public String toString() {
		return "PriorityQueue{" +
				"q=" + Arrays.toString(q) +
				", n=" + n +
				'}';
	}

	public static void main(String[] args) {
		PriorityQueue pq = new PriorityQueue();
		pq.add(1);
		pq.add(-1);
	}
}
