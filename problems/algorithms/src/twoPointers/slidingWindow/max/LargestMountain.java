package twoPointers.slidingWindow.max;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * LargestMountain
 *
 * ======
 *
 * Task.
 *
 * Given an array of numbers, find the largest interval which is a "mountain":
 * the two endpoints are both strictly smaller than all elements in between.
 *
 * For example:
 * [2, 4, 5, 3, 6, 4, 7, 3]
 * The interval [0, 3] is a mountain, but [3, 7] is the largest mountain. [0, 7]
 * is not a mountain because 3 == 3.
 *
 * ======
 *
 * Company : Google
 * Source: Leetcode
 */
public class LargestMountain {
    /**
     * Let's assume that the correct answer should return array of 2 numbers -
     * left and right index and that a wrong answer should return an empty
     * array.
     *
     * Check out the 132 pattern problem first, here we keep track of the
     * sequence xYx or left|mid|right where left < mid and mid > right.
     */
    public static class Solution {
        public int[] solve(int[] ar) {
            if (ar.length < 3) return new int[0];

            MonotonicQueue q = new MonotonicQueue();
            for (int i = 0; i < ar.length; i++) {
                q.push(new Element(ar[i], i));
            }

            return q.left == -1 || q.right - q.left < 2 ? new int[0] : new int[]{q.left, q.right};
        }

        private class MonotonicQueue {
            // smallest in the mountain except left and right edges
            Integer mid;
            Deque<Element> q;
            int left, right;

            public MonotonicQueue() {
                q = new ArrayDeque<>();
                left = right = -1;
            }

            public void push(Element cur) {
                while (!q.isEmpty() && q.peekLast().val >= cur.val) {
                    if (mid != null && q.peekLast().val < mid) {
                        updateMax(cur);
                    }

                    if (q.peekLast().val == cur.val) {
                        mid = null;
                    } else {
                        mid = q.peekLast().val;
                    }

                    q.removeLast();
                }

                if (!q.isEmpty() && mid != null && q.peekLast().val < mid) {
                    updateMax(cur);
                    mid = null;
                }

                q.addLast(cur);
            }

            private void updateMax(Element e) {
                if (e.ind - q.peekLast().ind > right - left) {
                    left = q.peekLast().ind;
                    right = e.ind;
                }
            }
        }

        private class Element {
            int val, ind;

            public Element(int val, int ind) {
                this.val = val;
                this.ind = ind;
            }
        }
    }
}