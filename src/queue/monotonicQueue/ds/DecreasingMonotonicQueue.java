package queue.monotonicQueue.ds;

public interface DecreasingMonotonicQueue extends MonotonicQueue
{
	/**
	 * @return the first value of the queue, which is the maximum or minimum.
	 */
	int getMax();

	/**
	 * Removes min or max when it is no longer needed.
	 */
	void removeFirst();
}
