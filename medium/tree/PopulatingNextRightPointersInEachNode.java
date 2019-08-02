package medium.tree;

// 116
public class PopulatingNextRightPointersInEachNode
{
	// draw defore implementing
	// so, we will go from left to right going through next links
	// while doing so we will set the next links in the children
	// there are only 2 cases, either left.next = right; or right.next = nextRoot.left
	class Solution 
	{
		public Node connect(Node root) 
		{
			if (root == null) return null;
			Node returnNode = root;
			Node start = null;
			
			while (root.left != null)
			{
				start = root.left;
				
				while (root != null)
				{
					root.left.next = root.right;
					if (root.next != null) root.right.next = root.next.left;
					root = root.next;
				}
				
				root = start;
			}
        
			return returnNode;
		}
	}

}

