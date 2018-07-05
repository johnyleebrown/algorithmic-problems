package Medium.Tree;

import Helpers.TreeNode;

/**
 * 866
 *
 * Given a binary tree rooted at root, the depth of each node is the shortest distance to the root.
 * A node is deepest if it has the largest depth possible among any node in the entire tree.
 * The subtree of a node is that node, plus the set of all descendants of that node.
 * Return the node with the largest depth such that it contains all the deepest nodes in it's subtree.
 *
 * Input: [3,5,1,6,2,0,8,null,null,7,4]
 * Output: [2,7,4]
 *
 * The values of each node are unique.
 *
 */
public class SmallestSubtreeWithAllTheDeepestNodes {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    public TreeNode subtreeWithAllDeepest(TreeNode root) {
        return helper(root).root;
    }

    // in order traversal
    // int[]{ height, deepest node, actual parent }
    IntAndTreeNode helper(TreeNode root) {
        if (root == null) return new IntAndTreeNode(-1, root);

        IntAndTreeNode leftParams = helper(root.left);
        IntAndTreeNode rightParams = helper(root.right);

        if (leftParams.height > rightParams.height){
            return new IntAndTreeNode(leftParams.height + 1, leftParams.root);
        } else if (rightParams.height > leftParams.height) {
            return new IntAndTreeNode(rightParams.height + 1, rightParams.root);
        } else {
            return new IntAndTreeNode(leftParams.height + 1, root);
        }
    }

    class IntAndTreeNode {
        int height;
        TreeNode root;

        public IntAndTreeNode(int height, TreeNode root) {
            this.height = height;
            this.root = root;
        }
    }
}
