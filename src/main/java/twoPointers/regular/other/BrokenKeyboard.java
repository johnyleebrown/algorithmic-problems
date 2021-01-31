package twoPointers.regular.other;

import reader.InputReader;

import java.io.PrintWriter;

/**
 * 1251 A
 *
 * ======
 *
 * Task.
 *
 * Recently Polycarp noticed that some of the buttons of his keyboard are malfunctioning. For
 * simplicity, we assume that
 * Polycarp's keyboard contains 26 buttons (one for each letter of the Latin alphabet). Each button
 * is either working
 * fine or malfunctioning.
 *
 * To check which buttons need replacement, Polycarp pressed some buttons in sequence, and a string
 * 𝑠 appeared on the
 * screen. When Polycarp presses a button with character 𝑐, one of the following events happened:
 *
 * if the button was working correctly, a character 𝑐 appeared at the end of the string Polycarp
 * was typing; if the
 * button was malfunctioning, two characters 𝑐 appeared at the end of the string. For example,
 * suppose the buttons
 * corresponding to characters a and c are working correctly, and the button corresponding to b is
 * malfunctioning. If
 * Polycarp presses the buttons in the order a, b, a, c, a, b, a, then the string he is typing
 * changes as follows: a →
 * abb → abba → abbac → abbaca → abbacabb → abbacabba.
 *
 * You are given a string 𝑠 which appeared on the screen after Polycarp pressed some buttons. Help
 * Polycarp to
 * determine which buttons are working correctly for sure (that is, this string could not appear on
 * the screen if any of
 * these buttons was malfunctioning).
 *
 * You may assume that the buttons don't start malfunctioning when Polycarp types the string: each
 * button either works
 * correctly throughout the whole process, or malfunctions throughout the whole process.
 *
 * ======
 *
 * Source: Codeforces
 */
public class BrokenKeyboard {
	public void solve(int testNumber, InputReader in, PrintWriter out) {
		int n = in.nextInt();

		for (int stringIndex = 0; stringIndex < n; stringIndex++) {
			String currentString = in.next();

			// edge cases
			if (currentString.isEmpty()) {
				out.println("");
				continue;
			}
			if (currentString.length() == 1) {
				out.println(currentString);
				continue;
			}

			int len = currentString.length();
			Boolean[] workingKeys = new Boolean[26];
			for (int l = 0; l < len - 1; l++) {
				// if we already marked char as good
				Boolean firstChar = workingKeys[currentString.charAt(l) - 'a'];
				if (firstChar != null && firstChar) {
					continue;
				}

				// count repeating chars
				int r = l;
				while (r < len - 1 && currentString.charAt(r) == currentString.charAt(r + 1)) {
					r++;
				}
				workingKeys[currentString.charAt(r) - 'a'] = (r - l + 1) % 2 != 0;

				// we checked r - l part, continue from r
				l = r;
			}

			// last one
			int x = currentString.charAt(len - 1) - 'a';
			if (workingKeys[x] == null || (!workingKeys[x] && currentString.charAt(len - 2) != currentString.charAt(len - 1))) {
				workingKeys[x] = true;
			}

			StringBuilder result = new StringBuilder();
			for (int i = 0; i < 26; i++) {
				if (workingKeys[i] != null && workingKeys[i]) {
					result.append((char) (i + 'a'));
				}
			}

			out.println(result.toString());
		}
	}
}
