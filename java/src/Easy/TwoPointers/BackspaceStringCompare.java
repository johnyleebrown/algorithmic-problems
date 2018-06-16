package Easy.TwoPointers;

/**
 * 844
 *
 * Given two strings S and T, return if they are equal when both are typed into empty text editors. # means a backspace character.
 */
public class BackspaceStringCompare {
    /**
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static boolean backspaceCompare(String S, String T) {
        int i = S.length(), j = T.length();
        while (i >= 0 && j >= 0) {
            i = helper(--i, S);
            j = helper(--j, T);
            if (i >= 0 && j >= 0 && S.charAt(i) != T.charAt(j)) return false;
        }
        if ((i >= 0 && j < 0) || (j >= 0 && i < 0)) return false;
        return true;
    }

    static int helper(int i, String s) {
        int i_count = 0;
        while (i_count != -1 && i >= 0) {
            while (i >= 0 && s.charAt(i) == '#') {
                i_count++;
                i--;
            }
            while (i_count > 0 && i >= 0 && s.charAt(i) != '#') {
                i_count--;
                i--;
            }
            if (i >= 0 && i_count <= 0 && s.charAt(i) == '#') continue;
            if (i_count <= 0) i_count = -1;
        }
        return i;
    }
}
