package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 946
 *
 * ======
 *
 * Task.
 *
 * Given two sequences pushed and popped with distinct values, return true if
 * and only if this could have been the result of a sequence of push and pop
 * operations on an initially empty stack.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ValidateStackSequences {
    /**
     * Simulate addition to stack, check every time if we can pop at the point.
     */
    public static class Solution {
        public boolean validateStackSequences(int[] pushed, int[] popped) {
            Deque<Integer> q = new ArrayDeque<>();
            int j = 0;
            for (int i = 0; i < pushed.length; i++) {
                if (pushed[i] == popped[j]) {
                    j++;
                    while (!q.isEmpty() && q.peekLast() == popped[j]) {
                        j++;
                        q.removeLast();
                    }
                } else {
                    q.add(pushed[i]);
                }
            }
            return q.isEmpty();
        }
    }
}