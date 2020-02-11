package medium.tree;

// 117
public class PopulatingNextRightPointersInEachNodeII
{
	class Solution 
	{
		public Node connect(Node root) 
		{
			if (root == null) return null;
			Node returnNode = root;
			Node start = root;
			Node prev = null;
			
			while (root != null)
			{
				if (root.left != null)
				{
					if (start == null) 
					{
						start = root.left;
					}
					if (prev != null) 
					{
						prev.next = root.left;
					}

					prev = root.left;
				}

				if (root.right != null)
				{
					if (start == null) 
					{
						start = root.right;
					}
					if (prev != null) 
					{
						prev.next = root.right;
					}

					prev = root.right;
				}

				root = root.next;
				if (root == null)
				{
					prev = null;
					root = start;
					start = null;
				}
			}
				
			return returnNode;
		}
	}

}

