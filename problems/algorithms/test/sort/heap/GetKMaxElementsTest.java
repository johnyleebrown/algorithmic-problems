package sort.heap;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.LinkedList;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GetKMaxElementsTest {
    @Test
    void name() {
        int[] input = new int[]{0, 7, 6, 5, 4, 3, 2, 1};
        GetKMaxElements.Solution s = new GetKMaxElements.Solution();
        assertEquals(new LinkedList<>(Arrays.asList(7, 6, 5)), s.solve(input, 3));
    }
}