package math.parseNumber;

import java.util.Stack;

/**
 * 1323
 *
 * ======
 *
 * Task.
 *
 * Given a positive integer num consisting only of digits 6 and 9.
 *
 * Return the maximum number you can get by changing at most one digit (6 becomes 9, and 9 becomes 6).
 */
public class Maximum69Number
{
	/**
	 * Using stack.
	 */
	static class Solution1
	{
		public int maximum69Number(int num)
		{
			Stack<Integer> st = new Stack<>();
			while (num > 0)
			{
				int x = num % 10;
				num = num / 10;
				st.push(x);
			}

			int result = 0;
			boolean found = false;

			while (!st.isEmpty())
			{
				int x = st.pop();
				if (x == 6 && found == false)
				{
					x = 9;
					found = true;
				}

				result = result * 10 + x;
			}

			return result;
		}
	}

	/**
	 * Parsing string.
	 */
	static class Solution2
	{
		public int maximum69Number(int num)
		{
			String s = String.valueOf(num);
			for (int i = 0; i < s.length(); i++)
			{
				if (s.charAt(i) == '6')
				{
					return Integer.parseInt(s.substring(0, i) + "9" + s.substring(i + 1, s.length()));
				}
			}
			return Integer.parseInt(s);
		}
	}
}
