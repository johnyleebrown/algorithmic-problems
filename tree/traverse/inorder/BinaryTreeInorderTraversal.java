package Medium.Tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import util.TreeNode;

// 94
public class BinaryTreeInorderTraversal 
{
    class Solution 
	{
        public List<Integer> inorderTraversal(TreeNode root) 
		{
            List<Integer> res = new ArrayList<>();
            Stack<TreeNode> stack = new Stack<>();
            TreeNode curr = root;
            
			while (curr != null || !stack.isEmpty()) 
			{
                while (curr != null) 
				{
                    stack.push(curr);
                    curr = curr.left;
                }
                curr = stack.pop();
                res.add(curr.val);
                curr = curr.right;
            }

            return res;
        }
    }
}

