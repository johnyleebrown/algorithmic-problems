package tree.construct;

import _commons.TreeNode;
import util.tester.Tester;

import java.util.ArrayList;
import java.util.List;

/**
 * 1382
 *
 * ======
 *
 * Task.
 *
 * Given a binary search tree, return a balanced binary search tree with the
 * same node values.
 *
 * A binary search tree is balanced if and only if the depth of the two subtrees
 * of every node never differ by more than 1.
 *
 * If there is more than one answer, return any of them.
 *
 * ======
 *
 * Source: Leetcode
 */
public class BalanceABinarySearchTree
{
	/**
	 * SF.
	 */
	public static class Solution
	{
		private int ind;
		private List<Integer> nodes = new ArrayList<>();

		public TreeNode balanceBST(TreeNode root)
		{
			collectNodesValues(root, nodes);
			return buildTree(nodes.size());
		}

		private TreeNode buildTree(int count)
		{
			if (count <= 0)
				return null;
			int half = (count - 1) / 2;
			TreeNode l = buildTree(half);
			TreeNode cur = new TreeNode(nodes.get(ind++));
			TreeNode r = buildTree(count - 1 - half);
			cur.left = l;
			cur.right = r;
			return cur;
		}

		private void collectNodesValues(TreeNode cur, List<Integer> l)
		{
			if (cur == null)
				return;
			collectNodesValues(cur.left, l);
			l.add(cur.val);
			collectNodesValues(cur.right, l);
		}

		public Solution()
		{
		}
	}

	public static void main(String[] args)
	{
		new Tester(new Solution())
				.add(new TreeNode("1,null,2,null,3,null,4,null,null")).expect(new TreeNode("2,1,3,null,null,null,4"))
				.run();
	}
}
