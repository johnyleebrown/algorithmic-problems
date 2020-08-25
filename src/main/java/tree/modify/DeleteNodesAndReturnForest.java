package tree.modify;

import util.ds.TreeNode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 1110
 */
class DeleteNodesAndReturnForest {
		class Solution {
				List<TreeNode> l = new ArrayList<>();

				public List<TreeNode> delNodes(TreeNode root, int[] d) {
						Set<Integer> s = new HashSet<>();
						for (int i : d) s.add(i);
						root = h(s, root);
						if (root != null) l.add(root);
						return l;
				}

				private TreeNode h(Set<Integer> s, TreeNode cur) {
						if (cur == null) return null;
						cur.left = h(s, cur.left);
						cur.right = h(s, cur.right);
						if (s.contains(cur.val)) {
								if (cur.left != null) l.add(cur.left);
								if (cur.right != null) l.add(cur.right);
								return null;
						}
						return cur;
				}
		}
}
