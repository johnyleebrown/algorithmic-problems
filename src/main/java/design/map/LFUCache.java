package design.map;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * 460
 */
public class LFUCache {

	/**
	 * 1 map - regular - <key,value>
	 * 2 map - key to frequency count (number of uses of this key)
	 * 3 map - freq_count to list of keys - the order is LRU order,
	 * so we push back when accessed, we don't need move_to_front
	 * because every time we move key to a new count entry
	 *
	 * Other important thing is min_freq - the key to entry we will want to
	 * evict, it changes only when we access key and it is minimum and if we add
	 * a new entry - it becomes 1.
	 */
	public static class Solution {

		Map<Integer, Integer> m; // regular
		Map<Integer, Integer> keyToCount; // key to count
		Map<Integer, LinkedHashSet<Integer>> countToKeys; // freq to keys

		int max; // max allowed count - cap
		int c; // total count
		int min; // min freq

		public Solution(int cap) {
			m = new HashMap<>();
			keyToCount = new HashMap<>();
			countToKeys = new HashMap<>();
			max = cap;
			c = 0;
			min = 0;
		}

		public int get(int k) {
			if (!m.containsKey(k)) {
				return -1;
			}

			int curC = keyToCount.get(k);
			int newC = curC + 1;
			keyToCount.put(k, newC);
			countToKeys.get(curC).remove(k);
			countToKeys.putIfAbsent(newC, new LinkedHashSet<>());
			countToKeys.get(newC).add(k);
			if (min == curC && countToKeys.get(curC).size() == 0) {
				min = newC;
			}
			return m.get(k);
		}

		public void put(int k, int v) {
			if (max <= 0) return;

			// put
			if (m.containsKey(k)) {
				m.put(k, v);
				int curC = keyToCount.get(k);
				int newC = curC + 1;
				keyToCount.put(k, newC);
				countToKeys.get(curC).remove(k);
				countToKeys.putIfAbsent(newC, new LinkedHashSet<>());
				countToKeys.get(newC).add(k);
				if (min == curC && countToKeys.get(curC).size() == 0) {
					min = newC;
				}
			} else {
				m.put(k, v);
				keyToCount.put(k, 1);
				countToKeys.putIfAbsent(1, new LinkedHashSet<>());
				countToKeys.get(1).add(k);

				c++;

				// evict
				// we need to evict only if we add new entry, cuz count++
				if (c > max) {
					// iterator starts from the beginning
					int key = countToKeys.get(min).iterator().next();
					countToKeys.get(min).remove(key);
					keyToCount.put(key, 0);
					m.remove(key);
					c--;
				}

				min = 1;
			}
		}
	}
}
