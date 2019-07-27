package Easy.Array;

import java.util.TreeMap;

/**
 * 729
 * Implement a MyCalendar class to store your events. A new event can be added if adding the event will not cause a double booking.
 */
public class MyCalendarI {

    class MyCalendar {
        TreeMap<Integer, Integer> calendar;

        public MyCalendar() {
            calendar = new TreeMap<>();
        }

        public boolean book(int start, int end) {
            Integer floorKey = calendar.floorKey(start);
            if (floorKey != null && calendar.get(floorKey) > start) return false;
            Integer ceilingKey = calendar.ceilingKey(start);
            if (ceilingKey != null && ceilingKey < end) return false;
            calendar.put(start, end);
            return true;
        }
    }
}
