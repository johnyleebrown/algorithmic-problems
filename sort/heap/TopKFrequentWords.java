/*
 * 692
 * Google
 */
class TopKFrequentWords
{
	/*
	 * Bucket sort.
	 */
	class Solution 
	{
		public List<String> topKFrequent(String[] words, int k) 
		{
			Map<String, Integer> map = new HashMap<>();
			int max = 0, min = words.length;
			for (String w: words)
			{
				int x = map.getOrDefault(w, 0) + 1;
				map.put(w, x);
				if (min > x)
				{
					min = x;
				}
				if (max < x)
				{
					max = x;
				}
			}
			int range = max - min + 1;
			PriorityQueue<String>[] pqa = new PriorityQueue[range];
			for (String s: map.keySet())
			{
				int count = map.get(s);
				int pos = count - min;
				if (pqa[pos] == null)
				{
					pqa[pos] = new PriorityQueue<>();
				}
				pqa[pos].add(s);
			}
			List<String> ans = new ArrayList<>();
			for (int i = pqa.length - 1; i >= 0 && k >= 0; i--)
			{
				if (pqa[i] == null)
				{
					continue;
				}
				while (!pqa[i].isEmpty() && --k >= 0)
				{
					ans.add(pqa[i].poll());
				}
			}
			return ans;
		}
	}
	/*
	 * PriorityQueue solution.
	 */
	class Solution
	{
		private class Element
		{
			int freq;
			String word;
			Element(int freq, String word)
			{
				this.freq = freq;
				this.word = word;
			}
		}

		public List<String> topKFrequent(String[] words, int k)
		{
			Map<String, Integer> m = new HashMap<>();
			for (String w: words)
			{
				m.put(w, m.getOrDefault(w, 0) + 1);
			}
			PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> {
				if (a.freq == b.freq)
				{
					return a.word.compareTo(b.word);
				}

				return b.freq - a.freq;
			});
			for (String w: m.keySet())
			{
				pq.add(new Element(m.get(w), w));
			}
			List<String> ans = new ArrayList<>();
			for (int i = 0; i < k; i++)
			{
				ans.add(pq.poll().word);
			}
			return ans;
		}
	}
}


