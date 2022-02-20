package intervals;

import java.util.Arrays;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

/**
 * 253
 */
public class MeetingRoomsII {

	/**
	 * Priority queue for ends of intervals. We check every time if current
	 * interval is not overlapping with peek interval. If not - we don't need to
	 * keep this interval anymore.
	 */
	public static class Solution2 {

		public int minMeetingRooms(int[][] ins) {
			if (ins.length == 0) return 0;
			Arrays.sort(ins, (a, b) -> a[0] - b[0]);
			PriorityQueue<Integer> ends = new PriorityQueue<>();
			ends.add(ins[0][1]);
			for (int i = 1; i < ins.length; i++) {
				if (ins[i][0] >= ends.peek()) {
					ends.poll();
				}
				ends.add(ins[i][1]);
			}
			return ends.size();
		}
	}

	/**
	 * We add +1 for start of interval and -1 for end of interval.
	 */
	public static class Solution1 {

		public int minMeetingRooms(int[][] intervals) {
			Map<Integer, Integer> map = new TreeMap<>();
			for (int[] i : intervals) {
				map.put(i[0], map.getOrDefault(i[0], 0) + 1);
				map.put(i[1], map.getOrDefault(i[1], 0) - 1);
			}

			int maxRooms = 0;
			int currentRoomCount = 0;
			for (int c : map.values()) {
				currentRoomCount += c;
				maxRooms = Math.max(maxRooms, currentRoomCount);
			}

			return maxRooms;
		}
	}
}