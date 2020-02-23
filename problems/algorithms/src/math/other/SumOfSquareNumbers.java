package math.other;

/**
 * 633
 * Given a non-negative integer c, your task is to decide whether there're two integers a and b such that a^2 + b^2 = c.
 */
public class SumOfSquareNumbers {

    // O(sqrt(c)), O(1)
    public boolean judgeSquareSum(int c) {
        if (c < 0) return false;
        int end = (int) Math.sqrt(c);
        int start = 0;
        while (start <= end) {
            int result = start * start + end * end;
            if (result < c) start++;
            else if (result > c) end--;
            else return true;
        }
        return false;
    }

    // O(sqrt(c)* log(c)), O(1)
    public boolean judgeSquareSum2(int c) {
        for (long a = 0; a * a <= c; a++) {
            double b = Math.sqrt(c - a * a);
            if (b == (int) b) return true;
        }
        return false;
    }
}
