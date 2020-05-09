package tree.traverse;


import util.ds.TreeNode;

/**
 * 117
 */
public class PopulatingNextRightPointersInEachNodeII {
	/**
	 * We have 2 tasks that we can repeat
	 * 1 find the running child
	 * 2 find next for the child
	 */
	public static class Solution {
		TreeNode cur;

		public TreeNode connect(TreeNode root) {
			TreeNode child = null;
			TreeNode next = null;
			TreeNode newLevel = null;
			cur = root;

			while (cur != null) {
				child = getChild(child);

				// next level will be the first child
				if (newLevel == null) newLevel = child;

				next = getNext(next, child);

				// exit - no children
				if (child == null) break;

				child.next = next;
				child = child.next;
				next = null;

				if (cur == null) {
					cur = newLevel;
					newLevel = null;
				}
			}

			return root;
		}

		TreeNode getChild(TreeNode child) {
			while (cur != null && child == null) {
				if (cur.left != null) {
					return cur.left;
				} else if (cur.right != null) {
					return cur.right;
				}
				cur = cur.next;
			}
			return child;
		}

		/**
		 * cur.right != child - we check if child is at right node then we need
		 * to go to next cur
		 * we can't go to next cur if child is at left node
		 */
		TreeNode getNext(TreeNode next, TreeNode child) {
			while (cur != null) {
				if (cur.left != null && cur.left != child && cur.right != child) {
					return cur.left;
				} else if (cur.right != null && cur.right != child) {
					return cur.right;
				}
				cur = cur.next;
			}
			return next;
		}
	}

	public static class Solution2 {
		public TreeNode connect(TreeNode root) {
			if (root == null) return null;
			TreeNode returnTreeNode = root;
			TreeNode start = root;
			TreeNode prev = null;

			while (root != null) {
				if (root.left != null) {
					if (start == null) {
						start = root.left;
					}
					if (prev != null) {
						prev.next = root.left;
					}

					prev = root.left;
				}

				if (root.right != null) {
					if (start == null) {
						start = root.right;
					}
					if (prev != null) {
						prev.next = root.right;
					}

					prev = root.right;
				}

				root = root.next;
				if (root == null) {
					prev = null;
					root = start;
					start = null;
				}
			}

			return returnTreeNode;
		}
	}
}