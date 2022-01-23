package string.other;

/**
 * 796
 * We are given two strings, A and B. A shift on A consists of taking
 * string A and moving the leftmost character to the rightmost position.
 * For example, if A = 'abcde', then it will be 'bcdea' after one shift
 * on A. Return True if and only if A can become B after some number of
 * shifts on A.
 */
public class RotateString {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static boolean solution(String A, String B) {
        for (int i = 0; i < A.length(); i++) {
            String x = A.substring(i) + A.substring(0, i);
            if (x.equals(B)) return true;
        }
        return false;
    }
}
