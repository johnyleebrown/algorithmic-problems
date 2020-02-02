package sort.monotonicQueue.ds;

/**
 * Queue with strictly increasing or decreasing values.
 */
public interface MonotonicQueue
{
	/**
	 * Method removes all values smaller then #newValue, sum up all counts of pairs removed from queue. Then adds new
	 * value and count as pair to the queue.
	 *
	 * @param newPairValue a new value to add to queue
	 */
	void push(int newPairValue, int index);
}