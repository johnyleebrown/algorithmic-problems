package regular.string;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 49
 */
public class GroupAnagrams {
	/**
	 * Record in map the list position in answer.
	 */
	class Solution {
		public List<List<String>> groupAnagrams(String[] a) {
			Map<String, Integer> map = new HashMap<>();
			List<List<String>> ans = new ArrayList<>();
			int ind = 0;
			for (String curStr : a) {
				char[] c = new char[26];
				for (int j = 0; j < curStr.length(); j++) {
					c[curStr.charAt(j) - 'a']++;
				}
				String s = String.valueOf(c);
				if (map.containsKey(s)) {
					int l = map.get(s);
					ans.get(l).add(curStr);
				}
				else {
					List<String> w = new ArrayList<>();
					w.add(curStr);
					ans.add(w);
					map.put(s, ind++);
				}
			}
			return ans;
		}
	}
}