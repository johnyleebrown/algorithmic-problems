package Medium.String;

/**
 * 848
 *
 * We have a string S of lowercase letters, and an integer array shifts.
 * Call the shift of a letter, the next letter in the alphabet, (wrapping around so that 'z' becomes 'a').
 * For example, shift('a') = 'b', shift('t') = 'u', and shift('z') = 'a'.
 * Now for each shifts[i] = x, we want to shift the first i+1 letters of S, x times.
 * Return the final string after all such shifts to S are applied.
 */
public class ShiftingLetters {
    /**
     * Time complexity: O(n)
     * Space complexity: O(n)
     */
    class Solution {
        public String shiftingLetters(String S, int[] shifts) {
            StringBuilder sb = new StringBuilder();
            int[] cumShift = new int[shifts.length];
            cumShift[shifts.length - 1] = shifts[shifts.length - 1];

            for (int i = shifts.length - 2; i >= 0; i--)
                cumShift[i] = (cumShift[i + 1] + shifts[i]) % 26;

            for (int i = 0 ; i < shifts.length; i++) {
                int x = S.charAt(i) + cumShift[i];
                sb.append((char) (((x - 'a') % 26) + 'a'));
            }

            return sb.toString();
        }
    }
}
