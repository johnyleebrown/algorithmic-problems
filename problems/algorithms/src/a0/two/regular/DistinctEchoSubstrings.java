package a0.two.regular;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 * 1316
 *
 * ======
 *
 * Task.
 *
 * Return the number of distinct non-empty substrings of text that can be
 * written as the concatenation of some string
 * with itself (i.e. it can be written as a + a where a is some string).
 *
 * ======
 *
 * Source: Leetcode
 */
public class DistinctEchoSubstrings {
    /**
     * Sliding window, rolling counter.
     */
    public static class Solution {
        public int distinctEchoSubstrings(String text) {
            Set<String> set = new HashSet<>();
            int n = text.length();

            for (int len = 1; len <= n / 2; len++) {
                for (int l = 0, r = len, counter = 0; l < n - len; l++, r++) {
                    if (text.charAt(l) == text.charAt(r)) {
                        counter++;
                    } else {
                        counter = 0;
                    }

                    if (counter == len) {
                        set.add(text.substring(l - len + 1, l + 1));
                        counter--;
                    }
                }
            }

            return set.size();
        }
    }

    public static class Solution2 {
        private int R = 256;
        private long Q;
        private long[] RMQ;

        public int distinctEchoSubstrings(String text) {
            Set<Long> set = new HashSet<>();
            int n = text.length();
            preprocess(n);
            long hash = 0;

            for (int len = 1; len <= n / 2; len++) {
                hash = 0;
                for (int l = 0, r = len, counter = 0; l < n - len; l++, r++) {
                    if (text.charAt(l) == text.charAt(r)) {
                        counter++;
                        hash = addChar(l, text, hash);
                    } else {
                        counter = 0;
                        hash = 0;
                    }

                    if (counter == len) {
                        set.add(hash);
                        counter--;
                        hash = removeFirstChar(l - len + 1, text, hash, len);
                    }
                }
            }

            return set.size();
        }

        private long removeFirstChar(int i, String text, long hash, int len) {
            return (hash + Q - RMQ[len - 1] * text.charAt(i) % Q) % Q;
        }

        private void preprocess(int n) {
            Q = calculateRandomPrime();
            RMQ = calculateRMQ(n / 2);
        }

        private long[] calculateRMQ(int n) {
            long[] ans = new long[n + 1]; // n=textLen/2
            long RMQ = 1;
            for (int i = 0; i <= n; i++) {
                ans[i] = RMQ;
                RMQ = (RMQ * R) % Q;
            }
            return ans;
        }

        private long calculateRandomPrime() {
            BigInteger prime = BigInteger.probablePrime(31, new Random());
            return prime.longValue();
        }

        private long addChar(int l, String text, long hash) {
            return (hash * R + text.charAt(l)) % Q;
        }
    }

    public static class Solution3 {
        private int R = 256;
        private long Q;
        private long[] powers;
        private long[] hashes;

        public int distinctEchoSubstrings(String text) {
            Set<Long> set = new HashSet<>();
            int n = text.length();
            preprocess(n, text);

            for (int len = 1; len <= n / 2; len++) {
                for (int l = 0, r = len, counter = 0; l < n - len; l++, r++) {
                    if (text.charAt(l) == text.charAt(r)) {
                        counter++;
                    } else {
                        counter = 0;
                    }

                    if (counter == len) {
                        set.add(getHash(l - len + 1, l + 1));
                        counter--;
                    }
                }
            }

            return set.size();
        }

        private void preprocess(int n, String text) {
            Q = calculateRandomPrime();
            hashes = new long[n + 1];
            powers = new long[n + 1];
            powers[0] = 1;
            for (int i = 1; i <= n; i++) {
                hashes[i] = (hashes[i - 1] * R + text.charAt(i - 1)) % Q;
                powers[i] = (powers[i - 1] * R) % Q;
            }
        }

        private long calculateRandomPrime() {
            BigInteger prime = BigInteger.probablePrime(31, new Random());
            return prime.longValue();
        }

        private long getHash(int l, int r) {
            return (hashes[r] + Q - hashes[l] * powers[r - l] % Q) % Q;
        }
    }
}
