package graph.shortest_paths.bfs.tree;

import commons.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * BinaryTreeBottomView
 *
 * ======
 *
 * Task.
 *
 * Given a binary tree, return the bottom view of the tree as an array of
 * values.
 * If the horizontal position of a node is x, its left child node's horizontal
 * position would be x-1 and right child's horizontal position would be x+1.
 * If 2 nodes have the same horizontal position, the node on the lower level
 * will block the upper one in the view.
 * If 2 nodes on the same level have the same horizontal position, the node to
 * the right will block the one to the left in the view.
 * Example:
 * 1
 * /     \
 * 2       3
 * /    \ /     \
 * 4     5(7)    6
 * Input: [1, 2, 3, 4, 5, null, 6]
 * Output: [4, 2, 5, 3, 6]
 *
 * ======
 *
 * Source: Interview@Amazon
 */
public class BinaryTreeBottomView {
    /**
     * Assign coordinates to all nodes while traversing.
     * We assume that the latest traversed node would be on the right - meaning
     * it will override the prev node in that position, which was traversed
     * earlier.
     * Return all nodes that correspond to all unique coordinates.
     */
    public static class Solution {
        public List<Integer> solve(TreeNode root) {
            List<Integer> ans = new ArrayList<>();
            if (root == null) return ans;
            // coord-to-value
            TreeMap<Integer, Integer> tm = new TreeMap<>();
            List<CustomNode> q = new ArrayList<>();
            q.add(new CustomNode(0, root));
            while (!q.isEmpty()) {
                CustomNode cur = q.remove(0);
                tm.put(cur.coord, cur.node.val);
                if (cur.node.left != null)
                    q.add(new CustomNode(cur.coord - 1, cur.node.left));
                if (cur.node.right != null)
                    q.add(new CustomNode(cur.coord + 1, cur.node.right));
            }
            ans.addAll(tm.values());
            return ans;
        }

        static class CustomNode {
            int coord;
            TreeNode node;

            public CustomNode(int coord, TreeNode n) {
                this.coord = coord;
                this.node = n;
            }
        }
    }
}