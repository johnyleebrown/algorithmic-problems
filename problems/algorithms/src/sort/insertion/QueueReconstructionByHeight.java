package sort.insertion;

/**
 * 406
 *
 * ======
 *
 * Task.
 *
 * Suppose you have a random list of people standing in a queue. Each person is
 * described by a pair of integers (h, k), where h is the height of the person
 * and k is the number of people in front of this person who have a height
 * greater than or equal to h. Write an algorithm to reconstruct the queue.
 *
 * ======
 *
 * Source: Leetcode
 */
public class QueueReconstructionByHeight {
    /**
     * Regular sort then insertion sort.
     */
    public static class Solution {
        public int[][] reconstructQueue(int[][] p) {
            // [[7,0], [4,4], [7,1], [5,0], [6,1], [5,2]]
            // sort
            // [7,0] [7,1] [6,1] [5,0] [5,2] [4,4]
            Arrays.sort(p, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);

            // put el to el[1] - meaning el[1] >= elements should go before el
            // [[5,0], [7,0], [5,2], [6,1], [4,4], [7,1]]
            List<int[]> res = new ArrayList<>();
            for (int[] ar : p) {
                res.add(ar[1], ar);
            }
            return res.toArray(new int[p.length][2]);
        }
    }
}