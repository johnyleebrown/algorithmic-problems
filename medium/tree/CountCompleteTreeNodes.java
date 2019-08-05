package Medium.Tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import Helpers.Helper;
import util.TreeNode;

/**
 * 222
 *
 * Given a complete binary tree, count the number of nodes.
 */
public class CountCompleteTreeNodes 
{

	// bfs 10/100
	class Solution 
	{
		public int countNodes(TreeNode root) 
		{
			if (root == null) return 0;
			int count = 0;       
			Queue<TreeNode> q = new LinkedList<>();
			q.add(root);
			while (!q.isEmpty())
			{
				int size = q.size();
				count += size;
				while (--size >= 0)
				{
					TreeNode n = q.poll();
					if (n.left != null) q.add(n.left);
					if (n.right != null) q.add(n.right);
				}
			}
			return count;
		}
	}

	class Solution2 
	{
		public int countNodes(TreeNode root) 
		{
			int nodes = 0;
			int h = height(root);
			
			while (root != null) 
			{
				if (height(root.right) == h - 1) 
				{
					nodes += 1 << h;
					root = root.right;
				} 
				else 
				{
					nodes += 1 << h-1;
					root = root.left;
				}
				
				h--;
			}
			
			return nodes;
		}
		 
		private int height(TreeNode root) 
		{
			return root == null ? -1 : 1 + height(root.left);
		}
	}
}

