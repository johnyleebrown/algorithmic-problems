package ds.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 146
 *
 * ======
 *
 * Task.
 *
 * Design and implement a data structure for Least Recently Used (LRU) cache. It
 * should support the following operations: get and put.
 *
 * get(key) - Get the value (will always be positive) of the key if the key
 * exists in the cache, otherwise return -1. put(key, value) - Set or insert the
 * value if the key is not already present. When the cache reached its capacity,
 * it should invalidate the least recently used item before inserting a new
 * item.
 *
 * The cache is initialized with a positive capacity.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LRUCache
{
	/**
	 * Least used items will be in the end of list because we will always move
	 * to the front the ones that we use. Map<Key, Node> + double linked list,
	 * the only reason for double is updating front node.
	 *
	 * Important case: move to front when we get or update entry. Move to front
	 * only if n of nodes > 1 and it is not front.
	 */
	public static class Solution
	{
		class LRUCacheSolution
		{
			private Map<Integer, Node> m;
			private int max, n;
			private Node back, front;

			public LRUCacheSolution(int cap)
			{
				m = new HashMap<>();
				max = cap;
			}

			public int get(int key)
			{
				if (!m.containsKey(key))
				{
					return -1;
				}

				Node x = m.get(key);
				moveNodeToFront(x);
				return x.val;
			}

			private void moveNodeToFront(Node x)
			{
				if (n != 1 && x != front)
				{
					if (back == x)
					{
						back = x.next;
					}
					if (x.prev != null)
					{
						x.prev.next = x.next;
					}

					x.next.prev = x.prev;
					x.prev = x.next = null;

					x.prev = front;
					front.next = x;
					front = x;
				}
			}

			public void put(int key, int value)
			{
				if (m.containsKey(key))
				{
					m.get(key).val = value;
					moveNodeToFront(m.get(key));
				}
				else
				{
					if (n == max)
					{
						removeLast();
					}

					Node x = new Node();
					x.key = key;
					x.val = value;

					m.put(key, x);
					if (front == null)
					{
						front = x;
						back = front;
					}
					else
					{
						x.prev = front;
						front.next = x;
						front = x;
					}

					n++;
				}
			}

			private void removeLast()
			{
				Node next = back.next;
				m.remove(back.key);
				if (next == null)
				{
					back = null;
					front = null;
				}
				else
				{
					next.prev = null;
					back = next;
				}
				n--;
			}

			private class Node
			{
				Node next, prev;
				int key, val;
			}
		}
	}
}
