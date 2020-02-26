package string.trie.ds;

public class RWayTrie
{
	private static final int R = 256;
	private Node root;
	/**
	 * Nodes count.
	 */
	private int count;

	/**
	 * R-way trie node.
	 */
	private class Node
	{
		private Object val;
		private boolean isEnd;
		private Node[] next = new Node[R];

		public boolean isWordEnd()
		{
			return this.isEnd;
		}

		public void setEnd()
		{
			this.isEnd = true;
		}

		public Node get(char c)
		{
			return next[c];
		}

		public boolean contains(char c)
		{
			return get(c) != null;
		}

		private void put(char currentChar)
		{
			next[currentChar] = new Node();
		}

		private void setVal(Object val)
		{
			this.val = val;
		}
	}

	public RWayTrie()
	{
	}

	public Object get(String key)
	{
		return getIteratively(key);
	}

	/**
	 * @param key search word
	 */
	private Object getIteratively(String key)
	{
		Node currentNode = root;
		int n = key.length();

		for (int i = 0; i < n; i++)
		{
			char c = key.charAt(i);
			if (!currentNode.contains(c))
			{
				return null;
			}
			currentNode = currentNode.get(c);
		}

		return currentNode.isWordEnd();
	}

	private Object getRecursively(Node currentNode, String key, int dist)
	{
		if (currentNode == null)
		{
			return null;
		}

		// distance from start is the word length = we traversed a whole word
		if (dist == key.length())
		{
			return currentNode.val;
		}

		char nextChar = key.charAt(dist);
		return getRecursively(currentNode.get(nextChar), key, dist + 1);
	}

	public boolean put(String key, Object val)
	{
		putIteratively(key, val);
	}

	private void putIteratively(String key, Object val)
	{
		Node currentNode = root;
		int n = key.length();

		for (int i = 0; i < n; i++)
		{
			char currentChar = key.charAt(i);
			if (!currentNode.contains(currentChar))
			{
				currentNode.put(currentChar);
			}
			currentNode = currentNode.get(currentChar);
		}

		currentNode.setEnd();
		currentNode.setVal(val);
	}

	public static void main(String[] args)
	{

	}
}
