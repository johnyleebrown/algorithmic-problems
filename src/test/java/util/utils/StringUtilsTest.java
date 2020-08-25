package util.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class StringUtilsTest {
    @Test
    void testStringTo2dArray() {
        String s = "[97,100],[51,65]";
        assertArrayEquals(new int[][]{{97, 100}, {51, 65}}, StringUtils.stringToAr(s));
    }
}