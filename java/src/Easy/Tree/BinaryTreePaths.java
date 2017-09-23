package Easy.Tree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 257
 * Given a binary tree, return all root-to-leaf paths.
 * ["1->2->5", "1->3"]
 */
public class BinaryTreePaths {

    private class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) { val = x; }
    }

    // Solution 1
    List<String> list = new LinkedList<>();
    public List<String> binaryTreePaths(TreeNode root) {
        if (root == null) return new LinkedList<>();
        binaryTreePathsHelper(root, "");
        return list;
    }

    public void binaryTreePathsHelper(TreeNode root, String str) {
        if (root.left == null && root.right == null) list.add(str + root.val);
        if (root.left != null) binaryTreePathsHelper(root.left, str + root.val + "->") ;
        if (root.right != null) binaryTreePathsHelper(root.right, str + root.val + "->") ;
    }

    // Solution 2
    public List<String> binaryTreePaths2(TreeNode root) {
        if (root == null) return new ArrayList<>();

        if (root.left == null &&  root.right == null) {
            List<String> temp = new ArrayList<>();
            temp.add(root.val + "");
            return temp;
        }

        List<String> left = binaryTreePaths(root.left);
        for (int i = 0; i < left.size(); i++) left.set(i, root.val + "->" + left.get(i));

        List<String> right = binaryTreePaths(root.right);
        for (int i = 0; i < right.size(); i++) right.set(i, root.val + "->" + right.get(i));

        if (left.isEmpty() || right.isEmpty())    return left.isEmpty() ? right : left;

        left.addAll(right);
        return left;
    }
}
