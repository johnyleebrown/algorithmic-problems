package array.other;

/**
 * 849
 *
 * In a row of seats, 1 represents a person sitting in that seat, and 0 represents that the seat is empty.
 *
 * There is at least one empty seat, and at least one person sitting.
 *
 * Alex wants to sit in the seat such that the distance between him and the closest person to him is maximized.
 *
 * Return that maximum distance to closest person.
 */
public class MaximizeDistanceToClosestPerson {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    class Solution {
        public int maxDistToClosest(int[] seats) {
            if (seats.length < 2) return 0;
            int lastFilledIndex = -1;
            int maxLen = 0;

            for (int i = 0 ; i < seats.length; i++) {
                if (lastFilledIndex == -1 && seats[i] == 1) maxLen = i;
                if (seats[i] == 1) {
                    maxLen = Math.max(maxLen, (i - lastFilledIndex) / 2);
                    lastFilledIndex = i;
                }
            }
            maxLen = Math.max(maxLen, seats.length - 1 - lastFilledIndex);

            return maxLen;
        }
    }
}
