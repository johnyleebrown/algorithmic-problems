package Easy.DP;

/**
 * 256
 */
public class PaintHouse {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(int[][] costs) {
        int[] idx = new int[costs.length + 1];
        idx[0] = -1;
        int[] a = new int[costs.length + 1];
        a[0] = 0;
        for (int i = 1 ; i <= costs.length ; i++) {
            int min = Integer.MAX_VALUE;
            for (int j = 0 ; j < 3 ; j++) {
                if (j == idx[i - 1]) continue;
                if (min > costs[i - 1][j]) {
                    min = costs[i - 1][j];
                    idx[i] = j;
                }
            }
            a[i] = a[i - 1] + min;
        }
        return a[costs.length];
    }
}
