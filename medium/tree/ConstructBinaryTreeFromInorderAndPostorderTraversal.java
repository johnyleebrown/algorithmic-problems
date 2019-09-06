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
	 */
	class Solution 
	{
		private Map<Integer, Integer> in = new HashMap<>();
		private Map<Integer, Integer> post = new HashMap<>();

		private int[] iAr;
		private int[] pAr;

		public TreeNode buildTree(int[] inorder, int[] postorder) 
		{
			init(inorder, postorder);
			return buildTreeChildByChildStrategy(0, inorder.length - 1);
		}

		private TreeNode buildTreeChildByChildStrategy(int i, int j)
		{
			int rootPostInd = findRootVal(i, j);
			int rootVal = pAr[rootPostInd];
			int rootInInd = in.get(rootVal);

			TreeNode root = new TreeNode(rootVal);
			root.left = rootInInd - 1 >= i ? buildTreeChildByChildStrategy(i, rootInInd - 1) : null;
			root.right = rootInInd + 1 <= j ? buildTreeChildByChildStrategy(rootInInd + 1, j) : null;

			return root;	
		}

		private int findRootVal(int i, int j)
		{
			int closest = post.get(iAr[i]);
			for (int k = i; k <= j; k++) closest = Math.max(closest, post.get(iAr[k]));
			return closest;
		}

		private void init(int[] inorder, int[] postorder)
		{
			iAr = inorder; pAr = postorder;
			for (int i = 0; i < inorder.length; i++) in.put(inorder[i], i);
			for (int i = 0; i < postorder.length; i++) post.put(postorder[i], i);
		}
	}
}

