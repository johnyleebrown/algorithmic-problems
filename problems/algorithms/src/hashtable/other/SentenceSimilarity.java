package hashtable.other;

import java.util.*;

/**
 * 734
 */
public class SentenceSimilarity {
	public static class Solution {
		public boolean areSentencesSimilar(String[] w1, String[] w2, List<List<String>> pairs) {
			if (w1.length != w2.length) return false;
			Map<String, Set<String>> m = new HashMap<>();
			for (List<String> p : pairs) {
				m.putIfAbsent(p.get(0), new HashSet<>());
				m.get(p.get(0)).add(p.get(1));
				m.putIfAbsent(p.get(1), new HashSet<>());
				m.get(p.get(1)).add(p.get(0));
			}
			for (int i = 0; i < w1.length; i++) {
				if (w1[i].equals(w2[i])) {
					continue;
				}
				if ((!m.containsKey(w1[i]) || !m.get(w1[i]).contains(w2[i])) || (!m.containsKey(w2[i]) || !m.get(w2[i]).contains(w1[i]))) {
					return false;
				}
			}
			return true;
		}
	}
}