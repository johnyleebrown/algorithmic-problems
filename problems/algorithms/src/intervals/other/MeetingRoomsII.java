package intervals.other;

import java.util.Map;
import java.util.TreeMap;

/**
 * 253
 */
public class MeetingRoomsII {
    class Solution {
        public int minMeetingRooms(int[][] intervals) {
            Map<Integer, Integer> map = new TreeMap<>();
            for (int[] i : intervals) {
                map.put(i[0], map.getOrDefault(i[0], 0) + 1);
                map.put(i[1], map.getOrDefault(i[1], 0) - 1);
            }

            int maxRooms = 0;
            int currentRoomCount = 0;
            for (int c : map.values()) {
                maxRooms = Math.max(maxRooms, currentRoomCount += c);
            }

            return maxRooms;
        }
    }
}