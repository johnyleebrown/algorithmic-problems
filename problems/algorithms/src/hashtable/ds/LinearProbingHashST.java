package hashtable.ds;

import java.util.Arrays;

public class LinearProbingHashST<Key, Value>
{
	private static final int INIT_CAPACITY = 4;

	private int n;
	private int m;
	private Value[] vals;
	private Key[] keys;

	public LinearProbingHashST()
	{
		this(INIT_CAPACITY);
	}

	public LinearProbingHashST(int m)
	{
		this.m = m;
		keys = (Key[]) new Object[m];
		vals = (Value[]) new Object[m];
	}

	public void put(Key key, Value val)
	{
		if (n >= m / 2)
			resize(2*m);

		int i;
		for (i = hash(key); keys[i] != null; i = (i + 1) % m) {
			if (keys[i].equals(key)) {
				vals[i] = val;
				return;
			}
		}

		keys[i] = key;
		vals[i] = val;
		n++;
	}

	private void resize(int newCapacity)
	{
		LinearProbingHashST<Key, Value> temp = new LinearProbingHashST<>(newCapacity);
		for (int i = 0; i < m; i++) {
			if (keys[i] != null) {
				temp.put(keys[i], vals[i]);
			}
		}
		keys = temp.keys;
		vals = temp.vals;
		m    = temp.m;
	}

	public Value get(Key key)
	{
		for (int i = hash(key); keys[i] != null; i = (i + 1) % m)
			if (keys[i].equals(key))
				return vals[i];
		return null;
	}

	private int hash(Key key)
	{
		return (key.hashCode() & 0x7fffffff) % m;
	}

	public void print()
	{
		for (int i = 0; i < m; i++)
		{
			System.out.println(keys[i] + " " + vals[i]);
		}
	}
}
