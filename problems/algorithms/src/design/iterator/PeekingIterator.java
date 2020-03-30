package design.iterator;

import java.util.Iterator;

/**
 * 284
 */
public class PeekingIterator implements Iterator<Integer>
{
	private Integer next = null;
	private Iterator<Integer> iter;

	public PeekingIterator(Iterator<Integer> iterator)
	{
		iter = iterator;
		if (iter.hasNext())
		{
			next = iter.next();
		}
	}

	public Integer peek()
	{
		return next;
	}

	@Override
	public Integer next()
	{
		Integer res = next;
		next = iter.hasNext() ? iter.next() : null;
		return res;
	}

	@Override
	public boolean hasNext()
	{
		return next != null;
	}
}