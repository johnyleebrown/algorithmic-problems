package tree.bst;

import _commons.TreeNode;
import util.tester.Tester;

/**
 * 1373
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree root, the task is to return the maximum sum of all keys
 * of any sub-tree which is also a Binary Search Tree (BST).
 *
 * ======
 *
 * Source: Leetcode
 */
public class MaximumSumBSTInBinaryTree
{
	/**
	 * If subtree is a bst, find max sum in it. To optimize - go from the
	 * bottom, post order and check if bst then compare sums.
	 */
	public static class Solution
	{
		private int max = 0;

		public int maxSumBST(TreeNode root)
		{
			traverseTree(root);
			return max;
		}

		private void traverseTree(TreeNode cur)
		{
			if (cur == null)
				return;

			if (isValidBST(cur))
			{
				findMaxSum(cur);
				return;
			}

			traverseTree(cur.left);
			traverseTree(cur.right);
		}

		private int findMaxSum(TreeNode cur)
		{
			if (cur == null)
				return 0;
			int l = findMaxSum(cur.left);
			int r = findMaxSum(cur.right);
			int all = cur.val + l + r;
			max = Math.max(max, all);
			return all;
		}

		private boolean isValidBST(TreeNode cur)
		{
			return f(cur, null, null);
		}

		private boolean f(TreeNode cur, Integer min, Integer max)
		{
			if (cur == null)
				return true;
			if (max != null && cur.val >= max || min != null && cur.val <= min)
				return false;
			return f(cur.left, min, cur.val) && f(cur.right, cur.val, max);
		}

		public Solution()
		{
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new TreeNode("1,4,3,2,4,2,5,null,null,null,null,null,null,4,6")).expect(20)
				.add(new TreeNode("4,3,null,1,2")).expect(2)
				.add(new TreeNode("-4,-2,-5")).expect(0)
				.add(new TreeNode("2,1,3")).expect(6)
				.add(new TreeNode("5,4,8,3,null,6,3")).expect(7)
				.run();
	}
}
