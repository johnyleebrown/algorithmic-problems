package design.map;

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
public class LRUCache {
	/**
	 * Least used items will be in the end of list because we will always move
	 * to the front the ones that we use. Map<Key, Node> + double linked list,
	 * the only reason for double is updating front node.
	 *
	 * Important case: move to front when we get or update entry. Move to front
	 * only if n of nodes > 1 and it is not front.
	 *
	 * last .. first
	 */
	public static class Solution {
		public static class LRUCacheSolution {

			Map<Integer, Node> m;
			Node first, last;
			int max;

			public LRUCacheSolution(int capacity) {
				m = new HashMap<>();
				max = capacity;
			}

			public int get(int key) {
				if (m.containsKey(key)) {
					Node node = m.get(key);
					int val = node.val;
					moveToFront(node);
					return val;
				} else {
					return -1;
				}
			}

			private void moveToFront(Node node) {
				if (node == first) return;
				if (node == last) {
					last = node.next;
				}

				Node prev = node.prev;
				Node next = node.next;
				if (prev != null) prev.next = next;
				if (next != null) next.prev = prev;

				if (first != null) {
					first.next = node;
					node.prev = first;
				}
				first = node;
			}

			public void put(int key, int value) {
				if (m.containsKey(key)) {
					Node n = m.get(key);
					moveToFront(n);
					n.val = value;
				} else {
					Node n = new Node(key, value);
					m.put(key, n);
					if (m.size() == 1) {
						first = n;
						last = n;
					} else {
						moveToFront(n);
					}
				}
				if (m.size() > max) {
					removeLast();
				}
			}

			private void removeLast() {
				m.remove(last.key);
				Node next = last.next;
				last.next = null;
				last = next;
			}

			private static class Node {
				int val, key;
				Node next, prev;

				public Node(int k, int v) {
					val = v;
					key = k;
				}
			}
		}
	}
}
