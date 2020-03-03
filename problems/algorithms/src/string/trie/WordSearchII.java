package string.trie;

import util.test.Tester;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 212
 *
 * ======
 *
 * Task.
 *
 * Given a 2D board and a list of words from the dictionary, find all words in
 * the board.
 *
 * Each word must be constructed from letters of sequentially adjacent cell,
 * where "adjacent" cells are those horizontally or vertically neighboring. The
 * same letter cell may not be used more than once in a word.
 *
 * ======
 *
 * Source: Leetcode
 */
public class WordSearchII
{
	/**
	 * Trie + dfs. The trick here is to set to false isEnd once we ve added to
	 * res, or use set.
	 */
	private static class Solution
	{
		private int[][] dirs = new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
		private List<String> res = new ArrayList<>();
		private int n, m;
		private Node root;

		public List<String> findWords(char[][] b, String[] words)
		{
			n = b.length;
			m = b[0].length;
			Trie t = new Trie();
			root = t.root;
			for (String w : words)
				t.put(w);
			for (int i = 0; i < n; i++)
				for (int j = 0; j < m; j++)
					dfs(i, j, b, root, new boolean[n][m]);
			return res;
		}

		private void dfs(int i, int j, char[][] b, Node curNode, boolean[][] seen)
		{
			// check if is valid
			if (i < 0 || j < 0 || i >= n || j >= m)
				return;

			if (seen[i][j])
				return;

			char curChar = b[i][j];
			if (!curNode.contains(curChar))
				return;
			curNode = curNode.get(curChar);

			// add to ans
			if (curNode.isEnd)
			{
				res.add(curNode.val);
				curNode.isEnd = false;
			}

			seen[i][j] = true;

			// go to variants
			for (int[] d : dirs)
				dfs(d[0] + i, d[1] + j, b, curNode, seen);

			seen[i][j] = false;
		}

		private class Trie
		{
			private Node root = new Node();

			public void put(String word)
			{
				Node cur = root;
				int n = word.length();
				for (int i = 0; i < n; i++)
				{
					char c = word.charAt(i);
					if (cur.get(c) == null)
						cur.put(c);
					cur = cur.get(c);
				}
				cur.isEnd = true;
				cur.val = word;
			}
		}

		private class Node
		{
			boolean isEnd = false;
			Node[] children = new Node[256];
			String val = null;

			public void put(char c)
			{
				children[c] = new Node();
			}

			public Node get(char c)
			{
				return children[c];
			}

			public boolean contains(char c)
			{
				return children[c] != null;
			}
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new char[][]{{'a','b','c'},{'a','e','d'},{'a','f','g'}}, new String[]{"abcdefg","gfedcbaaa","eaabcdgfa","befa","dgc","ade"}).expect(Arrays.asList("abcdefg","befa","eaabcdgfa","gfedcbaaa")).expectAnyOrder()
				.run();
	}
}
