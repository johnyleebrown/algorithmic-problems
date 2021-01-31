package graph.shortest_paths.bfs.tree;

import commons.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 513
 */
public class FindBottomLeftTreeValue
{

	public class Solution
	{
		public int findBottomLeftValue(TreeNode root)
		{
			return findBottomLeftValue(root, 1, new int[]{0, 0});
		}

		public int findBottomLeftValue(TreeNode root, int depth, int[] res)
		{
			if (res[1] < depth)
			{
				res[0] = root.val;
				res[1] = depth;
			}
			if (root.left != null) findBottomLeftValue(root.left, depth + 1, res);
			if (root.right != null) findBottomLeftValue(root.right, depth + 1, res);
			return res[0];
		}
	}

	// taking the first element of the row
	class Solution2
	{
		public int findBottomLeftValue(TreeNode root)
		{
			Queue<TreeNode> q = new LinkedList<>();
			q.add(root);
			int result = root.val;

			while (!q.isEmpty())
			{
				int qsize = q.size();
				result = q.peek().val;
				while (--qsize >= 0)
				{
					TreeNode node = q.poll();
					if (node.left != null) q.add(node.left);
					if (node.right != null) q.add(node.right);
				}
			}

			return result;
		}
	}
}
