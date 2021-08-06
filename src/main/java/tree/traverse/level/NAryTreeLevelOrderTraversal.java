package tree.traverse.level;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 429. N-ary Tree Level Order Traversal
 * https://leetcode.com/problems/n-ary-tree-level-order-traversal/
 */
public class NAryTreeLevelOrderTraversal {
	public static class Solution {
		public List<List<Integer>> levelOrder(Node root) {
			List<List<Integer>> ans = new ArrayList<>();
			if (root == null) return ans;
			Deque<Node> q = new ArrayDeque<>();
			q.addLast(root);
			while (!q.isEmpty()) {
				int size = q.size();
				List<Integer> l = new ArrayList<>();
				while (--size >= 0) {
					Node cur = q.removeFirst();
					l.add(cur.val);
					for (Node ch : cur.children) {
						q.addLast(ch);
					}
				}
				ans.add(l);
			}
			return ans;
		}

		public static class Node {
			public int val;
			public List<Node> children;

			public Node() {
			}

			public Node(int _val) {
				val = _val;
			}

			public Node(int _val, List<Node> _children) {
				val = _val;
				children = _children;
			}
		}
	}
}
