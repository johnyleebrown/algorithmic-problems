package graph.shortest_paths.bfs.tree;

import commons.TreeNode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199
 */
public class BinaryTreeRightSideView {
	/**
	 * Level order traversal but we insert to the list only
	 * the first element of the level if it is not null.
	 */
	class Solution {
		public List<Integer> rightSideView(TreeNode root) {
			List<Integer> resultList = new LinkedList<>();
			if (root == null) return resultList;

			Queue<TreeNode> q = new LinkedList<>();
			q.add(root);

			while (!q.isEmpty()) {
				resultList.add(q.peek().val);

				int size = q.size();
				while (--size >= 0) {
					TreeNode node = q.poll();
					if (node == null) continue;

					if (node.right != null) q.add(node.right);
					if (node.left != null) q.add(node.left);
				}
			}

			return resultList;
		}
	}
}

