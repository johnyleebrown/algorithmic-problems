package tree.count.path;

import commons.TreeNode;

/**
 * 742
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree where every node has a unique value, and a target key k, find the value of
 * the nearest leaf node to target k in the tree.
 *
 * Here, nearest to a leaf means the least number of edges travelled on the binary tree to reach any
 * leaf of the tree. Also, a node is called a leaf if it has no children.
 *
 * In the following examples, the input tree is represented in flattened form row by row. The actual
 * root tree given will be a TreeNode object.
 *
 * ======
 *
 * Source: Leetcode
 */
public class ClosestLeafInABinaryTree {
	/**
	 * Find k, find closest on left and right, and every step we take back search for closest in
	 * subtrees as well.
	 * Every step back in ElementAndDist we keep the dist from the target to the node we came
	 * back to.
	 */
	public static class Solution {
		private static final int max = 1_000_000_007;

		public int findClosestLeaf(TreeNode cur, int k) {
			return findClosestLeafToK(cur, k).element.val;
		}

		private ElementAndDist findClosestLeafToK(TreeNode cur, int k) {
			if (cur == null) {
				return null;
			}
			if (cur.val == k) {
				return new ElementAndDist(getClosestLeaf(cur, 0), 1);
			}
			ElementAndDist targetFromLeft = findClosestLeafToK(cur.left, k);
			if (targetFromLeft != null) {
				return compareClosest(cur.right, targetFromLeft);
			}
			ElementAndDist targetFromRight = findClosestLeafToK(cur.right, k);
			if (targetFromRight != null) {
				return compareClosest(cur.left, targetFromRight);
			}
			return null;
		}

		private ElementAndDist compareClosest(TreeNode node, ElementAndDist target) {
			Element closest = getClosestLeaf(node, 1);
			closest.distTo += target.dist;
			target.dist += 1;
			if (closest.distTo < target.element.distTo) {
				return new ElementAndDist(closest, target.dist);
			} else {
				return target;
			}
		}

		private Element getClosestLeaf(TreeNode c, int dist) {
			if (c == null) {
				return new Element(max, 0);
			}
			if (c.left == null && c.right == null) {
				return new Element(dist, c.val);
			}
			Element l = getClosestLeaf(c.left, dist + 1);
			Element r = getClosestLeaf(c.right, dist + 1);
			return l.distTo < r.distTo ? l : r;
		}

		private static class ElementAndDist {
			Element element; // closest element to target
			int dist; // dist to target

			ElementAndDist(Element e, int d) {
				element = e;
				dist = d;
			}
		}

		private static class Element {
			int distTo; // dist to node
			int val; // node value

			Element(int d, int v) {
				distTo = d;
				val = v;
			}
		}
	}
}