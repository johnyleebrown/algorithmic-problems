package Easy.TwoPointers;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Before;
import org.junit.jupiter.api.Test;

/**
 *
 */
class BackspaceStringCompareTest {
    private BackspaceStringCompare backspaceStringCompare;

    @Before
    public void setUp() throws Exception {
        backspaceStringCompare = new BackspaceStringCompare();
    }

    @org.junit.jupiter.api.Test
    void testCase1() {
        verify(true, "ab#c", "ad#c");
    }

    @Test
    void testCase2() {
        verify(true, "ab##", "c#d#");
    }

    @Test
    void testCase3() {
        verify(true, "a##c", "#a#c");
    }

    @Test
    void testCase4() {
        verify(false, "a#c", "b");
    }

    @Test
    void testCase5() {
        verify(true, "xywrrmp", "xywrrmu#p");
    }

    @Test
    void testCase6() {
        verify(false, "nzp#o#g", "nzp#o##g");
    }

    @Test
    void testCase7() {
        verify(false, "bxj##tw", "bxj###tw");
    }

    @Test
    void testCase8() {
        verify(true, "y#fo##f", "y#fx#o##f");
    }

    private void verify(final boolean expected, final String a, final String b) {
        assertEquals(expected, backspaceStringCompare.backspaceCompare(a, b));
    }
}