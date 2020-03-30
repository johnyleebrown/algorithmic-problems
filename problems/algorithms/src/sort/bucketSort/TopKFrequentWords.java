package sort.bucketSort;

import java.util.*;

/**
 * 692
 */
public class TopKFrequentWords {
	/**
	 * Bucket sort.
	 */
	class Solution {
		public List<String> topKFrequent(String[] words, int k) {
			Map<String, Integer> map = new HashMap<>();
			for (String w : words) {
				map.put(w, map.getOrDefault(w, 0) + 1);
			}
			PriorityQueue<String>[] pqa = new PriorityQueue[words.length + 1];
			for (String s : map.keySet()) {
				int count = map.get(s);
				if (pqa[count] == null) {
					pqa[count] = new PriorityQueue<>();
				}
				pqa[count].add(s);
			}
			List<String> ans = new ArrayList<>();
			for (int i = pqa.length - 1; i >= 0 && k >= 0; i--) {
				while (pqa[i] != null && !pqa[i].isEmpty() && --k >= 0) {
					ans.add(pqa[i].poll());
				}
			}
			return ans;
		}
	}
}