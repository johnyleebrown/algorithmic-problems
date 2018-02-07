package Easy.DP;

/**
 * 276
 * fence with n posts, each post can be painted with one of the k colors
 * no more than two adjacent posts have same color
 * return total num of ways
 */
public class PaintFence {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    public static int solution(int n, int k) {
        int[] temp = new int[n + 1];
        temp[0] = k;
        temp[1] = k;
        for (int i = 2; i <= n; i++)
            // 2 cases:
            // either n and n-1 are different color
            // either n and n-1 are same color
            temp[i] = temp[i - 1] * (k - 1) + temp[i - 2] * (k - 1);
        return temp[n];
    }
}
