package Easy.String;

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
     * Time complexity: O()
     * Space complexity: O()
     */
    public static boolean solution(String A, String B) {
        for (int s = 0; s < A.length(); s++) {
            String x = A.substring(s) + A.substring(0, s);
            if (x.equals(B)) return true;
        }
        return false;
    }
}
