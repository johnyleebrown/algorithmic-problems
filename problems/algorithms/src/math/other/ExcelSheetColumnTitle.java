package math.other;

/**
 * 168
 */
public class ExcelSheetColumnTitle
{
	class Solution
	{
		public String convertToTitle(int n)
		{
			StringBuilder sb = new StringBuilder();
			while (n > 0)
			{
				sb.insert(0, (char) ('A' + ((n - 1) % 26)));
				n = (n - 1) / 26;
			}
			return sb.toString();
		}
	}
}
