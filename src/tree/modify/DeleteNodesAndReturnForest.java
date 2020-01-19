package tree.modify;

import util.ds.TreeNode;

import java.util.*;

/**
 * 1110
 */
class DeleteNodesAndReturnForest
{
	class Solution
	{
		private Set<Integer> set = new HashSet<>();

		public List<TreeNode> delNodes(TreeNode root, int[] to_delete)
		{
			List<TreeNode> res = new LinkedList<>();
			if (root == null)
			{
				return res;
			}

			for (int i : to_delete)
			{
				set.add(i);
			}

			Queue<TreeNode> q = new LinkedList<>();
			q.add(root);

			if (!set.contains(root.val))
			{
				res.add(root);
			}

			while (!q.isEmpty())
			{
				TreeNode x = q.poll();

				if (x.left != null)
				{
					TreeNode l = x.left;
					q.add(l);

					if (set.contains(x.left.val))
					{
						x.left = null;
					}
					if (set.contains(x.val) && !set.contains(l.val))
					{
						res.add(l);
					}
				}

				if (x.right != null)
				{
					TreeNode r = x.right;
					q.add(r);

					if (set.contains(x.right.val))
					{
						x.right = null;
					}
					if (set.contains(x.val) && !set.contains(r.val))
					{
						res.add(r);
					}
				}
			}

			return res;
		}
	}
}
