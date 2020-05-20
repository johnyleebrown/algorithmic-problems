package backtracking.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 93
 *
 * ======
 *
 * Task.
 *
 * Given a string containing only digits, restore it by returning all possible
 * valid IP address combinations.
 *
 * ======
 *
 * Source: Leetcode
 */
public class RestoreIPAddresses
{
	/**
	 * At each time we add a part of the address and a dot. Also make sure we
	 * meet all of conditions.
	 */
	class Solution
	{
		private List<String> res = new ArrayList<>();
		private int targetLen;

		public List<String> restoreIpAddresses(String s)
		{
			if (s.length() == 0)
			{
				return res;
			}

			targetLen = s.length() + 3;
			gen(s, "", 0, 1);

			return res;
		}

		private void gen(String s, String currentString, int curIndex, int level)
		{
			if (currentString.length() == targetLen && level == 5)
			{
				res.add(currentString);
				return;
			}

			if (level == 5)
			{
				return;
			}

			for (int i = 1; i <= 3; i++)
			{
				if (!isValidIndex(s, curIndex, i))
				{
					continue;
				}

				String addressPart = s.substring(curIndex, curIndex + i);
				if (!isValidNumber(addressPart))
				{
					continue;
				}

				gen(s, createVariant(currentString, addressPart, level), curIndex + i, level + 1);
			}
		}

		private String createVariant(String currentString, String addressPart, int level)
		{
			return currentString + addressPart + (level != 4 ? "." : "");
		}

		private boolean isValidIndex(String s, int curIndex, int i)
		{
			return curIndex + i <= s.length();
		}

		private boolean isValidNumber(String s)
		{
			if (s.length() >= 2 && s.charAt(0) == '0') return false;
			return Integer.parseInt(s) <= 255;
		}
	}
}