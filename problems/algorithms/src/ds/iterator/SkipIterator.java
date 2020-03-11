package ds.iterator;

import java.util.*;

import static util.utility.Out.sout;

/**
 * Google_Interview_9
 */
public class SkipIterator implements Iterator<Integer>
{
	// map to count collection integers
	private Map<Integer, Integer> skipItems;
	private Iterator<Integer> it;
	private Integer nextElement;

	public SkipIterator(Iterator<Integer> it)
	{
		skipItems = new HashMap<>();
		this.it = it;

		prefill();
	}

	public boolean hasNext()
	{
		return nextElement != null;
	}

	public Integer next()
	{
		if (!hasNext()) throw new RuntimeException();

		Integer element = nextElement;
		prefill();

		return element;
	}

	public void skip(int x)
	{
		if (!hasNext()) throw new RuntimeException();

		// since we are prefilling next element
		// we need to check if our prepared element is not the next next
		if (nextElement == x)
		{
			prefill();
		}
		else
		{
			skipItems.put(x, skipItems.getOrDefault(x, 0) + 1);
		}
	}

	private void prefill()
	{
		nextElement = null;
		while (nextElement == null && it.hasNext())
		{
			Integer element = it.next();
			if (skipItems.containsKey(element) && skipItems.get(element) != 0)
			{
				skipItems.put(element, skipItems.get(element) - 1);	
			}
			else
			{
				nextElement = element;
			}
		}
	}

	public static void main(String[] s)
	{
		List<Integer> l = new ArrayList<>();
		l.add(2);
		l.add(3);
		l.add(5);
		l.add(6);
		l.add(5);
		l.add(7);
		l.add(5);
		l.add(-1);
		l.add(5);
		l.add(10);

		SkipIterator sk = new SkipIterator(l.iterator());
		sout(sk.hasNext());
		sout(sk.next());
		
		sk.skip(5);
		
		sout(sk.next());
		sout(sk.next());
		sout(sk.hasNext());
	}
}

