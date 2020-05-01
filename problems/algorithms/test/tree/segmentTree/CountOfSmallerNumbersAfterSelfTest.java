package tree.segmentTree;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountOfSmallerNumbersAfterSelfTest {
    @Test
    void test1() {
        assertEquals(new LinkedList<>(Arrays.asList(2, 1, 1, 0)),
                new CountOfSmallerNumbersAfterSelf.Solution().countSmaller(new int[]{5, 2, 6, 1}));
    }

    @Test
    void test2() {
        assertEquals(new LinkedList<>(Arrays.asList(0, 0, 0, 0)),
                new CountOfSmallerNumbersAfterSelf.Solution().countSmaller(new int[]{5, 5, 5, 5}));
    }

    @Test
    void test3() {
        assertEquals(new LinkedList<>(Arrays.asList(0, 0, 0, 0)),
                new CountOfSmallerNumbersAfterSelf.Solution().countSmaller(new int[]{1, 2, 3, 4}));
    }

    @Test
    void test4() {
        assertEquals(new LinkedList<>(Arrays.asList(3, 2, 1, 0)),
                new CountOfSmallerNumbersAfterSelf.Solution().countSmaller(new int[]{4, 3, 2, 1}));
    }

    @Test
    void test5() {
        assertEquals(new LinkedList<>(Arrays.asList(0, 1, 2, 1, 0)),
                new CountOfSmallerNumbersAfterSelf.Solution().countSmaller(new int[]{1, 2, 3, 2, 1}));
    }

    @Test
    void test6() {
        assertEquals(new LinkedList<>(Arrays.asList(3, 1, 0, 0, 0)),
                new CountOfSmallerNumbersAfterSelf.Solution().countSmaller(new int[]{3, 2, 1, 2, 3}));
    }

    @Test
    void test7() {
        assertEquals(new LinkedList<>(Arrays.asList(1, 0)),
                new CountOfSmallerNumbersAfterSelf.Solution().countSmaller(new int[]{-1, -2}));
    }

    @Test
    void test8() {
        assertEquals(new LinkedList<>(Arrays.asList(4, 2, 1, 7, 5, 2, 3, 4, 2, 2, 0, 0)),
                new CountOfSmallerNumbersAfterSelf.Solution().countSmaller(new int[]{-1, -3, -5, 9, 1, -1, 0, 13, -1, 2, -111, -2}));
    }
}