package string.serialize;

/*
 * 249
 * Google
 */
public class GroupShiftedStrings
{
	class Solution 
	{
		public List<List<String>> groupStrings(String[] strings) 
		{
			// we know that it wont exceed n
			HashMap<String, List<String>> stringPatternMap = new HashMap<>(strings.length);
			for (String variant: strings)
			{
				String variantPattern = createPattern(variant);
				stringPatternMap.putIfAbsent(variantPattern, new LinkedList<>());
				stringPatternMap.get(variantPattern).add(variant);
			}

			return new LinkedList(stringPatternMap.values());
		}

		// serializing
		private String createPattern(String variant)
		{
			String pattern = "";
			for (int i = 1; i < variant.length(); i++)
			{
				int diff = variant.charAt(i) - variant.charAt(i - 1);
				int val = (26 + diff) % 26;
				pattern += val + "d";
			}

			return pattern;
		}
	}
}

