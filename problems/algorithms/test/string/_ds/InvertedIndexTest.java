package string._ds;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

class InvertedIndexTest {
    @Test
    void test1() {
        String x = "abcdab";
        int[][] res = InvertedIndex.invert(x);
        for (int i = x.length() - 1; i >= 0; i--) {
            System.out.println(x.charAt(i));
            System.out.println(Arrays.toString(res[i]));
        }
    }
}