package medium.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import Helpers.Helper;
import util.TreeNode;

// 222
public class CountCompleteTreeNodes 
{
	class Solution 
	{
		public int countNodes(TreeNode root) 
		{
			if (root == null) return 0;
			return 1 + countNodes(root.left) + countNodes(root.right);
		}
	}
}

