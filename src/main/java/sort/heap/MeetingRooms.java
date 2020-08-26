package sort.heap;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * 252
 */
public class MeetingRooms {
	class Solution {
		public boolean canAttendMeetings(int[][] a) {
			if (a.length == 0)
				return true;
			PriorityQueue<int[]> pq = new PriorityQueue<>((a1, b1) -> a1[0] - b1[0]);
			Collections.addAll(pq, a);
			int[] prev = pq.poll();
			while (!pq.isEmpty()) {
				int[] cur = pq.poll();
				if (prev[1] > cur[0])
					return false;
				prev = cur;
			}
			return true;
		}
	}
}