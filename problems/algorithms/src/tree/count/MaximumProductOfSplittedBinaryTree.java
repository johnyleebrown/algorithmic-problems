package tree.count;

import util.ds.TreeNode;

import java.util.HashSet;
import java.util.Set;

/**
 * 1343
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree root. Split the binary tree into two subtrees by removing 1 edge such that the product of the
 * sums of the subtrees are maximized.
 *
 * Since the answer may be too large, return it modulo 10^9 + 7.
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaximumProductOfSplittedBinaryTree
{
	/**
	 * DFS. Count the total sum first. Count sums of all subtrees and cache them. Then iterate over all kinds of subtree
	 * sums and subtract in from total to find the max.
	 */
	class Solution
	{
		int mod = (int) (1e9) + 7;
		Set<Long> sums = new HashSet<>();

		public int maxProduct(TreeNode root)
		{
			long total_sum = dfs(root);

			long max = 0;
			for (long current_sum : sums)
			{
				max = Math.max(max, current_sum * (total_sum - current_sum));
			}

			return (int) (max % mod);
		}

		public long dfs(TreeNode root)
		{
			if (root == null)
			{
				return 0;
			}

			long left_sum = dfs(root.left);
			long right_sum = dfs(root.right);

			long total_sum = left_sum + right_sum + root.val;
			sums.add(total_sum);

			return total_sum;
		}

	}
}