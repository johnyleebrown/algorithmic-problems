package string._ds;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RabinKarpTest {
    @Test
    void test1() {
        String[][] tests = new String[][]{
                {"abacadabrabracabracadabrabrabracad", "abracadabra"},
                {"abacadabrabracabracadabrabrabracad", "rab"},
                {"abacadabrabracabracadabrabrabracad", "bcara"},
                {"abacadabrabracabracadabrabrabracad", "rabrabracad"},
                {"abacadabrabracabracadabrabrabracad", "abacad"}
        };

        for (String[] test : tests) {
            String text = test[0];
            String pattern = test[1];
            RabinKarp rk = new RabinKarp(pattern);
            assertEquals(text.indexOf(pattern), rk.search(text));
        }
    }
}