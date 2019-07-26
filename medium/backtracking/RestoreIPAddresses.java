package medium.backtracking;

/*
 * 93
 */
public class RestoreIPAddresses
{
	/*
	 * A valid IPV4 address is four integers ranging from zero to 255 and set apart by periods.
	 */
	class Solution {
		
		private List<String> combinations = new ArraysList<>();
		
		public List<String> restoreIpAddresses(String s) {
			if (s.length > 12) return combinations;   
			int[] nums = new int[s.length()];
			for (int i = 0; i < s.length(); i++) nums[i] = Integer.parseInt(s.charAt(i));
			generateCombinations(nums, 0, "", 0);
			return combinations;
		}
		
		private void generateCombinations(int[] nums, int i, String cur, int partLength)
		{
			// if (cur.length() > 3) continue;
			if (partLength > 3) return;
			
			// if (prev == '.' && cur > 2) continue;
			if (i != 0 && cur.charAt(i - 1) == '.' && nums[i] > 2) return;
			
			// if (prev != '.' && cur > 5) continue;
			if (i != 0 && cur.charAt(i - 1) != '.' && nums[i] > 5) return;
				
			if (nums.length == cur.length - 3) combinations.add(combination);
			else
			{
				// loop of variants = add '.' / add nums[i + 1]
				if (i != 0) 
				{
					generateCombinations(nums, i + 1, cur + '.', ?);        
				}
				generateCombinations(nums, i + 1, cur + nums[i + 1], ?);
			}
		}
		
	}

}

