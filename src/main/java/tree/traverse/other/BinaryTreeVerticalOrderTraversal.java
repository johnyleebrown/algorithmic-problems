package tree.traverse.other;

import commons.TreeNode;

import java.util.*;

/**
 * 314
 *
 * ======
 *
 * Given a binary tree, return the vertical order traversal of its nodes' values. (ie, from top to
 * bottom, column by column).
 *
 * If two nodes are in the same row and column, the order should be from left to right.
 */
public class BinaryTreeVerticalOrderTraversal {
	/**
	 * BFS - fastest.
	 */
	public static class Solution {
		public List<List<Integer>> verticalOrder(TreeNode r) {
			List<List<Integer>> ans = new ArrayList<>();
			if (r == null) return ans;
			List<Item> q = new ArrayList<>();
			q.add(new Item(r, 0, 0));
			Map<Integer, List<Integer>> m = new HashMap<>();
			int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
			while (!q.isEmpty()) {
				int s = q.size();
				while (--s >= 0) {
					Item cur = q.remove(0);
					min = Math.min(min, cur.x);
					max = Math.max(max, cur.x);
					m.putIfAbsent(cur.x, new ArrayList<>());
					m.get(cur.x).add(cur.node.val);
					if (cur.node.left != null) {
						Item child = new Item(cur.node.left, cur.x - 1, cur.y + 1);
						q.add(child);
					}
					if (cur.node.right != null) {
						Item child = new Item(cur.node.right, cur.x + 1, cur.y + 1);
						q.add(child);
					}
				}
			}
			for (int i = min; i <= max; i++) {
				ans.add(m.get(i));
			}
			return ans;
		}

		class Item {
			TreeNode node;
			int x, y;

			Item(TreeNode n, int xx, int yy) {
				node = n;
				x = xx;
				y = yy;
			}
		}
	}

	/**
	 * With binary search find position to insert in list, offset by 0.1 items in the same row
	 * and column, since we know that nodes from left have priority over right ones, we just
	 * increment offset gradually.
	 */
	public static class Solution2 {
		public List<List<Integer>> verticalOrder(TreeNode r) {
			List<List<Integer>> ans = new ArrayList<>();
			if (r == null) return ans;
			Map<Integer, List<Pair>> m = new TreeMap<>();
			f(m, r, 0, 0);
			for (int k : m.keySet()) {
				ans.add(new ArrayList<>());
				for (Pair p : m.get(k)) {
					ans.get(ans.size() - 1).add(p.val);
				}
			}
			return ans;
		}

		private void f(Map<Integer, List<Pair>> m, TreeNode r, int x, double y) {
			if (r == null) return;
			m.putIfAbsent(x, new ArrayList<>());

			int pos = bs(m.get(x), y);
			double offset = 0;
			while (pos < m.get(x).size() && m.get(x).get(pos).lvl < y + 1) {
				pos++;
				offset += 0.1;
			}
			m.get(x).add(pos, new Pair(r.val, y + offset));

			f(m, r.left, x - 1, y + 1);
			f(m, r.right, x + 1, y + 1);
		}

		class Pair {
			int val;
			double lvl;

			Pair(int v, double l) {
				val = v;
				lvl = l;
			}
		}

		int bs(List<Pair> p, double x) {
			int lo = 0;
			int hi = p.size() - 1;
			while (hi - lo >= 0) {
				int mid = lo + (hi - lo) / 2;
				if (p.get(mid).lvl == x) {
					return mid;
				}
				if (p.get(mid).lvl <= x) {
					lo = mid + 1;
				} else {
					hi = mid - 1;
				}
			}
			return lo;
		}
	}

	/**
	 * TreeMap and PriorityQueues.
	 */
	public static class Solution3 {
		public List<List<Integer>> verticalOrder(TreeNode r) {
			List<List<Integer>> ans = new ArrayList<>();
			if (r == null) return ans;
			Map<Integer, PriorityQueue<Pair>> m = new TreeMap<>();
			f(m, r, 0, 0);
			for (int k : m.keySet()) {
				ans.add(new ArrayList<>());
				while (!m.get(k).isEmpty()) {
					Pair p = m.get(k).poll();
					ans.get(ans.size() - 1).add(p.val);
				}
			}
			return ans;
		}

		void f(Map<Integer, PriorityQueue<Pair>> m, TreeNode r, int x, int y) {
			if (r == null) return;
			m.putIfAbsent(x, createPq());
			m.get(x).add(new Pair(r.val, y, m.get(x).size()));
			f(m, r.left, x - 1, y + 1);
			f(m, r.right, x + 1, y + 1);
		}

		class Pair {
			int val, lvl;
			int left;

			Pair(int v, int l, int le) {
				val = v;
				lvl = l;
				left = le;
			}
		}

		PriorityQueue<Pair> createPq() {
			return new PriorityQueue<>((a, b) -> {
				if (a.lvl == b.lvl) {
					return a.left - b.left;
				}
				return a.lvl - b.lvl;
			});
		}
	}
}