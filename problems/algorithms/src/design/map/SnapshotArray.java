package design.map;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 1146
 */
public class SnapshotArray {
		public static class Solution {
				int id;
				private static Map<Integer, TreeMap<Integer, Integer>> m;
				int n;

				public Solution(int n) {
						this.n = n;
						id = 0;
						m = new HashMap<>();
						for (int i = 0; i < n; i++) {
								m.put(i, new TreeMap<>());
								m.get(i).put(0, 0);
						}
				}

				public void set(int index, int val) {
						if (index > n) {
								return;
						}
						if (m.get(index).floorEntry(id).getValue() != val) {
								m.get(index).put(id, val);
						}
				}

				public int snap() {
						return id++;
				}

				public int get(int index, int snap_id) {
						return m.get(index).floorEntry(snap_id).getValue();
				}
		}
}