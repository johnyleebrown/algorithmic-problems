package graph.shortest_paths.bfs.tree;

import _commons.TreeNode;

import java.util.*;

/**
 * 103
 */
public class BinaryTreeZigzagLevelOrderTraversal
{
    public List<List<Integer>> zigzagLevelOrder(TreeNode root)
    {
        List<List<Integer>> orderedTree = new ArrayList<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int treeHeight = 0;

        while (!queue.isEmpty())
        {
            List<Integer> level = new ArrayList<>();

            int size = queue.size();
            while (--size >= 0)
            {
                TreeNode node = queue.poll();

                if (node != null)
                {
                    level.add(node.val);
                    queue.add(node.left);
                    queue.add(node.right);
                }
            }

            if (!level.isEmpty())
            {
                if (treeHeight % 2 != 0) Collections.reverse(level);
                orderedTree.add(level);
            }

            treeHeight++;
        }

        return orderedTree;
    }
}
/*
[3,9,20,null,null,15,7]
[null]
[3]
[3,null,null]
[3,2,null]
[3,2,1]
[3,2,1,null,4,null,5]
*/