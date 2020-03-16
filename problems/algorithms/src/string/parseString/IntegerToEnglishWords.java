package string.parseString;

import util.tester.Tester;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 273
 *
 * ======
 *
 * Task.
 *
 * Convert a non-negative integer to its english words representation.
 *
 * ======
 *
 * Similar: $INSERT_SIMILAR.
 *
 * ======
 *
 * Source: Leetcode
 */
public class IntegerToEnglishWords
{
	/**
	 * $INSERT_EXPLANATION.
	 */
	public static class Solution
	{
		String[] d = new String[]{"", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine"};
		String[] dd = new String[]{"", "Ten", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
		String[] other = new String[]{"Hundred", "Thousand", "Million", "Billion"};
		String[] tens = new String[]{"Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};

		public String numberToWords(int num)
		{
			if (num == 0)
			{
				return "Zero";
			}
			Deque<String> st = new ArrayDeque<>();
			int k = 0;
			int c = 0;
			while (num > 0)
			{
				int curDigit = num % 10;
				num /= 10;
				k++;
				if (k == 1)
				{
					if (c != 0 && (curDigit != 0 || num % 10 != 0 || (num / 10) % 10 != 0))
					{
						st.push(other[c]);
					}
					if (num % 10 == 1)
					{
						st.push(tens[curDigit]);
						num /= 10;
						k = 2;
					}
					else
					{
						if (curDigit != 0)
							st.push(d[curDigit]);
					}
				}
				else if (k == 2)
				{
					if (curDigit != 0)
						st.push(dd[curDigit]);
				}
				else if (k == 3)
				{
					if (curDigit != 0)
					{
						st.push(other[0]);
						st.push(d[curDigit]);
					}
					k = 0;
					c++;
				}
			}
			StringBuilder ret = new StringBuilder();
			while (!st.isEmpty())
			{
				ret.append(st.pop()).append(!st.isEmpty() ? " " : "");
			}
			return ret.toString();
		}

		public Solution()
		{
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(123).expect("One Hundred Twenty Three")
				.add(12345).expect("Twelve Thousand Three Hundred Forty Five")
				.add(0).expect("Zero")
				.add(1234567).expect("One Million Two Hundred Thirty Four Thousand Five Hundred Sixty Seven")
				.add(1234567891).expect("One Billion Two Hundred Thirty Four Million Five Hundred Sixty Seven Thousand Eight Hundred Ninety One")
				.add(1).expect("One")
				.add(11).expect("Eleven")
				.run();
	}
}
