package Medium.Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.TreeMap;


/**
 * 253
 *
 * Find the minimum number of conference rooms required
 *
 * inp: [[0,30],[5,10],[15,20]]
 * out: 2
 */
public class MeetingRoomsII {
    /**
     * Simply: find the max number of overlapping intervals
     *
     * Time complexity: O()
     * Space complexity: O()
     */
    public static int solution(Interval[] intervals) {
        if (intervals.length == 0) return 0;
        int countRooms = 1;
        Arrays.sort(intervals, getComp());

        TreeMap<Integer, Integer> mapStartTimes = new TreeMap<>();

        for (Interval i : intervals) {
            int start = i.getStart();
            int end = i.getEnd();

            boolean one = false;
            Integer ceilingKey = mapStartTimes.ceilingKey(start);
            if (ceilingKey != null) {
                Integer ceilingKeyEnd = mapStartTimes.get(ceilingKey);
                one = !(end <= ceilingKey && start < ceilingKey);
            }

            boolean two = false;
            Integer floorKey = mapStartTimes.floorKey(start);
            if (floorKey != null) {
                Integer floorKeyEnd = mapStartTimes.get(floorKey);
                two = !(start >= floorKeyEnd && end > floorKeyEnd);
            }

            mapStartTimes.put(start, end);

            if (one || two) countRooms++;
        }

        return countRooms;
    }

    private static Comparator<Interval> getComp() {
        return new Comparator<Interval>() {
            @Override
            public int compare(Interval a, Interval b) {
                return a.getStart() - b.getStart();
            }
        };
    }

    static class Interval {
        private int start;
        private int end;

        public Interval(final int start, final int end) {
            this.start = start;
            this.end = end;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }
    }

    public static void main(String[] args) {
        Interval[] a = new Interval[]{
                new Interval(0,5),
                new Interval(15,20),
                new Interval(10,30)
        };
        Interval[] b = new Interval[]{
                new Interval(0,5),
                new Interval(15,20),
                new Interval(10,11)
        };
        Interval[] c = new Interval[]{
                new Interval(0,5),
                new Interval(15,20),
                new Interval(4,10)
        };
        Interval[] d = new Interval[]{
                new Interval(-5,0),
                new Interval(5,10),
                new Interval(-12,12)
        };
        Interval[] e = new Interval[]{
                new Interval(-5,0),
                new Interval(5,10),
                new Interval(-3,8)
        };
        Interval[] f = new Interval[]{
                new Interval(-5,0),
                new Interval(5,10),
                new Interval(-20,-15)
        };
        Interval[] g = new Interval[]{
                new Interval(0,5),
                new Interval(15,20),
                new Interval(17,25)
        };
        Interval[] h = new Interval[]{
                new Interval(0,5),
                new Interval(15,20),
                new Interval(17,25)
        };
        Interval[] i = new Interval[]{
                new Interval(0,5),
                new Interval(15,20),
                new Interval(-5,3)
        };
        Interval[] j = new Interval[]{
                new Interval(0,30),
                new Interval(15,20),
                new Interval(5,10)
        };
        Interval[] j1 = new Interval[]{
                new Interval(5,10),
                new Interval(0,30),
                new Interval(15,20)
        };
        Interval[] j2 = new Interval[]{
                new Interval(5,10),
                new Interval(15,20),
                new Interval(0,30)
        };
        solution(j);
    }
}
