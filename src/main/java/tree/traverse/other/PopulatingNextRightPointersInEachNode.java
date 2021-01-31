package tree.traverse.other;

import commons.Node;

/**
 * 116
 */
public class PopulatingNextRightPointersInEachNode {
    /**
     * Draw before implementing so, we will go from left to right going through
     * next links while doing so we will set the next links in the children
     * there are only 2 cases, either left.next = right; or right.next =
     * nextRoot.left
     */
    public static class Solution {
        public Node connect(Node root) {
            if (root == null) return null;
            Node cur = root;
            while (cur.left != null) {
                Node next = cur.left;
                while (cur != null) {
                    cur.left.next = cur.right;
                    if (cur.next != null) {
                        cur.right.next = cur.next.left;
                    }
                    cur = cur.next;
                }
                cur = next;
            }
            return root;
        }
    }
}