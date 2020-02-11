package sort.heap;

import sun.jvm.hotspot.utilities.Interval;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 252
 */
public class MeetingRooms {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static boolean solution(Interval[] intervals) {
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        for (int i = 0; i < intervals.length; i++)
            pq.add(new int[]{(int) intervals[i].getLowEndpoint(), (int) intervals[i].getHighEndpoint()});

        int[] prev = new int[]{-1,-1};
        for (int[] a : pq) {
            if (a[0] < prev[1]) return false;
            prev = a;
        }

        return true;
    }
}
