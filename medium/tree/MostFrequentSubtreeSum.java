// 508
public class MostFrequentSubtreeSum
{
	class Solution 
	{
		private Map<Integer, Integer> map = new HashMap<>();
		private int max = Integer.MIN_VALUE;
		
		public int[] findFrequentTreeSum(TreeNode root) 
		{
			if (root == null) return new int[]{};
			count(root);
			
			List<Integer> res = new ArrayList<>();
			for (int key: map.keySet())
			{
				if (map.get(key) == max) res.add(key); 
			}
			
			int[] arr = new int[res.size()];
			for (int i = 0; i < res.size(); i++) arr[i] = res.get(i);
			return arr;
		}
		
		private int count(TreeNode root)
		{
			if (root == null) return 0;
			int sum = root.val + count(root.left) + count(root.right);
			int c = map.getOrDefault(sum, 0) + 1;
			max = Math.max(max, c);
			map.put(sum, c);
			return sum;
		}
		
	}

}

