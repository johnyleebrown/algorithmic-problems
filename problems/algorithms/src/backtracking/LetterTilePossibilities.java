package backtracking;

import java.util.HashSet;
import java.util.Set;

/**
 * 1079
 */
public class LetterTilePossibilities
{
	/**
	 * using letter seen and word seen
	 */
	class Solution
	{
		private Set<String> seenCombinations = new HashSet();
		private boolean[] seenLetters;

		public int numTilePossibilities(String tiles)
		{
			seenLetters = new boolean[tiles.length()];
			generate(tiles, "");
			return seenCombinations.size();
		}

		public void generate(String tiles, String combination)
		{
			// addition condition
			if (!combination.isEmpty())
			{
				seenCombinations.add(combination);
			}

			// abortion condition
			if (combination.length() >= tiles.length())
			{
				return;
			}

			for (int i = 0; i < tiles.length(); i++)
			{
				if (seenLetters[i])
				{
					continue;
				}

				seenLetters[i] = true;
				if (!seenCombinations.contains(combination + tiles.charAt(i)))
				{
					generate(tiles, combination + tiles.charAt(i));
				}
				seenLetters[i] = false;
			}
		}
	}
}

