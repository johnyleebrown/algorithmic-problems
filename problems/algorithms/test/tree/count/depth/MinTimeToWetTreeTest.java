package tree.count.depth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinTimeToWetTreeTest {
    @Test
    void oneLevelTest() {
        MinTimeToWetTree.Solution s = new MinTimeToWetTree.Solution();
        int[][] input = new int[][]{{0, 1, 10}, {0, 2, 20}, {0, 3, 30}};
        assertEquals(s.solve(input, 0), 30);
    }

    @Test
    void twoLevelTest() {
        MinTimeToWetTree.Solution s = new MinTimeToWetTree.Solution();
        int[][] input = new int[][]{{0, 1, 10}, {0, 2, 20}, {0, 3, 30}, {1, 4, 90}, {2, 5, 10}, {3, 6, 60}};
        assertEquals(s.solve(input, 0), 100);
    }

    @Test
    void oneChildNodesTest() {
        MinTimeToWetTree.Solution s = new MinTimeToWetTree.Solution();
        int[][] input = new int[][]{{0, 1, 10}, {1, 2, 20}, {2, 3, 30}, {3, 4, 40}, {4, 5, 50}};
        assertEquals(s.solve(input, 0), 150);
    }

    @Test
    void zeroCostsTest() {
        MinTimeToWetTree.Solution s = new MinTimeToWetTree.Solution();
        int[][] input = new int[][]{{0, 1, 0}, {0, 2, 0}, {0, 3, 0}, {1, 4, 0}, {2, 5, 0}, {3, 6, 0},
                {6, 7, 0}, {1, 8, 0}, {1, 9, 0}, {0, 10, 0}};
        assertEquals(s.solve(input, 0), 0);
    }

    @Test
    void emptyTreeTest() {
        MinTimeToWetTree.Solution s = new MinTimeToWetTree.Solution();
        int[][] input = new int[][]{{}};
        assertEquals(s.solve(input, 0), 0);
    }
}