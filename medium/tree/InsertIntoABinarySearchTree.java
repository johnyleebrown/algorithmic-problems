// 701
public class InsertIntoABinarySearchTree
{
	class Solution 
	{
		public TreeNode insertIntoBST(TreeNode root, int val) {
			return helper(root, val, new TreeNode(val));
		}
		
		private TreeNode helper(TreeNode root, int val, TreeNode newNode) {
			if (root == null) return newNode;
			if (root.val > val) root.left = helper(root.left, val, newNode);
			else root.right = helper(root.right, val, newNode);
			return root;
		}
	}

}

