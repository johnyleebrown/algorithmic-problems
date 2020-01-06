// 449
public class SerializeAndDeserializeBST
{
	public class Codec 
	{
		// using preorder to serialize
		public String serialize(TreeNode root) 
		{
			StringBuilder sb = new StringBuilder();
			serHelper(root, sb);
			return sb.toString();
		}
		
		private void serHelper(TreeNode root, StringBuilder sb)
		{
			if (root == null) sb.append("n" + "s");
			else
			{
				sb.append(root.val + "s");
				serHelper(root.left, sb);
				serHelper(root.right, sb);
			}
		}

		// same preorder here
		public TreeNode deserialize(String data) 
		{
			return desHelper(new LinkedList(Arrays.asList(data.split("s"))));
		}
		
		// 2|1|nn3|nn
		private TreeNode desHelper(Queue<String> q)
		{
			String s = q.poll();
			
			if ("n".equals(s)) return null;
			
			// get the node
			TreeNode root = new TreeNode(Integer.parseInt(s));
			
			// set it's left and right from recusrion result
			root.left = desHelper(q);
			root.right = desHelper(q);
			
			return root;
		}
    
	}
}

