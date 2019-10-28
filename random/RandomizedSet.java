/*
 *
 */
public RandomizedSet
{
	private Random r;
	private List<Integer> l;
	private Map<Integer, Integer> m;

	public RandomizedSet()
	{
		r = new Random();
		l = new ArrayList<>();
		m = new HashMap<>();
	}

	public boolean insert(int val)
	{
		if (m.containsKey(val))
		{
			return false;
		}

		m.put(val, l.size());
		l.add(val);
		
		return true;
	}

	private boolean remove(int val)
	{
		if (!m.containsKey(val))
		{
			return false;
		}

		int valIndex = m.get(val);
		if (valIndex < l.size() - 1)
		{
			int replacementValue = l.get(l.size() - 1);
			l.set(valIndex, replacementValue);
			m.put(replacementValue, valIndex);	
		}	

		l.remove(l.size() - 1);
		m.remove(val);

		return true;
	}

	private int getRandom()
	{
		return l.get(r.nextInt(l.size()));
	}
}

