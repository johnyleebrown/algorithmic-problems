package sort.monotonicQueue.ds.impl;

public class ItemWithCount<T> extends Item<T>
{
	/**
	 * Count of numbers removed from queue that were smaller then the #value.
	 */
	T removedFromQueueCount;

	public ItemWithCount(T value, T removedFromQueueCount)
	{
		super(value);
		this.removedFromQueueCount = removedFromQueueCount;
	}
}
