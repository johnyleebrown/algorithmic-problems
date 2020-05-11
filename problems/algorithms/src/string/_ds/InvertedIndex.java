package string._ds;

import java.util.Arrays;

/**
 * InvertedIndex
 */
public class InvertedIndex {
    /**
     * Calculates II array of first occurrences of all characters after index
     * i.
     *
     * We will go from right to left, copying up-to-date information about all
     * characters, and then we update information about character at current
     * index.
     *
     * We indicate that there is no character after i with -1.
     */
    public static int[][] invert(String S) {
        char[] s = S.toCharArray();
        int n = s.length;
        int[][] pos = new int[n][26];

        // fill the last character
        Arrays.fill(pos[n - 1], -1);
        pos[n - 1][s[n - 1] - 'a'] = n - 1;

        for (int i = n - 2; i >= 0; i--) {
            pos[i] = Arrays.copyOf(pos[i + 1], 26);
            pos[i][s[i] - 'a'] = i;
        }

        return pos;
    }
}
