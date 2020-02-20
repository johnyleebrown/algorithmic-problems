package tree.regular;

// 113
public class PathSumII
{
	// 100/100
	class Solution 
	{
		private List<List<Integer>> paths = new ArrayList<>();
		
		public List<List<Integer>> pathSum(TreeNode root, int sum) 
		{
			if (root == null) return paths;
			helper(new ArrayList<>(), root, sum);
			return paths;        
		}
		
		private void backtrack(int sum, TreeNode root, List<Integer> path)
		{
			if (root.left == null && root.right == null && sum == 0) 
			{
				paths.add(new ArrayList(path));    
			}
			else
			{
				helper(path, root.left, sum);
				helper(path, root.right, sum);
			}
		}

		private void helper(List<Integer> l, TreeNode root, int sum)
		{
			if (root != null)
			{
				l.add(root.val);
				backtrack(sum - root.val, root, l);
				l.remove(l.size() - 1);   
			}
		}
	}

}

/*
[5,4,8,11,null,13,4,7,2,null,null,5,1]
22
[1,2]
1
*/

