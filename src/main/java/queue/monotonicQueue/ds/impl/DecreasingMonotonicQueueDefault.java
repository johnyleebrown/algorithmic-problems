package queue.monotonicQueue.ds.impl;

import queue.monotonicQueue.ds.DecreasingMonotonicQueue;

import java.util.ArrayDeque;
import java.util.Deque;

public class DecreasingMonotonicQueueDefault implements DecreasingMonotonicQueue
{
	private Deque<ItemWithCount<Integer>> q = new ArrayDeque<>();

	public void push(int newPairValue, int index)
	{
		// number of items smaller then #newPairValue removed from queue
		int removedFromQueueCount = 0;

		while (!q.isEmpty() && newPairValue > q.peekLast().value)
		{
			// number of elements removed before q.peekLast() and itself(count 1)
			removedFromQueueCount += q.peekLast().removedFromQueueCount + 1;
			q.removeLast();
		}

		q.addLast(new ItemWithCount<>(newPairValue, removedFromQueueCount));
	}

	@Override
	public void removeFirst()
	{
		if (q.getFirst().removedFromQueueCount > 0)
		{
			q.getFirst().removedFromQueueCount--;
		}
		else
		{
			q.removeFirst();
		}
	}

	@Override
	public int getMax()
	{
		return q.getFirst().value;
	}
}