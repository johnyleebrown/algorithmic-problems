// 990
public class SatisfiabilityOfEqualityEquations
{
	class Solution 
	{
		private int[] parents;

		public boolean equationsPossible(String[] equations) 
		{
			parents = new int[26];

			for (int i = 0; i < 26; i++) parents[i] = i;

			for (String eq: equations) if (convert(eq.charAt(1)))
			{
				union(getInt(eq.charAt(0)), getInt(eq.charAt(3)));
			}

			for (String eq: equations) if (!convert(eq.charAt(1)))
			{
				if (find(getInt(eq.charAt(0))) == find(getInt(eq.charAt(3)))) 
					return false;
			}

			return true;
		}

		private int getInt(char c) { return (int) c - 97; }
		private boolean convert(char c) { return c == '=' ? true : false; }

		private void union(int p, int q)
		{
			parents[find(p)] = find(q);
		}

		private int find(int p)
		{
			while (p != parents[p]) p = parents[p];
			return p;
		}
	}
}

