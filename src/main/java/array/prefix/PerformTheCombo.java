package array.prefix;

import reader.InputReader;

import java.io.PrintWriter;
import java.util.Arrays;

/**
 * 624Div3C
 *
 * ======
 *
 * Task.
 *
 * You want to perform the combo on your opponent in one popular fighting game.
 * The combo is the string 𝑠 consisting of 𝑛 lowercase Latin letters. To
 * perform the combo, you have to press all buttons in the order they appear in
 * 𝑠. I.e. if 𝑠="abca" then you have to press 'a', then 'b', 'c' and 'a'
 * again.You know that you will spend 𝑚wrong tries to perform the combo and
 * during the 𝑖-th try you will make a mistake right after 𝑝𝑖-th button
 * (1≤𝑝𝑖<𝑛) (i.e. you will press first 𝑝𝑖 buttons right and start
 * performing the combo from the beginning). It is guaranteed that during the
 * 𝑚+1-th try you press all buttons right and finally perform the combo.I.e. if
 * 𝑠="abca", 𝑚=2 and 𝑝=[1,3]then the sequence of pressed buttons will be 'a'
 * (here you're making a mistake and start performing the combo from the
 * beginning), 'a', 'b', 'c', (here you're making a mistake and start performing
 * the combo from the beginning), 'a' (note that at this point you will not
 * perform the combo because of the mistake), 'b', 'c', 'a'.Your task is to
 * calculate for each button (letter) the number of times you'll press it.You
 * have to answer 𝑡independent test cases.
 *
 * ======
 *
 * Source: Codeforces
 */
public class PerformTheCombo {
	/**
	 * Since we have intervals [0, i], which indicate that from 0 to i letters
	 * were pressed, this means we can sum this pressings with prefix sums. For
	 * example we have arr [1,3,4], so it translates to [1,0,1,1] (counts of
	 * pressings from 0 to i) and this translates to prefix sum arr: [3,2,2,1]
	 * where i is letter at i pos in input string.
	 */
	private static class Solution {
		private static void s(InputReader in, PrintWriter o) {
			int max = (int) (1e5) * 2;
			int n = in.nextInt();
			int m = in.nextInt();
			String s = in.nextLine();
			int[] a = new int[max + 1];
			// counts of pressings from 0 to i
			for (int i = 0; i < m; i++)
				a[in.nextInt() - 1]++;
			a[n - 1]++;
			// count prefix sums from right to left
			for (int i = n - 1; i >= 0; i--)
				a[i] += i + 1 == n ? 0 : a[i + 1];
			int[] ans = new int[26];
			// for each letter find how much we pressed it from prefix sum arr
			for (int i = 0; i < n; i++)
				ans[s.charAt(i) - 'a'] += a[i];
			o.print(Arrays.toString(ans));
		}
	}
}
