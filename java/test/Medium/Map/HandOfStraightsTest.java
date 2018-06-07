package Medium.Map;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

class HandOfStraightsTest {
    private HandOfStraights handOfStraights;

    @Before
    public void setUp() throws Exception {
        handOfStraights = new HandOfStraights();
    }

    @Test
    void simpleTestCase1() {
        verify(true, new int[]{1,2,3,6,2,3,4,7,8}, 3);
    }

    @Test
    void simpleTestCase2() {
        verify(true, new int[]{1}, 1);
    }

    @Test
    void simpleTestCase3() {
        verify(true, new int[]{66,75,4,37,92,87,68,65,58,100,97,42,19,66,73,1,5,44,30,29,76,31,12,35,26,93,9,36,90,16,86,15,4,9,13,98,10,14,18,90,89,3,10,65,24,31,43,25,54,55,54,81,10,80,31,12,15,14,59,27,64,93,32,26,69,79,13,32,29,24,27,91,92,82,37,101,100,61,74,30,91,62,36,92,28,23,4,63,55,3,11,11,101,22,34,25,2,75,43,72}, 2);
    }

    @Test
    void simpleTestCase4() {
        verify(true, new int[]{2,1}, 2);
    }

    @Test
    void simpleTestCase5() {
        verify(false, new int[]{1,2,1,4}, 4);
    }

    private void verify(final boolean expected, final int[] a, final int b) {
        assertEquals(expected, handOfStraights.solution(a, b));
    }
}