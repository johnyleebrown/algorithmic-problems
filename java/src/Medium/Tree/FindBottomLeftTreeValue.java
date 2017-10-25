package Medium.Tree;

import java.util.LinkedList;
import java.util.Queue;

import Helpers.TreeNode;

/**
 * 513
 * Given a binary tree, find the leftmost value in the last row of the tree.
 */
public class FindBottomLeftTreeValue {

    // O(n), O(n)
    class Solution {
        public int findBottomLeftValue(TreeNode root) {
            Queue<TreeNode> queue = new LinkedList<>();
            queue.add(root);
            int result = root.val;
            while (!queue.isEmpty()) {
                int count = queue.size();
                result = queue.peek().val;
                for (int i = 0; i < count; i++) {
                    TreeNode node = queue.poll();
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                }
            }
            return result;
        }
    }
}
