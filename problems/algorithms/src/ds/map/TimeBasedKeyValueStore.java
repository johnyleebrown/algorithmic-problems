package ds.map;

import java.util.*;

/**
 * 981
 */
public class TimeBasedKeyValueStore
{
	/**
	 * Binary search solution.
	 */
	class Solution1
	{
		class Node
		{
			final String value;
			final int timeStamp;

			public Node(String value, int timeStamp)
			{
				this.value = value;
				this.timeStamp = timeStamp;
			}
		}

		Map<String, List<Node>> timeMap = new HashMap<>();

		public Solution1()
		{
		}

		public void set(String key, String value, int timestamp)
		{
			if (!timeMap.containsKey(key))
			{
				timeMap.put(key, new ArrayList<>());
			}

			timeMap.get(key).add(new Node(value, timestamp));
		}

		public String get(String key, int timestamp)
		{
			if (!timeMap.containsKey(key))
			{
				return "";
			}

			Node returnValue = binarySearch(timeMap.get(key), timestamp);
			return returnValue == null ? "" : returnValue.value;
		}

		private Node binarySearch(final List<Node> nodes, int timeStamp)
		{
			if (nodes.isEmpty())
			{
				return null;
			}

			int low = 0, high = nodes.size() - 1;
			Node returnValue = null;

			while (low <= high)
			{
				int mid = low + (high - low) / 2;
				final Node current = nodes.get(mid);

				if (current.timeStamp == timeStamp)
				{
					return returnValue = nodes.get(mid);
				}
				else if (current.timeStamp > timeStamp)
				{
					high = mid - 1;
				}
				else
				{
					returnValue = current;
					low = mid + 1;
				}
			}

			return returnValue;
		}
	}


	/**
	 * TreeMap solution.
	 *
	 * Set O(n + log(m)), get O(log(m)), where n - keys and m - times
	 */
	class Solution2
	{
		Map<String, TreeMap<Integer, String>> m = new HashMap<>();

		public Solution2()
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