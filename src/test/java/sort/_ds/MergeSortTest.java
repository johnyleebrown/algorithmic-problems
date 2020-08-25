package sort._ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class MergeSortTest {
    @Test
    void test1() {
        int[] inp = new int[]{-4, -2, 1, -5, -4, -4, 4, -2, 0, 4, 0, -2, 3, 1, -5, 0};
        Arrays.sort(inp);
        System.out.println(Arrays.toString(inp));
    }
}