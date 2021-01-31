package tree.compare;

import commons.TreeNode;

/**
 * 951
 */
public class FlipEquivalentBinaryTrees {
	class Solution {
		public boolean flipEquiv(TreeNode c1, TreeNode c2) {
			if (c1 == null && c2 == null) return true;
			if (c1 == null || c2 == null || c1.val != c2.val) return false;
			return (flipEquiv(c1.left, c2.right) && flipEquiv(c1.right, c2.left)) || (flipEquiv(c1.right, c2.right) && flipEquiv(c1.left, c2.left));
		}
	}
}