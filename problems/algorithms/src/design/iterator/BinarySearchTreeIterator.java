package design.iterator;

import util.ds.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 173
 */
public class BinarySearchTreeIterator {
	public static class Solution {
		class BSTIterator {
			Deque<TreeNode> st;

			public BSTIterator(TreeNode root) {
				st = new ArrayDeque<>();
				addLefts(root);
			}

			private void addLefts(TreeNode r) {
				while (r != null) {
					st.addLast(r);
					r = r.left;
				}
			}

			public int next() {
				TreeNode cur = st.removeLast();
				addLefts(cur.right);
				return cur.val;
			}

			public boolean hasNext() {
				return !st.isEmpty();
			}
		}
	}
}