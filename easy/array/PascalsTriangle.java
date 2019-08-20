package Easy.Array;

import java.util.ArrayList;
import java.util.List;

// 118
public class PascalsTriangle 
{
    public class Solution 
	{
		private List<List<Integer>> ans;

        public List<List<Integer>> generate(int numRows) 
		{
			ans = new ArrayList<>();
			if (numRows < 1) return ans;
			ans.add(helper(numRows));
			return ans;
        }

		private List<Integer> helper(int h)
		{
			if (h == 1) return Arrays.asList(1);
			List<Integer> prev = helper(h - 1); ans.add(prev);
			List<Integer> t = new ArrayList<>(); t.add(1);
			for (int i = 0; i < prev.size() - 1; i++)
				t.add(prev.get(i) + prev.get(i + 1));	
			t.add(1);
			return t;
		}
    }
}

