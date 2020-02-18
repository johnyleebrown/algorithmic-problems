package graph.greedy;

import util.ds.InputReader;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * 1303C
 *
 * ======
 *
 * Task.
 *
 * Polycarp wants to assemble his own keyboard. Layouts with multiple rows are
 * too complicated for him — his keyboard will consist of only one row, where
 * all 26
 *
 * lowercase Latin letters will be arranged in some order.
 *
 * Polycarp uses the same password 𝑠 on all websites where he is registered (it
 * is bad, but he doesn't care). He wants to assemble a keyboard that will allow
 * to type this password very easily. He doesn't like to move his fingers while
 * typing the password, so, for each pair of adjacent characters in 𝑠, they
 * should be adjacent on the keyboard. For example, if the password is abacaba,
 * then the layout cabdefghi... is perfect, since characters a and c are
 * adjacent on the keyboard, and a and b are adjacent on the keyboard. It is
 * guaranteed that there are no two adjacent equal characters in 𝑠
 *
 * , so, for example, the password cannot be password (two characters s are
 * adjacent).
 *
 * Can you help Polycarp with choosing the perfect layout of the keyboard, if it
 * is possible?
 *
 * ======
 *
 * Source: Codeforces
 */
public class PerfectKeyboard
{
	/**
	 * Let's say each letter is a graph node. By connecting nodes we are saying
	 * that letters can be put side by side.
	 */
	private class Solution
	{
		/**
		 * Connecting 2 letters on the graph. Can't connect when we already put
		 * these 2 letters on the graph, and these 2 letters are not connected
		 * yet, this tells us that these letters are not side by side to each
		 * other and since each node can only have 2 vertices, we exit.
		 */
		private boolean connect(Map<Character, Set<Character>> m, char c1, char c2, boolean[] seen)
		{
			if (c1 == c2 || (m.get(c1).contains(c2) && m.get(c2).contains(c1)))
			{
				return true;
			}
			else if (m.get(c1).size() == 2 || m.get(c2).size() == 2)
			{
				return false;
			}
			else if (seen[c1 - 'a'] && seen[c2 - 'a'])
			{
				return false;
			}

			m.get(c1).add(c2);
			m.get(c2).add(c1);
			seen[c1 - 'a'] = true;
			seen[c2 - 'a'] = true;

			return true;
		}

		/**
		 * Using dfs traverse graph from starting point, don't go to visited
		 * places. Assemble a variant of a keyboard.
		 */
		private StringBuilder getVariant(Map<Character, Set<Character>> m, char start)
		{
			StringBuilder sb = new StringBuilder();
			Set<Character> s = new HashSet<>();
			char cur = start;

			while (!s.contains(cur))
			{
				sb.append(cur);
				s.add(cur);

				for (char child : m.get(cur))
				{
					if (!s.contains(child))
					{
						cur = child;
					}
				}
			}

			return sb;
		}

		/**
		 * For each nearby chars of the password, connect them on a graph, if
		 * not possible - exit. Then assemble a keyboard by going from some
		 * starting position and then by filling out the rest of the keys.
		 */
		private void s(String s, InputReader in, PrintWriter out)
		{
			Map<Character, Set<Character>> m = new HashMap<>();
			for (int i = 0; i < 26; i++)
			{
				m.put((char) (i + 'a'), new HashSet<>());
			}

			boolean[] seen = new boolean[26];
			int n = s.length();

			// starting point to assemble the keyboard
			char start = s.charAt(0);

			for (int i = 0; i < n - 1; i++)
			{
				char c1 = s.charAt(i);
				char c2 = s.charAt(i + 1);

				// if can't put letters side by side - exit
				if (!connect(m, c1, c2, seen))
				{
					out.println("NO");
					return;
				}

				// we choose any starting point
				if (m.get(c1).size() == 1)
				{
					start = c1;
				}
				if (m.get(c2).size() == 1)
				{
					start = c2;
				}
			}

			StringBuilder sb = null;

			// edge case
			if (s.length() == 1)
			{
				sb = new StringBuilder(s);
				seen[s.charAt(0) - 'a'] = true;
			}
			else
			{
				sb = getVariant(m, start);
			}

			// final touch - fill keyboard with other letters
			for (int i = 0; i < 26; i++)
			{
				char cur = (char) (i + 'a');

				if (!seen[i])
				{
					sb.append(cur);
				}
			}

			out.println("YES");
			out.println(sb.toString());
		}

		public void solve(InputReader in, PrintWriter out)
		{
			int t = in.nextInt();
			while (--t >= 0)
			{
				s(in.nextLine(), in, out);
			}
		}
	}
}