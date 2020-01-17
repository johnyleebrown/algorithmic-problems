package bfs;

import util.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// 637
public class AverageOfLevelsInBinaryTree 
{
    // DFS
    class Solution 
	{
        public List<Double> averageOfLevels(TreeNode root) {
            int d = getDepth(root, 1);
            long[] sums = new long[d + 1];
            long[] nums = new long[d + 1];
            averageOfLevels(root, 1, sums, nums);
            List<Double> list = new ArrayList<>();
            for (int i = 1; i < sums.length; ++i) list.add(((double) sums[i]) / nums[i]);
            return list;
        }

        public void averageOfLevels(TreeNode root, int level, long[] sums, long[] nums) {
            if (root == null) return;
            sums[level] += root.val;
            nums[level]++;
            averageOfLevels(root.left, level + 1, sums, nums);
            averageOfLevels(root.right, level + 1, sums, nums);
        }

        private int getDepth(TreeNode n, int i) {
            if (n == null) return i - 1;
            return Math.max(getDepth(n.left, i + 1), getDepth(n.right, i + 1));
        }
    }

    // BFS
    class Solution2 {
        public List<Double> averageOfLevels(TreeNode root) {
            List<Double> res = new ArrayList<>();
            if (root == null) return res;
            Queue<TreeNode> queue = new LinkedList<>();
            queue.offer(root);
            while ( !queue.isEmpty() ) {
                int size = queue.size();
                double sum = 0.0;
                for (int i = 0; i < size; i ++) {
                    TreeNode node = queue.poll();
                    sum += node.val;
                    if (node.left != null) queue.offer(node.left);
                    if (node.right != null) queue.offer(node.right);
                }
                res.add(sum / size);
            }
            return res;
        }
    }
}
