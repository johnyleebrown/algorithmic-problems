package hashtable;

import com.sun.tools.javac.util.Pair;

import java.util.HashSet;
import java.util.Set;

/**
 * 36
 */
public class ValidSudoku
{
	/**
	 * Time complexity: O(n) Space complexity: O(n)
	 */
	public static boolean solution(char[][] board)
	{
		Set<String> seen = new HashSet<>();
		for (int i = 0; i < 9; ++i)
		{
			for (int j = 0; j < 9; ++j)
			{
				char number = board[i][j];
				if (number != '.')
					if (!seen.add(number + " in row " + i) ||
							!seen.add(number + " in column " + j) ||
							!seen.add(number + " in block " + i / 3 + "-" + j / 3))
						return false;
			}
		}
		Pair<Integer, Integer> p;
		return true;
	}
}
