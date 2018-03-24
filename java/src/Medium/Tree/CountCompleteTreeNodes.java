package Medium.Tree;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import Helpers.TreeNode;

/**
 * 222
 *
 * Given a complete binary tree, count the number of nodes.
 */
public class CountCompleteTreeNodes {

    class Solution0 {
        private int dfs(TreeNode x, int count, Set<TreeNode> set) {
            if (x == null) return count;
            int left = dfs(x.left, count, set);
            int right = dfs(x.right, count, set);
            return count + left + right + 1;
        }
    }

    /**
     * Binary search
     * 2*h + 1 <= n <= 2^(h + 1) - 1
     * Time complexity: O()
     * Space complexity: O()
     */
    class Solution1 {
        public int countNodes(TreeNode root) {
            if (root == null) return 0;

            int total = 0, h = getHeight(root);

            while (h > 0) {
                int right = getHeight(root.right);

                total += (1 << right);

                if (right == h - 1) root = root.right;
                else root = root.left;

                h--;
            }

            return total;
        }

        private int getHeight(TreeNode root) {
            if (root == null) return 0;
            return 1 + getHeight(root.left);
        }
    }

    class Solution2 {
        public int countNodes(TreeNode root) {
            if (root == null) return 0;

            Queue<TreeNode> q = new LinkedList<>();
            q.add(root);
            int count = 1;

            while (!q.isEmpty()) {
                TreeNode temp = q.poll();

                if (temp.val != -100) {
                    temp.val = -100;

                    if (temp.left != null) {
                        q.offer(temp.left);
                        count++;
                    }

                    if (temp.right != null) {
                        q.offer(temp.right);
                        count++;
                    }
                }
            }

            return count;
        }
    }
}
