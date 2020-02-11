/*
 * 951
 * Google
 */
class Solution
{
	public boolean flipEquiv(TreeNode root1, TreeNode root2)
	{
		// same subtrees
		if (root1 == root2)
		{
			return true;
		}

		// if values match
		if (root1 == null || root2 == null || root1.val != root2.val)
		{
			return false;
		}

		// values match but subtrees don't
		return flipEquiv(root1.left, root2.left) && flipEquiv(root1.right, root2.right) 
			|| flipEquiv(root1.left, root2.right) && flipEquiv(root1.right, root2.left);
	}
}

