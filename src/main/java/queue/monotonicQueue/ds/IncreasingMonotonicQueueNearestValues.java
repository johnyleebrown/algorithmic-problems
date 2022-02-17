package queue.monotonicQueue.ds;

public interface IncreasingMonotonicQueueNearestValues extends MonotonicQueue
{
	int getNearestValueLessThanAtIndex(int index);
}
