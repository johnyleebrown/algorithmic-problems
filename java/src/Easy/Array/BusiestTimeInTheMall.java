package Easy.Array;

/**
 * Pramp
 * To find the busiest time, we need to figure out how many people
 * were at the mall after each entry/exit. However, we are given
 * the changes (the “deltas”) in the number of people, and not this
 * number. We can build this data by adding/subtracting each
 * entry/exist by ascending time order.
 */
public class BusiestTimeInTheMall {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int solution(int[][] data) {
        int maxTotal = Integer.MIN_VALUE;
        int currentTotal = 0;
        int maxTS = 0;

        for (int i = 0 ; i < data.length ; i++) {
            if (data[i][2] == 1)
                currentTotal += data[i][1];
            else
                currentTotal -= data[i][1];

            if (i + 1 < data.length && data[i + 1][0] == data[i][0])
                continue;

            if (currentTotal > maxTotal) {
                maxTotal = currentTotal;
                maxTS = data[i][0];
            }

        }
        return maxTS;
    }
}
