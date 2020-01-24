package queue.monotonicQueue.ds.impl;

import queue.monotonicQueue.ds.DecreasingMonotonicQueueNearestValues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class DecreasingMonotonicQueueNearestValuesImpl implements DecreasingMonotonicQueueNearestValues
{
	private Deque<ItemWithIndex<Integer>> q;

	// could do array
	private List<Integer> nearestValues;
	private int defaultNearestValue;

	public DecreasingMonotonicQueueNearestValuesImpl(int defaultNearestValue)
	{
		this.q = new ArrayDeque<>();

		this.nearestValues = new ArrayList<>();
		this.defaultNearestValue = defaultNearestValue;
	}

	@Override
	public int getNearestValueBiggerThanAtIndex(int index)
	{
		return nearestValues.get(index);
	}

	@Override
	public void push(int newPairValue, int index)
	{
		while (!q.isEmpty() && newPairValue >= q.peekLast().value)
		{
			q.removeLast();
		}

		if (!q.isEmpty())
		{
			nearestValues.add(index, q.peekLast().index);
		}
		else
		{
			nearestValues.add(index, defaultNearestValue);
		}

		q.addLast(new ItemWithIndex<>(newPairValue, index));
	}
}
