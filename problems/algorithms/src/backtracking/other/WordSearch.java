package backtracking.other;

/**
 * 79
 */
public class WordSearch
{
	/**
	 * check if we can construct the word starting from every letter and moving
	 * to its adj cells so use dfs
	 */
	class Solution
	{
		private final int[][] dirs = new int[][]{{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

		public boolean exist(char[][] board, String word)
		{
			// edges
			if (board == null || board.length == 0
					|| board[0].length == 0 || word == null || word.length() == 0)
				return false;
			if (board.length * board[0].length < word.length()) return false;

			for (int i = 0; i < board.length; i++)
				for (int j = 0; j < board[0].length; j++)
				{
					char t = board[i][j];
					if (t != word.charAt(0)) continue;
					board[i][j] = ' ';
					if (generate(t, 0, String.valueOf(t), i, j, board, word))
						return true;
					board[i][j] = t;
				}

			return false;
		}

		private boolean generate(char ch, int len, String cur, int i,
		                         int j, char[][] board, String word)
		{
			if (ch != word.charAt(len)) return false;
			if (len == word.length() - 1)
			{
				return true;
			}

			for (int[] dir : dirs)
			{
				int newI = i + dir[0], newJ = j + dir[1];
				if (!isValid(newI, newJ, board.length, board[0].length) ||
						board[newI][newJ] == ' ') continue;

				char temp = board[newI][newJ];
				board[newI][newJ] = ' ';
				if (generate(temp, len + 1, cur + temp, newI, newJ, board, word))
					return true;
				board[newI][newJ] = temp;
			}

			return false;
		}

		private boolean isValid(int i, int j, int n, int m)
		{
			return i >= 0 && j >= 0 && j < m && i < n;
		}
	}
}
/*
[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
"ABCCED"
[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
"SEE"
[["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]]
"ABCB"
[["a","a"]]
"aaa"
[["a","a","a","a"],["a","a","a","a"],["a","a","a","a"],["a","a","a","a"],["a","a","a","b"]]
"aaaaaaaaaaaaaaaaaaaa"
*/