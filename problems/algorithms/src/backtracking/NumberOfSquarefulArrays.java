package backtracking;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 996
 */
public class NumberOfSquarefulArrays
{
	class Solution
	{
		private Set<String> seenStr = new HashSet<>();
		private boolean[] seen;

		public int numSquarefulPerms(int[] A)
		{
			if (A.length == 0) return 0;
			seen = new boolean[A.length];
			Arrays.sort(A); // optimization
			generate(A, "", 0, null);
			return seenStr.size();
		}

		private void generate(int[] A, String s, int c, Integer prev)
		{
			if (c == A.length) seenStr.add(s);
			else
			{
				for (int i = 0; i < A.length; i++)
				{
					if (seen[i]) continue;
					// optimization
					if (i > 0 && A[i - 1] == A[i] && !seen[i - 1]) continue;
					if (prev != null && isSq(A[prev], A[i])) continue;

					seen[i] = true;
					generate(A, s + "," + A[i], c + 1, i);
					seen[i] = false;
				}
			}
		}

		private boolean isSq(Integer prev, int cur)
		{
			int sum = prev + cur;
			int x = (int) Math.sqrt(sum);
			return x * x != sum;
		}
	}
}