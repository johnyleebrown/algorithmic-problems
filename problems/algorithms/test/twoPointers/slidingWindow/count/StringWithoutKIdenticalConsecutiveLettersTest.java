package twoPointers.slidingWindow.count;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StringWithoutKIdenticalConsecutiveLettersTest {
    @Test
    void test1() {
        boolean[] ans = new boolean[]{true, true, true, false, false, false, false, false};
        Object[][] input = new Object[][]{{"TFTFTF", 2}, {"TTFTTF", 2}, {"FFTFFT", 2}, {"TTT", 2}, {"FFF", 2}, {"FTTTF", 2}, {"TFFFT", 2}, {"TFFAAAAFT", 3}};
        for (int i = 0; i < input.length; i++) {
            assertEquals(ans[i], StringWithoutKIdenticalConsecutiveLetters.Solution.solve((String) input[i][0], (int) input[i][1]));
        }
    }
}