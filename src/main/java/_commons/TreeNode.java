package _commons;

import java.util.*;

public class TreeNode {
	public int val;
	public TreeNode left, right, next, parent;
	public boolean someFlag;

	public TreeNode(int val) {
		this.val = val;
	}

	public TreeNode(boolean f) {
		someFlag = f;
	}

	public TreeNode(String s) {
		TreeNode newTree = buildFromStringArray(s);
		if (newTree == null)
			return;
		this.val = newTree.val;
		this.left = newTree.left;
		this.right = newTree.right;
	}

	/**
	 * Supposedly balanced tree as input, hence base * 2 - size of the level.
	 */
	public static TreeNode buildFromStringArray(String s) {
		String[] sAr = s.split(",");
		int n = sAr.length;
		if (n == 1) return createNode(sAr, 0);

		double base = 0.5;
		List<TreeNode> q = new ArrayList<>();
		TreeNode root = null;

		for (int i = 0; i < n; ) {
			base *= 2;
			double size = base;

			while (--size >= 0) {
				if (q.isEmpty()) {
					root = createNode(sAr, i++);
					q.add(root);
				} else {
					TreeNode prev = q.remove(0);
					if (prev == null) continue;

					if (i >= n) return root;
					prev.left = createNode(sAr, i++);
					q.add(prev.left);

					if (i >= n) return root;
					prev.right = createNode(sAr, i++);
					q.add(prev.right);
				}
			}
		}

		return root;
	}

	private static TreeNode createNode(String[] a, int i) {
		String s = a[i];
		try {
			int curNum = Integer.parseInt(s);
			return new TreeNode(curNum);
		} catch (NumberFormatException e) {
			return null;
		}
	}

	public static Map<Integer, Integer> collectValues(TreeNode root) {
		Map<Integer, Integer> valuesToCountsMap = new HashMap<>();
		traverse(root, valuesToCountsMap);
		return valuesToCountsMap;
	}

	private static void traverse(TreeNode root, Map<Integer, Integer> valuesToCountsMap) {
		if (root == null) return;
		valuesToCountsMap.put(root.val, valuesToCountsMap.getOrDefault(root.val, 0) + 1);
		traverse(root.left, valuesToCountsMap);
		traverse(root.right, valuesToCountsMap);
	}

	public void printTree() {
		List<String> res = new ArrayList<>();
		toStringPreOrder(this, res);
		System.out.println(res);
	}

	private void toStringPreOrder(TreeNode root, List<String> res) {
		if (root == null) {
			res.add("null");
			return;
		}
		res.add(String.valueOf(root.val));
		toStringPreOrder(root.left, res);
		toStringPreOrder(root.right, res);
	}

	public void printTreeLevelOrder() {
		toStringLevelOrder(this);
	}

	private void toStringLevelOrder(TreeNode root) {
		List<TreeNode> q = new ArrayList<>();
		q.add(root);

		while (!q.isEmpty()) {
			List<String> local = new ArrayList<>();
			int size = q.size();
			boolean noNullsAtLevelFlag = false;
			while (--size >= 0) {
				TreeNode cur = q.remove(0);
				if (cur != null)
					noNullsAtLevelFlag = true;
				local.add(cur == null ? "null" : String.valueOf(cur.val));
				if (cur != null) {
					q.add(cur.left);
					q.add(cur.right);
				}
			}
			if (noNullsAtLevelFlag)
				System.out.println(local);
		}
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof TreeNode)) return false;
		TreeNode treeNode = (TreeNode) o;
		return val == treeNode.val &&
		       Objects.equals(left, treeNode.left) &&
		       Objects.equals(right, treeNode.right);
	}

	@Override
	public int hashCode() {
		return Objects.hash(val, left, right);
	}
}
