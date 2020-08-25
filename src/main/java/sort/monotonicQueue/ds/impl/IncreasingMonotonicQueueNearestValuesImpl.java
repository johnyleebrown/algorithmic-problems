package sort.monotonicQueue.ds.impl;

import sort.monotonicQueue.ds.IncreasingMonotonicQueueNearestValues;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class IncreasingMonotonicQueueNearestValuesImpl implements IncreasingMonotonicQueueNearestValues
{
	private Deque<ItemWithIndex<Integer>> q;

	// could do array
	private List<Integer> nearestValues;
	private int defaultNearestValue;

	public IncreasingMonotonicQueueNearestValuesImpl(int defaultNearestValue)
	{
		this.q = new ArrayDeque<>();

		this.nearestValues = new ArrayList<>();
		this.defaultNearestValue = defaultNearestValue;
	}

	@Override
	public int getNearestValueLessThanAtIndex(int index)
	{
		return nearestValues.get(index);
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
			nearestValues.add(q.peekLast().index);
		}
		else
		{
			nearestValues.add(defaultNearestValue);
		}

		q.addLast(new ItemWithIndex<>(newPairValue, index));
	}
}
