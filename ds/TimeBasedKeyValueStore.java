/*
 * 981
 * Google
 */
public class TimeBasedKeyValueStore
{
	/*
	 * TreeMap solution.
	 * set O(n + log(m)), get O(log(m)), where n - keys and m - times
	 */
	class Solution 
	{
		static Map<String, TreeMap<Integer, String>> m = new HashMap<>();

		public TimeMap() 
		{
		}

		public void set(String key, String value, int timestamp) 
		{
			m.putIfAbsent(key, new TreeMap<>());
			m.get(key).put(timestamp, value);
		}

		public String get(String key, int timestamp) 
		{
			if (!m.containsKey(key))
			{
				return "";
			}

			Integer f = m.get(key).floorKey(timestamp);
			return f == null ? "" : m.get(key).get(f);
		}
	}
}

