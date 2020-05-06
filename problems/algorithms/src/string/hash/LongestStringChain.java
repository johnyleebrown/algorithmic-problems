package string.hash;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 1048
 *
 * ======
 *
 * Task.
 *
 * Given a list of words, each word consists of English lowercase letters.
 *
 * Let's say word1 is a predecessor of word2 if and only if we can add exactly
 * one letter anywhere in word1 to make it equal to word2.  For example, "abc"
 * is a predecessor of "abac".
 *
 * A word chain is a sequence of words [word_1, word_2, ..., word_k] with k >=
 * 1, where word_1 is a predecessor of word_2, word_2 is a predecessor of
 * word_3, and so on.
 *
 * Return the longest possible length of a word chain with words chosen from the
 * given list of words.
 *
 * ======
 *
 * Source: Leetcode
 */
public class LongestStringChain {
    /**
     * We use formula to concat substrings: substringHash =
     * h(left)*R^(rightLen)+h(right).
     */
    public static class Solution3 {
        private long[] pow;
        private long R = 256, Q;

        public int longestStrChain(String[] words) {
            Arrays.sort(words, (a, b) -> a.length() - b.length());
            Map<String, Long> hashes = new HashMap<>();
            Map<Long, Integer> dp = new HashMap<>();
            preprocess(words, hashes, dp);

            int ans = 0;
            for (String word : words) {
                long currentWordHash = hashes.get(word);
                long left = 0;
                long right = currentWordHash;

                for (int i = 0; i < word.length(); i++) {
                    if (i > 0) left = addLast(left, word.charAt(i - 1));
                    right = removeFirst(right, word.length() - i, word.charAt(i));
                    long sum = sum(left, right, word.length() - i - 1);

                    if (dp.containsKey(sum)) {
                        dp.put(hashes.get(word), Math.max(dp.get(currentWordHash), dp.get(sum) + 1));
                    }
                }
                ans = Math.max(ans, dp.get(currentWordHash));
            }

            return ans;
        }

        private void preprocess(String[] words, Map<String, Long> hashes, Map<Long, Integer> dp) {
            pow = new long[words[words.length - 1].length() + 1];
            Q = getRandPrime();
            pow[0] = 1;
            for (int i = 1; i < pow.length; i++) {
                pow[i] = (pow[i - 1] * R) % Q;
            }

            for (String word : words) {
                long h = hash(word);
                dp.put(h, 1);
                hashes.put(word, h);
            }
        }

        private long hash(String s) {
            return hash(s, 0, s.length() - 1);
        }

        private long hash(String s, int l, int r) {
            long h = 0;
            for (int i = l; i <= r; i++) {
                h = (h * R + s.charAt(i)) % Q;
            }
            return h;
        }

        private long sum(long left, long right, int len) {
            return (left * pow[len] + right) % Q;
        }

        private long addLast(long h, char c) {
            return (h * R + c) % Q;
        }

        private long removeFirst(long h, int len, char c) {
            return (h + Q - (pow[len - 1] * c) % Q) % Q;
        }

        private long getRandPrime() {
            BigInteger prime = BigInteger.probablePrime(31, new Random());
            return prime.longValue();
        }
    }

}