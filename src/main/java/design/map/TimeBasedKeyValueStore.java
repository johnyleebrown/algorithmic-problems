package design.map;

import java.util.*;

/**
 * 981
 */
public class TimeBasedKeyValueStore {
	/**
	 * Binary search solution.
	 */
	public static class Solution {
		public static class TimeMap {
			static Map<String, List<Crab>> m;

			public TimeMap() {
				m = new HashMap<>();
			}

			public void set(String k, String v, int t) {
				m.putIfAbsent(k, new ArrayList<>());
				m.get(k).add(new Crab(t, v));
			}

			public String get(String k, int t) {
				if (!m.containsKey(k)) {
					return "";
				}
				int ind = bs(t, m.get(k));
				if (ind < 0) {
					return "";
				}
				return m.get(k).get(ind).v;
			}

			private int bs(int x, List<Crab> l) {
				int lo = -1, hi = l.size();
				while (hi - lo > 1) {
					int mid = lo + (hi - lo) / 2;
					if (l.get(mid).t <= x) {
						lo = mid;
					} else {
						hi = mid;
					}
				}
				return lo;
			}

			static class Crab {
				Integer t;
				String v;

				Crab(int tt, String vv) {
					t = tt;
					v = vv;
				}
			}
		}
	}

	/**
	 * TreeMap solution.
	 *
	 * Set O(n + log(m)), get O(log(m)), where n - keys and m - times
	 */
	public static class Solution2 {
		public static class TimeMap {
			static Map<String, TreeMap<Integer, String>> m;

			public TimeMap() {
				m = new HashMap<>();
			}

			public void set(String k, String v, int timestamp) {
				m.putIfAbsent(k, new TreeMap<>());
				m.get(k).put(timestamp, v);
			}

			public String get(String k, int timestamp) {
				if (!m.containsKey(k)) {
					return "";
				}
				Map.Entry<Integer, String> e = m.get(k).floorEntry(timestamp);
				if (e == null) {
					return "";
				}
				return e.getValue();
			}
		}
	}
}