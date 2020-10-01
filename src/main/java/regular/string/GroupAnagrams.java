package regular.string;

import java.util.*;

/**
 * 49
 */
public class GroupAnagrams {
	/**
	 * Record in map the list position in answer.
	 */
	public static class Solution1 {
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
				} else {
					List<String> w = new ArrayList<>();
					w.add(curStr);
					ans.add(w);
					map.put(s, ind++);
				}
			}
			return ans;
		}
	}

	/**
	 * Map key is a string version of count array(26).
	 */
	public static class Solution2 {
		public List<List<String>> groupAnagrams(String[] ar) {
			List<List<String>> ans = new LinkedList<>();
			if (ar.length == 0) {
				return ans;
			}

			Map<String, List<String>> m = new HashMap<>();
			for (String s : ar) {
				int[] ca = new int[26];
				for (int i = 0; i < s.length(); i++) {
					ca[s.charAt(i) - 'a']++;
				}
				String key = Arrays.toString(ca);
				m.putIfAbsent(key, new LinkedList<>());
				m.get(key).add(s);
			}

			ans.addAll(m.values());
			return ans;
		}
	}
}