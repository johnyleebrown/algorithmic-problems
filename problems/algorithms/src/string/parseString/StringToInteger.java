package string.parseString;

import java.math.BigInteger;

/**
 * 8
 *
 * ======
 *
 * Task.
 *
 * Implement atoi which converts a string to an integer.
 *
 * The function first discards as many whitespace characters as necessary until
 * the first non-whitespace character is found. Then, starting from this
 * character, takes an optional initial plus or minus sign followed by as many
 * numerical digits as possible, and interprets them as a numerical value.
 *
 * The string can contain additional characters after those that form the
 * integral number, which are ignored and have no effect on the behavior of this
 * function.
 *
 * If the first sequence of non-whitespace characters in str is not a valid
 * integral number, or if no such sequence exists because either str is empty or
 * it contains only whitespace characters, no conversion is performed.
 *
 * If no valid conversion could be performed, a zero value is returned.
 *
 * ======
 *
 * Source: Leetcode
 */
public class StringToInteger
{
	/**
	 * SF.
	 */
	class Solution
	{
		public int myAtoi(String s)
		{
			int n = s.length();
			if (n == 0)
				return 0;

			int i = 0;
			StringBuilder sb = new StringBuilder();
			boolean minus = false;

			while (i < n)
			{
				if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != '+' && s.charAt(i) != '-' && s.charAt(i) != ' ')
				{
					return 0;
				}
				if ((s.charAt(i) == '+' || s.charAt(i) == '-') && i + 1 < n && !Character.isDigit(s.charAt(i + 1)))
				{
					return 0;
				}
				if (i > 0 && Character.isDigit(s.charAt(i)) && s.charAt(i - 1) == '-')
				{
					minus = true;
				}
				while (i < n && Character.isDigit(s.charAt(i)))
				{
					sb.append(s.charAt(i++));
				}
				if (i > 0 && Character.isDigit(s.charAt(i - 1)) && i < n && !Character.isDigit(s.charAt(i)))
				{
					break;
				}
				i++;
			}

			if (sb.length() == 0)
				return 0;

			BigInteger pr = null;
			if (minus)
				pr = new BigInteger("-" + sb.toString());
			else
				pr = new BigInteger(sb.toString());

			return pr.compareTo(new BigInteger(String.valueOf(Integer.MAX_VALUE))) == 1
					? Integer.MAX_VALUE
					: pr.compareTo(new BigInteger(String.valueOf(Integer.MIN_VALUE))) == -1
					? Integer.MIN_VALUE
					: pr.intValue();
		}
	}
}
