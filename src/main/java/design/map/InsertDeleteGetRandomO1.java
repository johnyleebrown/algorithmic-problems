package design.map;

import java.util.*;

/**
 * 380
 */
public class InsertDeleteGetRandomO1 {
	/**
	 * Use map to keep value and index in list. Remove is O(1). Use list to rand
	 * val in O(1). Removing value from list can tale up to O(n) if it is not
	 * the last one. We remove tha last one and set it to index of val in list
	 * we want to remove.
	 */
	public static class Solution {
		public static class RandomizedSet {

			Random r;
			List<Integer> l; // vals
			Map<Integer, Integer> m; // val-pos

			public RandomizedSet() {
				r = new Random();
				l = new ArrayList<>();
				m = new HashMap<>();
			}

			public boolean insert(int val) {
				if (m.containsKey(val)) {
					return false;
				}
				m.put(val, l.size());
				l.add(val);
				return true;
			}

			public boolean remove(int val) {
				if (!m.containsKey(val)) {
					return false;
				}
				int pos = m.get(val);
				int val2 = l.get(l.size() - 1);
				m.remove(val);
				l.remove(l.size() - 1);
				if (pos != l.size()) {
					l.set(pos, val2);
					m.put(val2, pos);
				}
				return true;
			}

			public int getRandom() {
				return l.get(r.nextInt(l.size()));
			}
		}
	}
}