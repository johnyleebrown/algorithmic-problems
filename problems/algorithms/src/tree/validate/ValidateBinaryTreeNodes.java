package tree.validate;

import util.tester.Tester;

/**
 * 1361
 *
 * ======
 *
 * Task.
 *
 * You have n binary tree nodes numbered from 0 to n - 1 where node i has two
 * children leftChild[i] and rightChild[i], return true if and only if all the
 * given nodes form exactly one valid binary tree.
 *
 * If node i has no left child then leftChild[i] will equal -1, similarly for
 * the right child.
 *
 * Note that the nodes have no values and that we only use the node numbers in
 * this problem.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ValidateBinaryTreeNodes
{
	/**
	 * SF. Tree traversal.
	 */
	public static class Solution
	{
		int n;

		public boolean validateBinaryTreeNodes(int n, int[] l, int[] r)
		{
			this.n = n;
			int i = 0;
			for (; i < n; i++)
				if (l[i] == 0 || r[i] == 0)
					break;
			return dfs(i == n ? 0 : i, l, r, new boolean[n]) && this.n == 0;
		}

		private boolean dfs(int cur, int[] l, int[] r, boolean[] seen)
		{
			if (cur == -1)
				return true;
			if (seen[cur])
				return false;
			n--;
			seen[cur] = true;
			boolean left = dfs(l[cur], l, r, seen);
			boolean right = dfs(r[cur], l, r, seen);
			return left && right;
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(4, new int[]{1, -1, 3, -1}, new int[]{2, -1, -1, -1}).expect(true)
				.add(4, new int[]{1, -1, 3, -1}, new int[]{2, 3, -1, -1}).expect(false)
				.add(2, new int[]{1, 0}, new int[]{-1, -1}).expect(false)
				.add(6, new int[]{1, -1, -1, 4, -1, -1}, new int[]{2, -1, -1, 5, -1, -1}).expect(false)
				.run();
	}
}
