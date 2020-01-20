package queue.monotonicQueue.ds;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecreasingMonotonicQueue implements MonotonicQueue
{
	private Deque<Pair<Integer>> q;

	public DecreasingMonotonicQueue()
	{
		q = new ArrayDeque<>();
	}

	public void push(int newPairValue)
	{
		System.out.println("<========" );
		System.out.println("inserting " + newPairValue);

		// number of items smaller then #newPairValue removed from queue
		int removedFromQueueCount = 0;

		while (!q.isEmpty() && q.peekLast().value < newPairValue)
		{
			System.out.println("last val is " + q.peekLast().value + " count is " + q.peekLast().removedFromQueueCount);

			// number of elements removed before q.peekLast() and itself(count 1)
			removedFromQueueCount += q.peekLast().removedFromQueueCount + 1;
			q.removeLast();
		}

		System.out.println("total new count is " + removedFromQueueCount);
		q.addLast(new Pair<>(newPairValue, removedFromQueueCount));
	}

	@Override
	public int getFirst()
	{
		return q.getFirst().value;
	}

	public int getMax()
	{
		return getFirst();
	}

	public void removeFirst()
	{
		System.out.println("wish to remove " + q.peekFirst().value + " with count " + q.peekFirst().removedFromQueueCount);

		if (q.getFirst().removedFromQueueCount > 0)
		{
			q.getFirst().removedFromQueueCount--;
		}
		else
		{
			q.removeFirst();
		}
	}

	private class Pair<T>
	{
		T value;

		/**
		 * Count of numbers removed from queue that were smaller then the #value.
		 */
		T removedFromQueueCount;

		private Pair(T value, T removedFromQueueCount)
		{
			this.value = value;
			this.removedFromQueueCount = removedFromQueueCount;
		}
	}
}