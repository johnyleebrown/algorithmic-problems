package graph.dfs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 1048
 */
public class LongestStringChain {

	/**
	 * Bucket sort, graph, dfs
	 * We go through all LEN-1 strings.
	 */
	public static class Solution2 {

		public int longestStrChain(String[] words) {

			// bucket sort
			List<String>[] buckets = new List[17];
			for (String w : words) {
				int len = w.length();
				if (buckets[len] == null) buckets[len] = new ArrayList<>();
				buckets[len].add(w);
			}

			// create connection if strings are similar
			Map<String, Set<String>> g = new HashMap<>();
			for (int i = 16; i > 1; i--) {
				if (buckets[i] == null || buckets[i - 1] == null) continue;
				for (int j = 0; j < buckets[i].size(); j++) {
					String cur = buckets[i].get(j);
					g.putIfAbsent(cur, new HashSet<>());
					for (int k = 0; k < buckets[i - 1].size(); k++) {
						String other = buckets[i - 1].get(k);
						if (areSimilar(other, cur)) {
							g.get(cur).add(other);
						}
					}
				}
			}

			// find longest path
			int ans = 1;
			Map<String, Integer> dp = new HashMap<>();
			for (String w : g.keySet()) {
				ans = Math.max(ans, dfs(w, g, dp));
			}

			return ans;
		}

		private int dfs(String w, Map<String, Set<String>> g, Map<String, Integer> dp) {
			if (dp.containsKey(w)) return dp.get(w);
			int ans = 1;
			for (String v : g.getOrDefault(w, Collections.emptySet())) {
				ans = Math.max(1 + dfs(v, g, dp), ans);
			}
			dp.put(w, ans);
			return ans;
		}

		private boolean areSimilar(String s1, String s2) {
			boolean oneDiff = false;
			int j = 0;
			for (int i = 0; i < s1.length(); ) {
				if (s1.charAt(i) != s2.charAt(j)) {
					if (oneDiff) return false;
					j++;
					oneDiff = true;
				} else {
					i++;
					j++;
				}
			}
			boolean twoDiff = oneDiff && j == s1.length() - 1;
			return !twoDiff;
		}
	}

	/**
	 * similar to prev but instead of going through all LEN-1 strings - create predecessors
	 * and check them in set of LEN-1 strings
	 * precreate predecessors because there are less of them
	 */
	static class Solution3 {

		public int longestStrChain(String[] words) {

			return 0;
		}
	}
}
