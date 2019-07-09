package Medium.Tree;

import java.util.LinkedList;
import java.util.Queue;

import util.TreeNode;

/**
 * 513
 * Given a binary tree, find the leftmost value in the last row of the tree.
 */
public class FindBottomLeftTreeValue {

    public class Solution {
        public int findBottomLeftValue(TreeNode root) {
            return findBottomLeftValue(root, 1, new int[]{0, 0});
        }

        public int findBottomLeftValue(TreeNode root, int depth, int[] res) {
            if (res[1] < depth) {
                res[0] = root.val;
                res[1] = depth;
            }
            if (root.left != null) findBottomLeftValue(root.left, depth + 1, res);
            if (root.right != null) findBottomLeftValue(root.right, depth + 1, res);
            return res[0];
        }
    }

    // O(n), O(n)
    class Solution2 {
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
