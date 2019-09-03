package easy.tree;

import util.TreeNode;

// 538
public class ConvertBstToGreaterTree 
{
    // Reverse inorder traversal
    public class Solution 
	{
        int sum = 0;

        public TreeNode convertBST(TreeNode root) 
		{
            if (root == null) return null;
            
			convertBST(root.right);
            
			root.val += sum;
			sum = root.val;
            
			convertBST(root.left);
            
			return root;
        }
    }
}

