package tree.ds_;

import util.ds.TreeNode;

import java.util.Stack;

/**
 * Expression tree for the example: (2?(1?2)) or ((1?(5?7))?((6?2)?7)). Regular notation.
 * Using tree node here, op nodes are identified with boolean flag set to true.
 */
public class ExpressionTree
{
	private TreeNode root;

	public ExpressionTree(String a)
	{
		root = build(a);
	}

	/**
	 * Let's assume that there are at least 2 numbers and 1 op.
	 */
	private TreeNode build(String a)
	{
		Stack<Character> st1 = new Stack<>();
		Stack<TreeNode> st2 = new Stack<>();

		int n = a.length();
		for (int i = 0; i < n; i++)
		{
			char c = a.charAt(i);
			if (c != ')')
			{
				st1.push(c);
			}
			// if == '('
			else
			{
				buildSubtree(st1, st2);
			}
		}

		return st2.pop();
	}

	private void buildSubtree(Stack<Character> st1, Stack<TreeNode> st2)
	{
		TreeNode r = getNode(st1.pop(), st2);
		TreeNode op = getNode(st1.pop(), st2);
		TreeNode l = getNode(st1.pop(), st2);
		op.right = r;
		op.left = l;
		st1.pop(); // remove left bracket
		st2.push(op);
		st1.push('*'); // place smth instead of the expr in braces
	}

	private TreeNode getNode(char cur, Stack<TreeNode> st2)
	{
		if (cur == '*')
		{
			return st2.pop();
		}
		else if (cur == '?')
		{
			return new TreeNode(true);
		}

		return new TreeNode(cur - '0');
	}

	public TreeNode getRoot()
	{
		return root;
	}

	public static void main(String[] args)
	{
		new ExpressionTree("((1?(5?7))?((6?2)?7))").getRoot().printTreeLevelOrder();
		new ExpressionTree("(2?(1?2))").getRoot().printTreeLevelOrder();
	}
}
