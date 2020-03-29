package string._ds;

/**
 * This implementation uses a version of the Knuth-Morris-Pratt substring search
 * algorithm.
 *
 * http://e-maxx.ru/algo/prefix_function
 * https://habr.com/en/post/191454/
 * https://algoprog.ru/material/25514
 */
public class KMP
{
	private final int R;       // the radix
	private int[][] dfa;       // the KMP automoton

	private char[] pattern;    // either the character array for the pattern
	private String pat;        // or the pattern string

	/**
	 * Preprocesses the pattern string.
	 */
	public KMP(String pattern)
	{
		this.R = 256;
		this.pat = pattern;

		// build DFA from pattern
		int m = pattern.length();
		dfa = new int[R][m];
		dfa[pattern.charAt(0)][0] = 1;
		for (int x = 0, j = 1; j < m; j++)
		{
			for (int c = 0; c < R; c++)
				dfa[c][j] = dfa[c][x];     // Copy mismatch cases.
			dfa[pattern.charAt(j)][j] = j + 1;   // Set match case.
			x = dfa[pattern.charAt(j)][x];     // Update restart state.
		}
	}

	/**
	 * Returns the index of the first occurrrence of the pattern string in the
	 * text string.
	 *
	 * Simulate operation of DFA on text.
	 */
	public int search(String text)
	{
		int m = pat.length();
		int n = text.length();
		int i;
		int j;

		for (i = 0, j = 0; i < n && j < m; i++)
			j = dfa[text.charAt(i)][j];

		// found
		if (j == m)
			return i - m;

		// not found
		return n;
	}
}
