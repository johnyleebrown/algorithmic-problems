package design.iterator;

import _commons.TreeNode;

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

    /**
     * Use prev and next links, otherwise use stack to get next items like in
     * prev solution.
     *
     * First 3 lines:
     * TreeNodeCustom next = cur.next;
     * cur = next;
     * return next.val;
     *
     * Then expand it.
     */
    public static class Solution2 {
        public static class BSTIterator {
            private TreeNodeCustom cur;
            private Deque<TreeNodeCustom> st;
            private TreeNodeCustom root;

            public BSTIterator(TreeNodeCustom root) {
                st = new ArrayDeque<>();
                this.root = root;
            }

            public int next() {
                if (cur == null) {
                    addLefts(root);
                    TreeNodeCustom next = st.removeLast();
                    cur = next;
                    return next.val;
                }

                if (cur.next == null) {
                    addLefts(cur.right);
                    cur.next = st.removeLast();
                }

                TreeNodeCustom next = cur.next;
                next.prev = cur;
                cur = next;
                return next.val;
            }

            private void addLefts(TreeNodeCustom node) {
                while (node != null) {
                    st.add(node);
                    node = node.left;
                }
            }

            public boolean hasNext() {
                if (cur == null) {
                    return root != null;
                }
                if (cur.next == null) {
                    return !st.isEmpty() || cur.right != null;
                }
                return true;
            }

            public int previous() {
                TreeNodeCustom prev = cur.prev;
                cur = prev;
                return prev.val;
            }

            public boolean hasPrevious() {
                return cur.prev != null;
            }
        }
    }

    /**
     * Regular TreeNode with a link to prev and next.
     */
    public static class TreeNodeCustom {
        TreeNodeCustom prev, next;
        TreeNodeCustom left, right;
        int val;

        public TreeNodeCustom(int val) {
            this.val = val;
        }
    }
}