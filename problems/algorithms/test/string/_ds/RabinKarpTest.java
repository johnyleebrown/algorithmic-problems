package string._ds;

import org.junit.jupiter.api.Test;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

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

    @Test
    void name() throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        String s = "#123#";
        byte[] hash = digest.digest(s.getBytes(StandardCharsets.UTF_8));
        System.out.println(Arrays.toString(hash));
    }

    public String s(byte[] s) {
        return Base64.getEncoder().encodeToString(s);
    }
}