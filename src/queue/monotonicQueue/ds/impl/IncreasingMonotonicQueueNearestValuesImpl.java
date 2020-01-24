package queue.monotonicQueue.ds.impl;

import queue.monotonicQueue.ds.IncreasingMonotonicQueueNearestValues;

import java.util.ArrayDeque;
import java.util.Deque;

public class IncreasingMonotonicQueueNearestValuesImpl implements IncreasingMonotonicQueueNearestValues
{
	private Deque<ItemWithIndex<Integer>> q;

	private int[] nearestValues;
	private int defaultNearestValue;

	public IncreasingMonotonicQueueNearestValuesImpl(int inputArrayLength, int defaultNearestValue)
	{
		this.q = new ArrayDeque<>();

		this.nearestValues = new int[inputArrayLength];
		this.defaultNearestValue = defaultNearestValue;
	}

	@Override
	public int getNearestValueLessThanAtIndex(int index)
	{
		return nearestValues[index];
	}

	@Override
	public void push(int newPairValue, int index)
	{
		while (!q.isEmpty() && newPairValue <= q.peekLast().value)
		{
			q.removeLast();
		}

		if (!q.isEmpty())
		{
			nearestValues[index] = q.peekLast().index;
		}
		else
		{
			nearestValues[index] = defaultNearestValue;
		}

		q.addLast(new ItemWithIndex<>(newPairValue, index));
	}
}
