package Easy.Tree;

import java.util.LinkedList;
import java.util.Queue;

import util.TreeNode;

/**
 * 226
 */
public class InvertBinaryTree {
    class Solution {
        public TreeNode invertTree(TreeNode root) {
            if (root == null) return null;
            TreeNode x = root.left;
            root.left = invertTree(root.right);
            root.right = invertTree(x);
            return root;
        }
    }

    public class Solution2 {
        public TreeNode invertTree(TreeNode root) {

            if (root == null) {
                return null;
            }

            final Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);

            while(!queue.isEmpty()) {
                final TreeNode node = queue.poll();
                final TreeNode left = node.left;
                node.left = node.right;
                node.right = left;

                if(node.left != null) {
                    queue.offer(node.left);
                }
                if(node.right != null) {
                    queue.offer(node.right);
                }
            }
            return root;
        }
    }
}
