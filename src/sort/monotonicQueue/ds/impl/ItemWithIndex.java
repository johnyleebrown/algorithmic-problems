package sort.monotonicQueue.ds.impl;

public class ItemWithIndex<T> extends Item<T>
{
	T index;

	public ItemWithIndex(T value, T index)
	{
		super(value);
		this.index = index;
	}
}
