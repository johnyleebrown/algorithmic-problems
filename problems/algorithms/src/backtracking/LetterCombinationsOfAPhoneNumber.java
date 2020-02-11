package backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * 17
 */
public class LetterCombinationsOfAPhoneNumber
{
	class Solution
	{
		private List<String> combinations = new ArrayList<>();

		private char[][] letters = new char[][]{
				{},
				{},
				{'a', 'b', 'c'},
				{'d', 'e', 'f'},
				{'g', 'h', 'i'},
				{'j', 'k', 'l'},
				{'m', 'n', 'o'},
				{'p', 'q', 'r', 's'},
				{'t', 'u', 'v'},
				{'w', 'x', 'y', 'z'},
		};

		public List<String> letterCombinations(String digits)
		{
			if (digits == null || digits.length() == 0)
			{
				return combinations;
			}

			generate("", -1, digits);
			return combinations;
		}

		private void generate(String combination, int j, String s)
		{
			if (combination.length() == s.length())
			{
				combinations.add(combination);
			}
			else
			{
				char[] row = letters[getInt(s, j + 1)];
				for (char c : row)
				{
					generate(combination + c, j + 1, s);
				}
			}
		}

		private int getInt(String s, int i)
		{
			return Character.getNumericValue(s.charAt(i));
		}
	}
}