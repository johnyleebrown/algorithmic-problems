package Medium.Math;

/**
 * You are standing at position 0 on an infinite number line. There is a goal at position target.
 * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
 * Return the minimum number of steps required to reach the destination.
 */
public class ReachANumber {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    public int reachNumber(int target) {
        target = Math.abs(target);
        int step = 0;
        int sum = 0;

        while (sum < target) {
            step++;
            sum += step;
        }
        while ((sum - target) % 2 != 0) {
            step++;
            sum += step;
        }
        return step;
    }
}
