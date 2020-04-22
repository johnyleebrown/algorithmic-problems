package design.iterator;

import util.ds.InputReader;

import java.util.concurrent.Semaphore;

/**
 * 173
 */
public class BinarySearchTreeIterator {
    public static class Solution2 {
        public static class BSTIterator {
            TreeNodeCustom next;
            Semaphore ss = new Semaphore(1);
            TreeNodeCustom prev;

            public BSTIterator(TreeNodeCustom root) {
                next = addLefts(root);
                prev = null;
            }

            public int next() throws InterruptedException {
                int ret = next.val;
                System.out.println(next.prev == null ? null : next.prev.val);
                prev = next.prev;
                if (next.right != null && next.next == null)
                    next = addLefts(next.right);
                else
                    next = next.next;
                return ret;
            }

            private void p() {

            }

            private TreeNodeCustom addLefts(TreeNodeCustom cur) {
                TreeNodeCustom x = cur;
                while (x.left != null) {
//                    System.out.println(x.val);
                    if (x.prev == null) x.prev = x.left;
                    x.left.next = x;
                    x = x.left;
                }
//                System.out.println(x.val);
                return x;
            }

            public boolean hasNext() {
                return next != null;
            }

            public int previous() {
                int ret = prev.val;
                next = prev.next;
                prev = prev.prev;
                return ret;
            }

            public boolean hasPrevious() {
                return prev != null;
            }
        }
    }

    /**
     * TreeNode with a link to prev.
     */
    public static class TreeNodeCustom {
        TreeNodeCustom prev, next;
        TreeNodeCustom left, right;
        int val;

        public TreeNodeCustom(int val) {
            this.val = val;
        }
    }

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
//            int y = in.nextInt();
            if (x == 1) {
                System.out.print("next: ");
                // DO_THING_1
                System.out.println(it.next());
            } else if (x == 2) {
                System.out.print("has next: ");
                // DO_THING_2
                System.out.println(it.hasNext());
            } else if (x == 3) {
                System.out.print("prev: ");
                // DO_THING_3
                System.out.println(it.previous());
            } else if (x == 4) {
                System.out.print("has prev: ");
                // DO_THING_4
                System.out.println(it.hasPrevious());
            } else return;
        }
    }
}