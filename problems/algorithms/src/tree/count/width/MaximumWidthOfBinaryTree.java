package tree.count.width;

import util.ds.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 662
 */
public class MaximumWidthOfBinaryTree {
	// count max row length at each row, remove nulls at the row start and the end 
	class Solution {
		public int widthOfBinaryTree(TreeNode root) {
			if (root == null) return 0;
			Deque<TreeNode> q = new ArrayDeque<>();
			q.add(root);
			int max = 0;

			while (!q.isEmpty()) {
				int count = 0;
				max = Math.max(max, q.size());

				int size = q.size();
				while (count < size) {
					TreeNode node = q.pollFirst();
					if (node == null) {
						q.add(null);
						q.add(null);
					}
					else {
						q.add(node.left);
						q.add(node.right);
					}
					count++;
				}
				while (!q.isEmpty() && q.peekFirst() == null) q.pollFirst();
				while (!q.isEmpty() && q.peekLast() == null) q.pollLast();
			}

			return max;
		}
	}
}

