package hard.bfs;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.LinkedList;
import java.util.Queue;
import java.lang.StringBuilder;

/*
 * 854 K-Similar Strings
 * Tags: anagram, similar strings
 *
 * we want to find the number of swaps we need to perform to get the target string
 */
public class KSimilarStrings
{
	class Solution {
		public int kSimilarity(String A, String B) {
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
					for (String variant: variants)
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
				for (int j = 1; j < str.length() - 1; j++) {
					sb.setCharAt(i, sb.charAt(j));
					list.add(sb.toString());
					sb.setCharAt(i, c);
				}
			}
			return list;
		}

		private boolean isValid()
		{
			return true;
		}
	}	
}

