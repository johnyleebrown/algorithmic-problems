package medium.tree;

// 106
public class ConstructBinaryTreeFromInorderAndPostorderTraversal
{
	/*
	 * inorder = [9,3,15,20,7]
	 * postorder = [9,15,7,20,3]
	 *
	 * 1 we have postorder so the root always will be the most right item
	 * 2 find it in inorder (i, j) and remember position = k
	 * 3 (i,k) - left subtree, (k, j) - right subtree
	 *
	 * opt.I 
	 * maybe map for postorder so we could check the indexes and 
	 * not search every time for a place (search for n^2 -> n)
	 *
	 * opt.II
	 * no post map - find the next root with the smart way
	 */
	class Solution {
		private Map<Integer, Integer> in = new HashMap<>();
		private Map<Integer, Integer> post = new HashMap<>();

		public TreeNode buildTree(int[] inorder, int[] postorder) {
			if (inorder == null || postorder == null || inorder.length == 0 
					|| postorder.length == 0 || inorder.length != postorder.length) return null;

			for (int i = 0; i < inorder.length; i++) in.put(inorder[i], i);
			for (int i = 0; i < postorder.length; i++) post.put(postorder[i], i);
			
			return buildTreeChildByChildStrategy(0, inorder.length - 1, inorder, postorder);
		}

		private TreeNode buildTreeChildByChildStrategy(int i, int j, int[] inorder, int[] postorder)
		{
			int rootPostInd = findRootVal(i, j, inorder);
			int rootVal = postorder[rootPostInd];

			int rootInInd = in.get(rootVal);
			
			TreeNode root = new TreeNode(rootVal);
			root.left = rootInInd - 1 >= i ? buildTreeChildByChildStrategy(i, rootInInd - 1, inorder, postorder) : null;
			root.right = rootInInd + 1 <= j ? buildTreeChildByChildStrategy(rootInInd + 1, j, inorder, postorder) : null;

			return root;	
		}
		
		private int findRootVal(int i, int j, int[] inorder)
		{
			int closest = post.get(inorder[i]);
			for (int k = i; k <= j; k++)
			{
				closest = Math.max(closest, post.get(inorder[k]));
			}
			return closest;
		}

	}
}

