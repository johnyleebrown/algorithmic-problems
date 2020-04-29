package string._ds;

import java.math.BigInteger;
import java.util.Random;

/**
 * RabinKarp
 *
 * - search for text in a string
 * - Las Vegas version double checks the result, so slower then Monte-Carlo but
 * the last one can make a mistake with p=1/Q.
 * - good for multiple patterns
 *
 * https://www.youtube.com/watch?v=BsxPFYP4afE&list=PLRdD1c6QbAqJn0606RlOR6T3yUqFWKwmX&index=97
 */
public class RabinKarp {
    private String pattern;
    private long patHash; // pattern hash
    private int m;
    private int R; // radix, supposed to be random if q is prime
    private long Q; // large prime for mod
    private long RMQ;

    public RabinKarp(String pattern) {
        this.pattern = pattern;
        m = pattern.length();
        R = 256;
        Q = getRandPrime();

        // compute pattern hash
        patHash = hash(pattern, m);

        // precompute (R^(m-1))%Q
        RMQ = 1;
        for (int i = 1; i < m; i++) {
            RMQ = (RMQ * R) % Q;
        }
    }

    private long getRandPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }

    private long hash(String s, int m) {
        long h = 0;
        for (int i = 0; i < m; i++) {
            h = (h * R + s.charAt(i)) % Q;
        }
        return h;
    }

    /**
     * Returns the start of the pattern in text.
     */
    public int search(String text) {
        int n = text.length();
        if (n < m) {
            throw new IllegalArgumentException("Text size is too small to search current pattern in.");
        }

        // compute the window size substring hash
        long rollingHash = hash(text, m); // rolling hash

        // base case
        if (isMatch(rollingHash, text, 0)) return 0;

        for (int i = m; i < n; i++) {
            // slide
            rollingHash = removeFirst(rollingHash, text, i);
            rollingHash = addChar(rollingHash, text, i);

            //check for match
            int offset = i - m + 1;
            if (isMatch(rollingHash, text, offset)) return offset;
        }

        return -1;
    }

    private boolean isMatch(long rollingHash, String text, int offset) {
        return rollingHash == patHash && lasVegasCheck(text, offset);
    }

    /**
     * = curHash - firstChar*RMQ%Q + Q (java mod)
     */
    private long removeFirst(long rollingHash, String text, int i) {
        return (rollingHash + Q - RMQ * text.charAt(i - m) % Q) % Q;
    }

    /**
     * = curHash*R + charAt(i)
     */
    private long addChar(long rollingHash, String text, int i) {
        return (rollingHash * R + text.charAt(i)) % Q;
    }

    private boolean lasVegasCheck(String text, int j) {
        for (int i = 0; i < m; i++) {
            if (pattern.charAt(i) != text.charAt(j + i)) {
                return false;
            }
        }
        return true;
    }
}