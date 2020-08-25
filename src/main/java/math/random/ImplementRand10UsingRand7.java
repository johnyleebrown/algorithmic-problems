package math.random;

/**
 * 470
 *
 * ======
 *
 * Task.
 *
 * Given a function rand7 which generates a uniform random integer in the range
 * 1 to 7, write a function rand10 which generates a uniform random integer in
 * the range 1 to 10.
 *
 * Do NOT use system's Math.random().
 *
 * ======
 *
 * Source: Leetcode
 */
public class ImplementRand10UsingRand7 {
    /**
     * We can uniformly gen a number from 1 to 49, then reduce it to 1..10
     * using rejection sampling - meaning generate in 1..49 until we have 1..40.
     * We use 40 because we can just % 10 it uniformly.
     */
    public static class Solution {
        public int rand10() {
            int ind = Integer.MAX_VALUE;
            while (ind >= 40) {
                int i = rand7();
                int j = rand7();
                ind = 7 * (i - 1) + (j - 1);
            }
            return ind % 10 + 1;
        }

        private int rand7() {
            return 0;
        }
    }
}