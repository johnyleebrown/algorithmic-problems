package sort.bucketSort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 347
 */
public class TopKFrequentElements {
	class Solution {
		public List<Integer> topKFrequent(int[] a, int k) {
			Map<Integer, Integer> count = new HashMap<>();
			for (int i : a) {
				count.put(i, count.getOrDefault(i, 0) + 1);
			}

			List<Integer>[] buckets = new List[a.length + 1];
			for (int num : count.keySet()) {
				int frequency = count.get(num);
				if (buckets[frequency] == null) {
					buckets[frequency] = new ArrayList<>();
				}
				buckets[frequency].add(num);
			}

			List<Integer> ans = new ArrayList<>();
			for (int i = buckets.length - 1; i >= 0; i--) {
				if (buckets[i] == null)
					continue;

				for (int num : buckets[i]) {
					ans.add(num);
					if (ans.size() == k)
						return ans;
				}
			}

			return ans;
		}
	}
}