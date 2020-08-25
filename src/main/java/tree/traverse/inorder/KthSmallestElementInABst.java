package tree.traverse.inorder;

import util.ds.TreeNode;

import java.util.Stack;

/**
 * 230
 */
public class KthSmallestElementInABst
{
	private static class Solution
	{
		public int kthSmallest(TreeNode root, int k)
		{
			TreeNode t = root;
			Stack<TreeNode> st = new Stack<>();
			int count = 0;

			while (t != null || !st.isEmpty())
			{
				while (t != null)
				{
					st.push(t);
					t = t.left;
				}

				TreeNode x = st.pop();
				if (++count == k) return x.val;
				t = x.right;
			}

			return -1;
		}
	}
}