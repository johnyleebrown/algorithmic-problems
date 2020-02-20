package tree.regular;

import util.TreeNode;

import java.util.*;

/**
 * 653
 * Given a Binary Search Tree and a target number, return true if there exist two elements
 * in the BST such that their sum is equal to the given target.
 */
public class TwoSumIV {

    // O(n), O(n)
    class Solution {
        HashSet set = new HashSet();

        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false;
            if (set.contains(k - root.val)) return true;
            set.add(root.val);
            return findTarget(root.left, k) || findTarget(root.right, k);
        }
    }

    // BFS: O(n), O(n)
    class Solution2 {
        public boolean findTarget(TreeNode root, int k) {
            Set< Integer > set = new HashSet();
            Queue< TreeNode > queue = new LinkedList();
            queue.add(root);
            while (!queue.isEmpty()) {
                if (queue.peek() != null) {
                    TreeNode node = queue.remove();
                    if (set.contains(k - node.val)) return true;
                    set.add(node.val);
                    queue.add(node.right);
                    queue.add(node.left);
                } else queue.remove();
            }
            return false;
        }
    }

    // 2 pointers, O(n), O(n)
    class Solution3 {
        public boolean findTarget(TreeNode root, int k) {
            List< Integer > list = new ArrayList();
            inorder(root, list);
            int l = 0, r = list.size() - 1;
            while (l < r) {
                int sum = list.get(l) + list.get(r);
                if (sum == k) return true;
                if (sum < k) l++;
                else r--;
            }
            return false;
        }
        public void inorder(TreeNode root, List < Integer > list) {
            if (root == null) return;
            inorder(root.left, list);
            list.add(root.val);
            inorder(root.right, list);
        }
    }
}
