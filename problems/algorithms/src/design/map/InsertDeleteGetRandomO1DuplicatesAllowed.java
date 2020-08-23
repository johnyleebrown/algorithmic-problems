package design.map;

import java.util.*;

/**
 * 381
 *
 * ======
 *
 * Task.
 *
 * Design a data structure that supports all following operations in average O(1) time.
 *
 * Note: Duplicate elements are allowed.
 * insert(val): Inserts an item val to the collection.
 * remove(val): Removes an item val from the collection if present.
 * getRandom: Returns a random element from current collection of elements. The probability of each
 * element being returned is linearly related to the number of same value the collection contains.
 *
 * ======
 *
 * Source: Leetcode
 */
public class InsertDeleteGetRandomO1DuplicatesAllowed {
	/**
	 * Kepp a list of positions for each val instead of just one.
	 */
	public static class Solution {
		public static class RandomizedCollection {

			Map<Integer, Set<Integer>> m; // lists of positions
			List<Integer> l; // all elements
			Random r;

			public RandomizedCollection() {
				m = new HashMap<>();
				l = new ArrayList<>();
				r = new Random();
			}

			public boolean insert(int val) {
				boolean contains = m.containsKey(val);
				if (!contains) {
					m.put(val, new HashSet<>());
				}

				m.get(val).add(l.size());
				l.add(val);

				return !contains;
			}

			public boolean remove(int curVal) {
				if (!m.containsKey(curVal)) {
					return false;
				}

				int lastValPos = l.size() - 1;
				int lastVal = l.get(lastValPos);
				l.remove(lastValPos);

				int curValPos = m.get(curVal).iterator().next();
				m.get(curVal).remove(curValPos);

				if (lastValPos != curValPos) {
					m.get(lastVal).remove(lastValPos);
					l.set(curValPos, lastVal);
					m.get(lastVal).add(curValPos);
					if (m.get(curVal).size() == 0)
						m.remove(curVal);
				} else {
					if (m.get(lastVal).size() == 0)
						m.remove(lastVal);
				}

				return true;
			}

			public int getRandom() {
				return l.get(r.nextInt(l.size()));
			}
		}
	}
}