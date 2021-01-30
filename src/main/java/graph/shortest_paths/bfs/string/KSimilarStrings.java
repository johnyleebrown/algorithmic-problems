package graph.shortest_paths.bfs.string;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.StringBuilder;

/**
 * 854
 */
public class KSimilarStrings
{
	/**
	 * 70% fast.
	 *
	 * time complexity for the result case we will have n permutations, this means that for others is always < n.
	 * overall -> O(n).
	 *
	 * the idea here is to find the chars in string polled from queue that match the char on position in i from target
	 * string. this way we are finding the right spots for each char. and we don't want to touch the chars that are
	 * already aligned with B.
	 */
	class Solution
	{
		public int kSimilarity(String A, String B)
		{
			if (A.equals(B)) return 0;

			int n = A.length();
			Set<String> seen = new HashSet<>();
			seen.add(A);
			Queue<String> q = new LinkedList<>();
			q.add(A);

			int k = 0;
			while (!q.isEmpty())
			{
				k++;
				int size = q.size();

				while (--size >= 0)
				{
					StringBuilder sb = new StringBuilder(q.poll());

					int i = 0;
					while (i < n && sb.charAt(i) == B.charAt(i)) i++; // optimization

					char c1 = sb.charAt(i);
					for (int j = i + 1; j < n; j++)
					{
						char c2 = sb.charAt(j);

						// the main idea of the optimization
						// looking for chars that are the same as the char in target
						// but not the chars that are already in the right place
						if (c2 != B.charAt(i) || c2 == B.charAt(j)) continue;

						// swap
						sb.setCharAt(i, c2);
						sb.setCharAt(j, c1);

						// check the variant
						String variant = sb.toString();
						if (seen.add(variant))
						{
							if (variant.equals(B)) return k;
							q.add(variant);
						}

						// swap back
						sb.setCharAt(i, c1);
						sb.setCharAt(j, c2);
					}
				}
			}

			return k;
		}
	}

	/**
	 * TLE
	 * <p>
	 * well, yeah, we check all the permutations of each variant. yikes.
	 */
	class Solution2
	{
		public int kSimilarity(String A, String B)
		{
			if (A.equals(B)) return 0;
			Set<String> seen = new HashSet<>();
			seen.add(A);
			Queue<String> q = new LinkedList<>();
			q.add(A);
			int k = 0;
			while (!q.isEmpty())
			{
				k++;
				int size = q.size();
				while (--size >= 0)
				{
					String str = q.poll();
					List<String> variants = generateStrings(str);
					for (String variant : variants)
					{
						if (!seen.add(variant)) continue;
						if (variant.equals(B)) return k;
						q.add(variant);
					}
				}
			}
			return k;
		}

		private List<String> generateStrings(String str)
		{
			StringBuilder sb = new StringBuilder(str);
			List<String> list = new LinkedList<>();
			for (int i = 0; i < str.length() - 1; i++)
			{
				char c = sb.charAt(i);
				for (int j = 1; j < str.length() - 1; j++)
				{
					sb.setCharAt(i, sb.charAt(j));
					list.add(sb.toString());
					sb.setCharAt(i, c);
				}
			}
			return list;
		}
	}
}
/*
"ab"
"ba"
"abc"
"bca"
"abac"
"baca"
"aabc"
"abca"
"abccaacceecdeea"
"bcaacceeccdeaae"
*/
