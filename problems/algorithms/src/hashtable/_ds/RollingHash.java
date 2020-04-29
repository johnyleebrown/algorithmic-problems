package hashtable._ds;

import java.math.BigInteger;
import java.util.Random;

/**
 * RollingHash
 *
 * todo
 * other ops
 * precompute multipliers
 */
public class RollingHash {
    long curHash; // rolling hash
    long patHash; // pattern hash
    long R = 256; // radix, advised to be random though
    long Q; // a large number, preferably prime
    int m; // window len
    String t; // text
    String pat; // searched pattern
    long[] cache; // radix cache

    public RollingHash(String text, String pattern) {
        t = text;
        Q = getPrime();
        m = pattern.length();

        // calculate r^m-i for each i from 1 to m
        cache[0] = 1;
        for (int i = 1; i < m; i++) {
            cache[i] = (cache[i - 1] * R) % Q;
        }
    }

    public void addLast(int i) {
        curHash = curHash * R + t.charAt(i);
    }

    public void pollFirst(int i) {
        curHash = curHash - t.charAt(i - m + 1) * ((int) Math.pow(R, m - 1));
        m--;
    }

    public void slideRight() {
        //todo
    }

    private long getPrime() {
        return getNotRandPrime();
    }

    private long getNotRandPrime() {
        return 5381; // DJB Hash Function Prime
    }

    private long getRandPrime() {
        BigInteger prime = BigInteger.probablePrime(31, new Random());
        return prime.longValue();
    }
}
