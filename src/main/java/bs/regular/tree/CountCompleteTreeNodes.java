package bs.regular.tree;

import _commons.TreeNode;

import java.util.LinkedList;
import java.util.List;

/**
 * 222
 */
public class CountCompleteTreeNodes {
	/**
	 * Complete binary tree number of nodes: n = (2^(h + 1)) - 1. Number of
	 * nodes on the last level = 2 ^ (h). We know that number of nodes will be
	 * between (h-1) levels of nodes + 1 node on last level and n. Npw, hi = n,
	 * we need lo. lo is first node on last level. We subtract 1 from lo and hi,
	 * because these number might be the answer. lo = n - last_level + 1 =
	 * ((2^(h + 1)) - 1) - 2^h + 1 = 2^h.
	 */
	public static class Solution {
		public int countNodes(TreeNode root) {
			int h = getHeight(root);
			int n = ((int) Math.pow(2, h + 1)) - 1;
			int lo = ((int) Math.pow(2, h)) - 1;
			int hi = n + 1;

			while (hi - lo > 1) {
				int mid = lo + (hi - lo) / 2;
				if (canWalkToNode(getPath(mid), root)) {
					lo = mid;
				} else {
					hi = mid;
				}
			}
			return lo;
		}

		/**
		 * Go left = false, go right = true.
		 */
		private List<Boolean> getPath(int mid) {
			List<Boolean> q = new LinkedList<>();
			int x = mid;
			while (x > 1) {
				q.add(0, x % 2 == 0 ? false : true);
				x /= 2;
			}
			return q;
		}

		private boolean canWalkToNode(List<Boolean> q, TreeNode root) {
			for (boolean i : q) {
				if (root == null) {
					return false;
				}
				if (root.left != null && i == false) {
					root = root.left;
				} else if (root.right != null && i == true) {
					root = root.right;
				} else {
					return false;
				}
			}
			return true;
		}

		private int getHeight(TreeNode root) {
			int c = -1;
			while (root != null) {
				c++;
				root = root.left;
			}
			return c;
		}
	}
}