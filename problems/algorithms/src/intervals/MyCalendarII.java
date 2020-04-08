package intervals;

import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 731
 */
public class MyCalendarII {
	/**
	 * The idea of this method is to scan all the intervals from left to right
	 * and determine how many intersections there are at the point in time. If
	 * it is > 2 then we can not make a booking an we need to return false.
	 */
	public static class Solution {
		private TreeMap<Integer, Integer> map = new TreeMap<>();

		public boolean book(int start, int end) {
			map.put(start, map.getOrDefault(start, 0) + 1);
			map.put(end, map.getOrDefault(end, 0) - 1);

			int intersections = 0;
			for (int v : map.values()) {
				intersections += v;
				if (intersections > 2) {
					//map.put(start, map.getOrDefault(start, 0) + 1) is done at the start
					// you want to remove it if you cant make a booking
					if (map.get(start) == 1) {
						map.remove(start);
					}
					else {
						map.put(start, map.get(start) - 1);
					}

					if (map.get(end) == -1) {
						map.remove(end);
					}
					else {
						map.put(end, map.get(end) + 1);
					}

					return false;
				}
			}

			return true;
		}
	}


	/**
	 * Keep all intervals. If we have an overlap with current interval, we check
	 * if we have another interval in map that might intersect that overlap.
	 */
	public static class Solution2 {
		PriorityQueue<int[]> calendar;

		public Solution2() {
			calendar = new PriorityQueue<>((a, b) -> (a[0] - b[0]));
		}

		public boolean book(int start, int end) {
			TreeMap<Integer, Integer> overlaps = new TreeMap<>();

			for (int[] interval : calendar) {
				int[] overlap = getOverlap(interval, start, end);
				if (overlap[0] >= 0) {
					if (hasOverlap(overlaps, overlap[0], overlap[1])) {
						return false;
					}
					overlaps.put(overlap[0], overlap[1]);
				}
			}

			calendar.add(new int[]{start, end});
			return true;
		}

		private int[] getOverlap(int[] interval, int start, int end) {
			int[] res = new int[]{-1, -1};
			if (start >= interval[1] || end <= interval[0]) {
				return res;
			}
			res[0] = Math.max(start, interval[0]);
			res[1] = Math.min(end, interval[1]);
			return res;
		}

		private boolean hasOverlap(TreeMap<Integer, Integer> overlaps, int start, int end) {
			Integer floorKey = overlaps.floorKey(start);
			if (floorKey != null && overlaps.get(floorKey) > start) {
				return true;
			}
			Integer ceilingKey = overlaps.ceilingKey(start);
			return ceilingKey != null && ceilingKey < end;
		}
	}
}