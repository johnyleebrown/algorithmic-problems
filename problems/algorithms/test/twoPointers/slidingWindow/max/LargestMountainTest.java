package twoPointers.slidingWindow.max;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LargestMountainTest {

    static LargestMountain.Solution s;

    @BeforeAll
    static void init() {
        s = new LargestMountain.Solution();
    }

    @Test
    void shouldReturnCorrectAnswer_whenHaveDuplicatingNumbers() {
        assertArrayEquals(s.solve(new int[]{5, 1, 3, 3, 3, 2}), new int[]{1, 5});
        assertArrayEquals(s.solve(new int[]{5, 1, 3, 5, 3, 3, 2}), new int[]{1, 6});
        assertArrayEquals(s.solve(new int[]{5, 1, 3, 5, 3, 3}), new int[]{2, 4});
        assertArrayEquals(s.solve(new int[]{2, 3, 3, 6, 3}), new int[]{2, 4});
    }

    @Test
    void shouldReturnCorrectAnswer_whenLeftMountainEdgeIsLargerThenRight() {
        assertArrayEquals(s.solve(new int[]{2, 2, 3, 1}), new int[]{1, 3});
    }

    @Test
    void shouldReturnCorrectAnswer_whenLeftMountainEdgeIsSmallerThenRight() {
        assertArrayEquals(s.solve(new int[]{1, 3, 60, 30}), new int[]{1, 3});
    }

    @Test
    void shouldReturnEmptyArray_whenInputIsAMonotonicSequence() {
        assertArrayEquals(s.solve(new int[]{5, 4, 3, 2, 1}), new int[0]);
        assertArrayEquals(s.solve(new int[]{3, 2, 2, 1}), new int[0]);
        assertArrayEquals(s.solve(new int[]{1, 2, 3, 4, 5}), new int[0]);
        assertArrayEquals(s.solve(new int[]{1, 2, 3, 5, 5, 5, 6}), new int[0]);
    }

    @Test
    void shouldReturnCorrectAnswer_whenHaveCrossingMountains() {
        assertArrayEquals(s.solve(new int[]{1, 100, 1, 2, 2, 1}), new int[]{2, 5});
        assertArrayEquals(s.solve(new int[]{2, 4, 5, 3, 6, 4, 7, 3}), new int[]{3, 7});
    }

    @Test
    void shouldReturnCorrectAnswer_whenHaveReversedMountain() {
        assertArrayEquals(s.solve(new int[]{5, 4, 3, 2, 1, 2, 3, 4, 5, 6, 5}), new int[]{8, 10});
        assertArrayEquals(s.solve(new int[]{3, 2, 1, 3}), new int[0]);
    }

    @Test
    void smallCases() {
        assertArrayEquals(s.solve(new int[]{1, 3, 2}), new int[]{0, 2});
        assertArrayEquals(s.solve(new int[]{1, 3, 1}), new int[]{0, 2});
        assertArrayEquals(s.solve(new int[]{2, 3, 1}), new int[]{0, 2});
        assertArrayEquals(s.solve(new int[]{2, 1, 1}), new int[0]);
        assertArrayEquals(s.solve(new int[]{1, 2, 2}), new int[0]);
        assertArrayEquals(s.solve(new int[]{1, 2, 3}), new int[0]);
        assertArrayEquals(s.solve(new int[]{3, 2, 1}), new int[0]);
        assertArrayEquals(s.solve(new int[]{3, 2, 1, 1, 1}), new int[0]);
        assertArrayEquals(s.solve(new int[]{2, 2, 1}), new int[0]);
        assertArrayEquals(s.solve(new int[]{2, 2, 2}), new int[0]);
        assertArrayEquals(s.solve(new int[]{2, 2}), new int[0]);
    }

    @Test
    void randArrays() {
        assertArrayEquals(s.solve(new int[]{7, 0, 5, 2, 3, 0, 7, 1, 6, 1}), new int[]{1, 5});
        assertArrayEquals(s.solve(new int[]{0, 2, 4, 0, 2, 1, 5, 1, 6, 0}), new int[]{3, 9});
        assertArrayEquals(s.solve(new int[]{4, 4, 4, 0, 5, 2, 0, 4, 4, 1}), new int[]{3, 6});
        assertArrayEquals(s.solve(new int[]{29, 39, 11, 36, 27, 16, 6, 33, 42, 21, 22, 47, 35, 49, 0, 15, 24, 3, 2, 25, 43, 23, 45, 17, 41, 5, 37, 46, 12, 40, 14, 34, 19, 7, 38, 9, 18, 48, 13, 8, 10, 20, 28, 32, 4, 1, 30, 26, 31, 44}), new int[]{14, 45});
        assertArrayEquals(s.solve(new int[]{15, 0, 8, 4, 12, 6, 10, 16, 18, 4, 11, 7, 17, 1, 15, 3, 17, 6, 2, 13, 21, 16, 3, 1, 8, 23, 16, 14, 5, 6, 9, 20, 19, 4, 14, 19, 17, 13, 9, 23, 14, 6, 23, 13, 18, 0, 21, 12, 2, 3}), new int[]{1, 45});
    }
}