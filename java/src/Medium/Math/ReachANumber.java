package Medium.Math;

/**
 * 754
 *
 * You are standing at position 0 on an infinite number line. There is a goal at position target.
 * On each move, you can either go left or right. During the n-th move (starting from 1), you take n steps.
 * Return the minimum number of steps required to reach the destination.
 */
public class ReachANumber {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int reachNumber(int target) {
        target = Math.abs(target);
        int step = 0;
        int sum = 0;

        // Find the smallest step that the summation from 1 to step just exceeds or equals target
        while (sum < target) {
            step++;
            sum += step;
        }

        // The goal is to get rid of the difference between sum and target to reach target.
        while ((sum - target) % 2 != 0) {
            step++;
            sum += step;
        }

        return step;
    }

    public static void main(String[] args) {
        System.out.println(reachNumber(5));
    }
}
