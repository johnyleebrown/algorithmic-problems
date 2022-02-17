package queue.monotonicQueue.ds;

public interface DecreasingMonotonicQueueNearestValues extends MonotonicQueue
{
	int getNearestValueBiggerThanAtIndex(int index);
}
