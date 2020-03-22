package string.trie.ds;

/**
 * todo: add prefix search, pattern search
 *
 * Reference: https://github.com/kevin-wayne/algs4/blob/master/src/main/java/edu/princeton/cs/algs4/TrieST.java
 */
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

		public Node[] getNext()
		{
			return next;
		}
	}

	public RWayTrie()
	{
	}

	public Object get(String key)
	{
//		return getIteratively(key);
		return getRecursively(root, key, 0);
	}

	/**
	 * @param key search word
	 */
	private Object getIteratively(String key)
	{
		if (root == null)
			return null;

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

		if (currentNode.isWordEnd())
			return currentNode.val;
		return null;
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
			if (currentNode.isWordEnd())
				return currentNode.val;
			return null;
		}

		char nextChar = key.charAt(dist);
		return getRecursively(currentNode.get(nextChar), key, dist + 1);
	}

	public void put(String key, Object val)
	{
		root = putRecursively(root, 0, key, val);
//		return putIteratively(key, val);
	}

	private void putIteratively(String key, Object val)
	{
		if (root == null)
			root = new Node();

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

	private Node putRecursively(Node currentNode, int count, String key, Object val)
	{
		if (currentNode == null)
			currentNode = new Node();
		if (count == key.length())
		{
			currentNode.val = val;
			currentNode.isEnd = true;
			return currentNode;
		}
		char c = key.charAt(count);
		currentNode.getNext()[c] = putRecursively(currentNode.getNext()[c], count + 1, key, val);
		return currentNode;
	}

	public String longestPrefixOf(String query) {
		if (query == null) throw new IllegalArgumentException("argument to longestPrefixOf() is null");
		int length = longestPrefixOf(root, query, 0, -1);
		if (length == -1) return null;
		return query.substring(0, length);
	}

	// returns the length of the longest string key in the subtrie
	// rooted at x that is a prefix of the query string,
	// assuming the first d character match and we have already
	// found a prefix match of length length
	private int longestPrefixOf(Node x, String query, int d, int length) {
		if (x == null) return length;
		if (x.isEnd) length = d;
		if (d == query.length()) return length;
		char c = query.charAt(d);
		return longestPrefixOf(x.next[c], query, d+1, length);
	}

	public static void main(String[] args)
	{
		RWayTrie t = new RWayTrie();
		t.put("banana", 1);
		t.put("bananas", 2);
		t.put("banan", 3);
		System.out.println(t.get("banan"));
		System.out.println(t.get("bananz"));
		System.out.println(t.get("b"));
	}
}
