package array.subarraySum;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SubarraySumEqualsKTest {
    @Test
    void test1() {
        SubarraySumEqualsK.Solution s = new SubarraySumEqualsK.Solution();
        assertEquals(4, s.subarraySum(new int[]{0, 5, 0}, 5));
    }
}