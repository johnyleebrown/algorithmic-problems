package Easy.Tree;

import java.util.HashSet;

import Helpers.TreeNode;

/**
 * 653
 * Given a Binary Search Tree and a target number, return true if there exist two elements
 * in the BST such that their sum is equal to the given target.
 */
public class TwoSumIV {


    class Solution {
        HashSet set = new HashSet();

        public boolean findTarget(TreeNode root, int k) {
            if (root == null) return false;
            if (set.contains(k - root.val)) return true;
            else set.add(root.val);
            return findTarget(root.left, k) || findTarget(root.right, k);
        }
    }
}
