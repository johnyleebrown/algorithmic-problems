package array.prefix.subarray;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinimumSizeSubarraySumTest {
    @Test
    void test1() {
        List<MinimumSizeSubarraySum.S> solutions = Arrays.asList(new MinimumSizeSubarraySum.Solution(), new MinimumSizeSubarraySum.Solution2());

        Object[][] cases = new Object[][]{
                {8, new int[]{1, 4, 3, 5, 7, 1}},
                {4, new int[]{2, 2, 1, 1, 3, 2, 1, 1}},
                {6, new int[]{6, -2, -4, 0, 4, 2, 3, 10}},
                {10, new int[]{2, 9, -1, 7, 3, 8, -2, 6, 11, 17}},
                {0, new int[]{0, 1, -1, -2, 3}}
        };

        for (Object[] entry : cases) {
            assertEquals(solutions.get(0).solve((int) entry[0], (int[]) entry[1]),
                    solutions.get(1).solve((int) entry[0], (int[]) entry[1]));
        }
    }
}