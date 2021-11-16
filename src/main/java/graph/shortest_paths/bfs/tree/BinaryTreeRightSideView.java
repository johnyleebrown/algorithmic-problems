package graph.shortest_paths.bfs.tree;

import commons.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 199
 */
public class BinaryTreeRightSideView {

  /**
   * Level order traversal but we insert to the list only the first element of
   * the level if it is not null.
   */
  public static class Solution1 {

    public List<Integer> rightSideView(TreeNode root) {
      List<Integer> resultList = new LinkedList<>();
      if (root == null) {
        return resultList;
      }

      Queue<TreeNode> q = new LinkedList<>();
      q.add(root);

      while (!q.isEmpty()) {
        resultList.add(q.peek().val);

        int size = q.size();
        while (--size >= 0) {
          TreeNode node = q.poll();
          if (node == null) {
            continue;
          }

          if (node.right != null) {
            q.add(node.right);
          }
          if (node.left != null) {
            q.add(node.left);
          }
        }
      }

      return resultList;
    }
  }

  /**
   * @trick [bfs with dfs] this logic works because we go first to the right
   * child it means these nodes that we will visit coming from the right child
   * are more important, so they will be first to witness a condition when level
   * == ans.size
   *
   * the other way of doing this would be adding answer at positions:
   * map.put(level, root.val), then converting this to the list
   */
  public static class Solution2 {

    public List<Integer> rightSideView(TreeNode root) {
      List<Integer> ans = new ArrayList<>();
      rightSideViewHelper(root, ans, true, 1);
      return ans;
    }

    private void rightSideViewHelper(TreeNode root, List<Integer> ans,
        boolean shouldAdd, int level) {
      if (root == null) {
        return;
      }
      if (shouldAdd && ans.size() + 1 == level) {
        ans.add(root.val);
      }
      boolean hasRightChild = root.right != null;
      rightSideViewHelper(root.right, ans, true, level + 1);
      rightSideViewHelper(root.left, ans, !hasRightChild, level + 1);
    }
  }
}
