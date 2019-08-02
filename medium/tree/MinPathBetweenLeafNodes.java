package medium.tree;

/*
 * tags: {google}
 */
public class MinPathBetweenLeafNodes
{
	private class NAryTreeNode
	{
		private int val;
		private NAryTreeNode[] children;
		NAryTreeNode(){}
	}
	
	// we need to return a minimum at each crossroads
	// we need to update global minimum at each crossroads
	public int calculate(NAryTreeNode root)
	{
		if (root.left == null && root.right == null) return root;	
	}

}

