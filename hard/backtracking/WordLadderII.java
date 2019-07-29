package hard.backtracking;

public class WordLadderII
{
	// wordSet = seen set, removing the word from set if we generated it
	// TLE
	class Solution 
	{
		private List<List<String>> paths = new ArrayList<>();
		
		public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) 
		{
			Set<String> wordSet = new HashSet<>();
			for (String s: wordList) wordSet.add(s);
			List<String> l = new ArrayList<>();
			l.add(beginWord);
			generatePaths(l, beginWord, endWord, wordSet);
			return paths;
		}
		
		private List<String> getVariants(Set<String> seen, String str)
		{
			List<String> strList = new ArrayList<>();
			StringBuilder sb = new StringBuilder(str);
			
			for (int i = 0; i < str.length(); i++)
			{
				for (int j = 0; j < 26; j++)
				{
					sb.setCharAt(i, (char)('a' + j));
					String newStr = sb.toString();
					if (!seen.remove(newStr)) continue;
					strList.add(newStr);
				}
				sb.setCharAt(i, str.charAt(i));
			}
			
			return strList;
		}
		
		private void generatePaths(List<String> path, String checkpoint, 
				String endWord, Set<String> seen)
		{
			if (checkpoint.equals(endWord)) paths.add(new ArrayList(path));
			else
			{
				for (String variant: getVariants(seen, checkpoint))
				{
					path.add(variant);
					generatePaths(path, variant, endWord, new HashSet(seen));
					path.remove(path.size() - 1);
				}
			}
		}
		
	}
}

