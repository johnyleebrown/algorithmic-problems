package tree.construct;

import util.ds.TreeNode;

/**
 * 654
 */
public class MaximumBinaryTree
{
	class Solution
	{
		public TreeNode constructMaximumBinaryTree(int[] nums)
		{
			if (nums == null || nums.length == 0)
			{
				return null;
			}

			return subtreeSearch(0, nums.length - 1, nums);
		}

		private TreeNode subtreeSearch(int i, int j, int[] nums)
		{
			if (j < i)
			{
				return null;
			}

			int maxInd = i;
			int maxNum = nums[i];
			for (int k = i + 1; k <= j; k++)
			{
				if (maxNum < nums[k])
				{
					maxNum = nums[k];
					maxInd = k;
				}
			}

			TreeNode root = new TreeNode(maxNum);
			root.left = subtreeSearch(i, maxInd - 1, nums);
			root.right = subtreeSearch(maxInd + 1, j, nums);

			return root;
		}
	}
}