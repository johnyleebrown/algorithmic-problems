package Medium.Sort;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

/**
 *
 */
public class MeetingRoomsIITest {
    private MeetingRoomsII solution;
    @Before
    public void setUp() throws Exception {
        solution = new MeetingRoomsII();
    }
    @Test
    public void shouldReturnZeroIfNoBooking() throws Exception {
        verify(0);
    }
    @Test
    public void shouldReturnOneIfSeperatedBooking() throws Exception {
        verify(1, 1, 2);
        verify(1, 1, 2, 3, 4, 5, 6, 7, 8 );
        verify(1, 1, 2, 2, 4, 4, 6, 6, 8 );
    }
    @Test
    public void shouldPassGivenExample() throws Exception {
        verify(2, 0, 30, 5, 10, 15, 20);
    }
    private void verify(final int expected, final int ... times) {
        final MeetingRoomsII.Interval[] intervals = constructInterval(times);
        assertEquals(expected, solution.solution(intervals));
    }
    private MeetingRoomsII.Interval[] constructInterval(final int[] times) {
        final MeetingRoomsII.Interval[] intervals = new MeetingRoomsII.Interval[times.length / 2];
        for (int i = 0; i < times.length; i++) {
            final MeetingRoomsII.Interval thisInter = new MeetingRoomsII.Interval(times[i], times[++i]);
            intervals[i / 2] = thisInter;
        }
        return intervals;
    }
}
