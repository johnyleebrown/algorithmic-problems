package util.tester;

public class TesterPreprocessor
{
	@SuppressWarnings({"unchecked"})
	public static int[][] ararFromString(String s)
	{
		//[[1,2],[1,3],[1,7],[2,4],[2,6],[3,5]]
		String[] rightBracketSplit = s.split("]");
		int n = rightBracketSplit.length;
		int[][] res = new int[n][];
		for (int i = 0; i < n; i++)
		{
			int k = 0;
			String cur = rightBracketSplit[i];
			String[] leftBracketSplit = cur.split("\\[");
			String[] commaSplit = leftBracketSplit[leftBracketSplit.length - 1].split(",");
			res[i] = new int[commaSplit.length];
			for (int j = 0; j < commaSplit.length; j++)
			{
				res[i][j] = Integer.parseInt(commaSplit[j]);
			}
		}
		return res;
	}

	@SuppressWarnings({"unchecked"})
	public static int[] arFromString(String s)
	{
		//[5,9,6,10,-1,8,9,1,9,3,4]

		//empty array []
		if (s.length() == 2)
			return new int[]{};

		//arr of strings 5,9,6,10,-1,8,9,1,9,3,4
		String[] splitOnComma = s
				.replaceAll("\\[","")
				.replaceAll("]","")
				.split(",");
		int n = splitOnComma.length;

		int[] ans = new int[n];
		for (int i = 0; i < n; i++)
			ans[i] = Integer.parseInt(splitOnComma[i]);

		return ans;
	}
}
