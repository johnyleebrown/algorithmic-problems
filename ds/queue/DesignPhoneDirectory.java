/*
 * 379
 * Company: Google
 */
class PhoneDirectory
{
	private Set<Integer> used;
	private List<Integer> available;
	private int max;

	public PhoneDirectory(int maxNumbers)
	{
		used = new HashSet<>();
		available = new ArrayList<>();
		max = maxNumbers;

		for (int i = 0; i < maxNumbers; i++)
		{
			available.add(i);
		}
	}

	public int get()
	{
		if (available.size() == 0)
		{
			return -1;
		}

		Integer next = available.remove(0);
		used.add(next);
		return next;
	}

	public boolean check(int number)
	{
		if (number >= max || number < 0)
		{
			return false;
		}

		return !used.contains(number);
	}

	public void release(int number)
	{
		if (used.contains(number))
		{
			used.remove(number);
			available.add(number);
		}
	}
}

