package medium.bfs;

/*
 * 199 Binary Tree Right Side View
 */
public class BinaryTreeRightSideView
{
 	/* 
	 * Level order traversal but we insert to the list only the first element of the level if it is not null
	 */
	class Solution {
		public List<Integer> rightSideView(TreeNode root) 
		{
			List<Integer> resultList = new LinkedList<>();
			if (root == null) return resultList;
			
			Queue<TreeNode> queue = new LinkedList<>();
			queue.add(root);
			
			while (!queue.isEmpty())
			{
				resultList.add(q.peek().val);
				
				int size = queue.size();
				while (--size >= 0)
				{
					TreeNode node = queue.poll();
					if (node == null) continue;
					
					if (node.right != null) q.add(node.right);
					if (node.left != null) q.add(node.left); 
				}
			}

			return resultList;
		}
	}
}
