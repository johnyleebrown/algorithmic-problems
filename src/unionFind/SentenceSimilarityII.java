/*
 * 737
 * Google
 */
class Solution
{
	public boolean areSentencesSimilarTwo(String[] words1, String[] words2,
			List<List<String>> pairs)
	{
		if (words1.length != words2.length)
		{
			return false;
		}

		//save the relationship of child-parent, key is child and value is parent
		Map<String, String> parent = new HashMap<>();

		for (List<String> s : pairs) 
		{
			String p1 = findParent(s.get(0), parent);
			String p2 = findParent(s.get(1), parent);

			//if p1 doesn't equal to p2, we need setup relationship between them.
			//make one as parent of the other. Here I make p2 as parent of p1.
			if (!p1.equals(p2)) 
			{
				parent.put(p1, p2);
			}
		}

		int len = words1.length;
		for (int i = 0; i < len; i++) 
		{
			String p1 = findParent(words1[i], parent);
			String p2 = findParent(words2[i], parent);

			//If no relationship found for p1 and p2, that means they're not similar word.
			if (!p1.equals(p2)) 
			{
				return false;
			}
		}

		return true;
	}

	//Find the very top parent of s. If no parent found for s, return s itself.
	String findParent(String s, Map<String, String> parent) 
	{
		if (parent.containsKey(s)) 
		{
			return findParent(parent.get(s), parent);
		}

		return s;
	}
}

