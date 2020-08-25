package string.parseString.other;

import java.util.ArrayList;
import java.util.List;

/**
 * 722
 *
 * ======
 *
 * Task.
 *
 * Given a C++ program, remove comments from it. The program source is an array
 * where source[i] is the i-th line of the source code. This represents the
 * result of splitting the original source code string by the newline character
 * \n.
 *
 * ======
 *
 * Source: Leetcode
 */
public class RemoveComments
{
	/**
	 * We iterate through the lines. We either add text up until the // comment
	 * start. Or we enter multiline mode and iterate until we find an end of the
	 * m-line comment.
	 */
	public static class Solution
	{
		public List<String> removeComments(String[] s)
		{
			List<String> ans = new ArrayList<>();
			StringBuilder sb = new StringBuilder();
			boolean multilineMode = false;

			for (int i = 0; i < s.length; i++)
			{
				int j = 0;
				int m = s[i].length();

				while (j < m)
				{
					if (!multilineMode)
					{
						if (s1(j, m, s[i]))
						{
							multilineMode = true;
							j += 2;
							continue;
						}
						if (s2(j, m, s[i]))
						{
							break;
						}
						sb.append(s[i].charAt(j));
					}
					else
					{
						if (e1(j, m, s[i]))
						{
							multilineMode = false;
							j += 2;
							continue;
						}
					}
					j++;
				}
				if (!multilineMode && sb.length() != 0)
				{
					ans.add(sb.toString());
					sb.setLength(0);
				}
			}

			return ans;
		}

		boolean s1(int j, int m, String s)
		{
			return j + 1 < m && s.charAt(j) == '/' && s.charAt(j + 1) == '*';
		}

		boolean e1(int j, int m, String s)
		{
			return j + 1 < m && s.charAt(j) == '*' && s.charAt(j + 1) == '/';
		}

		boolean s2(int j, int m, String s)
		{
			return j + 1 < m && s.charAt(j) == '/' && s.charAt(j + 1) == '/';
		}
	}
}
