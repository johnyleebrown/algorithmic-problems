package twoPointers.slidingWindow.max;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class LongestSubarrayWithAbsoluteDifferenceTest {
    @Test
    void test1() {
        LongestSubarrayWithAbsoluteDifference.Solution s = new LongestSubarrayWithAbsoluteDifference.Solution();
        int[][] inp = new int[][]{{2, 5, 10, 7, 3, 9, 0, 6, 11, 17},
                {2, 5, 8, 7, 3, 8, 0, 6, 11, 17},
                {2, 5, 8, 7, 3, 8, 0, 6, 11, 17},
                {2, 5, 8, 7, 3, 8, 0, 6, 11, 17},
                {3, 5, 8, 7, 3, 8, 0, 6, 11, 17},
                {1, 2, 4},
                {8, 3, 15, 7, 6, 4}};
        int[] inp2 = new int[]{6
                , 6
                , 5
                , 3
                , 3
                , 2
                , 2};
        int[][] ans = new int[][]{{5, 10, 7}
                , {5, 8, 7, 3, 8}
                , {5, 8, 7}
                , {8, 7}
                , {3, 5}
                , {1, 2}
                , {7, 6}};

        for (int i = 0; i < inp.length; i++) {
            assertArrayEquals(ans[i], s.solve(inp[i], inp2[i]));
        }
    }
}