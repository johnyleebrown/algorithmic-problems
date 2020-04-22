package design.iterator;

import util.ds.InputReader;
import util.ds.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 173
 */
public class BinarySearchTreeIterator {
    public static void main(String[] args) throws InterruptedException {
        TreeNodeCustom t1 = new TreeNodeCustom(7);
        TreeNodeCustom t1l = new TreeNodeCustom(3);
        TreeNodeCustom t1r = new TreeNodeCustom(15);
        t1.left = t1l;
        t1.right = t1r;
        TreeNodeCustom t3l = new TreeNodeCustom(9);
        TreeNodeCustom t3r = new TreeNodeCustom(20);
        t1r.left = t3l;
        t1r.right = t3r;
        Solution2.BSTIterator it = new Solution2.BSTIterator(t1);
        InputReader in = new InputReader(System.in);
        while (true) {
            int x = in.nextInt();
            if (x == 1) {
                System.out.print("next: ");
                System.out.println(it.next());
            } else if (x == 2) {
                System.out.print("has next: ");
                System.out.println(it.hasNext());
            } else if (x == 3) {
                System.out.print("prev: ");
                System.out.println(it.previous());
            } else if (x == 4) {
                System.out.print("has prev: ");
                System.out.println(it.hasPrevious());
            } else return;
        }
    }

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

            public BSTIterator(TreeNodeCustom root) {
                st = new ArrayDeque<>();
                addLefts(root);
                cur = st.removeLast();
            }

            private void addLefts(TreeNodeCustom cur) {
                while (cur != null) {
                    st.add(cur);
                    cur = cur.left;
                }
            }

            public int next() {
                if (cur.next == null) {
                    addLefts(cur.right);
                    cur.next = st.removeLast();
                }

                TreeNodeCustom next = cur.next;
                next.prev = cur;
                cur = next;
                return next.val;
            }

            public boolean hasNext() {
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