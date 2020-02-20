package string.regular;

public class SerializeAndDeserializeBinaryTree
{
	public class Codec 
	{
		public String serialize(TreeNode root) 
		{
			String data = "";
			String nl = "n", cm = ",";
			
			if (root == null) return nl;
			
			Queue<TreeNode> q = new LinkedList<>();
			q.add(root);
			
			while (!q.isEmpty())
			{
				int size = q.size();
				while (--size >= 0)
				{
					TreeNode x = q.poll();
					
					if (x == null) data += nl + cm;
					else data += x.val + cm;
					
					if (x != null)
					{
						if (x.left != null) q.add(x.left);
						else q.add(null);

						if (x.right != null) q.add(x.right);
						else q.add(null);
					}
				}
			}
			
			return data;
		}

		public TreeNode deserialize(String data) 
		{
			if (data.isEmpty()) return null;
			
			String[] arr = data.split(",");
			if (arr[0].equals("n")) return null;
			
			int i = 0;
			TreeNode rootToReturn = new TreeNode(Integer.parseInt(arr[i]));
			 
			Queue<TreeNode> q = new LinkedList<>();
			q.add(rootToReturn);
			
			while (!q.isEmpty())
			{
				TreeNode root = q.poll();
				if (root == null) continue;
				
				if (i + 1 >= arr.length) break;
				
				i++;
				
				if (arr[i].length() == 1 && !Character.isDigit(arr[i].charAt(0))) root.left = null;
				else 
				{
					root.left = new TreeNode(Integer.parseInt(arr[i]));
					q.add(root.left);
				}
				
				if (i + 1 >= arr.length) break;
				
				i++;
				
				if (!Character.isDigit(arr[i].charAt(0))) root.right = null;
				else 
				{
					root.right = new TreeNode(Integer.parseInt(arr[i]));
					q.add(root.right);
				}
			}
			
			return rootToReturn;
		}

	}
}

