package design.iterator;

import util.ds.TreeNode;

import java.util.Stack;

/**
 * 173
 */
public class BinarySearchTreeIterator
{
	private Stack<TreeNode> stack;
	private TreeNode node;

	public BinarySearchTreeIterator(TreeNode root)
	{
		stack = new Stack<>();
		node = root;
	}

	public boolean hasNext()
	{
		return !stack.isEmpty() || node != null;
	}

	public int next()
	{
		while (node != null)
		{
			stack.push(node);
			node = node.left;
		}

		node = stack.pop();
		int res = node.val;
		node = node.right;

		return res;
	}
}