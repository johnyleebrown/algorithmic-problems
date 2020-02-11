package bfs.string;

import java.util.*;

/**
 * 126
 *
 * ======
 *
 * Task.
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find all shortest transformation sequence(s)
 * from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time Each transformed word must exist in the word list. Note that beginWord is
 * not a transformed word. Note:
 *
 * Return an empty list if there is no such transformation sequence. All words have the same length. All words contain
 * only lowercase alphabetic characters. You may assume no duplicates in the word list. You may assume beginWord and
 * endWord are non-empty and are not the same.
 *
 * ======
 *
 * Source: Leetcode
 */
public class WordLadderII
{
	/**
	 * BFS. For queue elements we store lists, so in the end we could just add the needed list to the result. The trick
	 * here is with seen set. For each step towards the target we have a new localSeen set. After the current breadth is
	 * over, we add everything to overallSeen.
	 */
	class Solution
	{
		List<List<String>> result = new ArrayList<>();
		Set<String> baseWords = new HashSet<>();
		Set<String> overallSeen = new HashSet<>();
		Set<String> localSeen = new HashSet<>();
		List<List<String>> paths = new ArrayList<>();

		public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
		{
			baseWords.addAll(wordList);

			if (!baseWords.contains(endWord))
			{
				return result;
			}

			overallSeen.add(beginWord);

			List<String> firstPath = new ArrayList<>();
			firstPath.add(beginWord);
			paths.add(firstPath);

			while (!paths.isEmpty())
			{
				boolean f = false;
				int size = paths.size();

				while (--size >= 0)
				{
					f = getVariantsForString(paths.remove(0), endWord, localSeen) || f;
				}

				if (f) break;

				overallSeen.addAll(localSeen);
				localSeen.clear();
			}

			return result;
		}

		private boolean getVariantsForString(List<String> t, String endWord, Set<String> curSeen)
		{
			String currentWord = t.get(t.size() - 1); // get the last word of current list
			boolean found = false;
			char[] charArray = currentWord.toCharArray();

			for (int i = 0; i < currentWord.length(); i++)
			{
				for (char c = 'a'; c <= 'z'; c++)
				{
					if (c == currentWord.charAt(i))
					{
						continue;
					}

					charArray[i] = c;
					String variant = String.valueOf(charArray);

					if (!baseWords.contains(variant) || overallSeen.contains(variant))
					{
						continue;
					}

					paths.add(new ArrayList<>(t));
					paths.get(paths.size() - 1).add(variant);

					if (variant.equals(endWord))
					{
						found = true;
						result.add(paths.get(paths.size() - 1));
						continue;
					}

					curSeen.add(variant);
				}

				charArray[i] = currentWord.charAt(i);
			}

			return found;
		}
	}

	/**
	 * BFS + DFS in the end.
	 */
	class Solution2
	{
		List<List<String>> result = new ArrayList<>();
		Set<String> baseWords = new HashSet<>();
		Set<String> globalSeen = new HashSet<>();
		Set<String> localSeen = new HashSet<>();
		Map<String, List<String>> wordGraph = new HashMap<>();

		public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
		{
			baseWords.addAll(wordList);

			if (!baseWords.contains(endWord))
			{
				return result;
			}

			globalSeen.add(beginWord);

			List<String> q = new ArrayList<>();
			q.add(beginWord);

			while (!q.isEmpty())
			{
				boolean found = false;
				int size = q.size();

				while (--size >= 0)
				{
					found = getVariantsForString(q.remove(0), endWord, localSeen, q) || found;
				}

				if (found)
				{
					break;
				}

				globalSeen.addAll(localSeen);
				localSeen.clear();
			}

			generatePaths(beginWord, endWord, new ArrayList<>(), beginWord);
			return result;
		}

		private boolean getVariantsForString(String currentWord, String endWord, Set<String> curSeen, List<String> q)
		{
			boolean found = false;
			char[] charArray = currentWord.toCharArray();
			wordGraph.put(currentWord, new ArrayList<>());

			for (int i = 0; i < currentWord.length(); i++)
			{
				for (char c = 'a'; c <= 'z'; c++)
				{
					if (c == currentWord.charAt(i))
					{
						continue;
					}

					charArray[i] = c;
					String variant = String.valueOf(charArray);

					if (!baseWords.contains(variant) || globalSeen.contains(variant))
					{
						continue;
					}

					wordGraph.get(currentWord).add(variant);

					if (variant.equals(endWord))
					{
						found = true;
						continue;
					}

					q.add(variant);
					curSeen.add(variant);
				}

				charArray[i] = currentWord.charAt(i);
			}

			return found;
		}

		private void generatePaths(String currentString, String endWord, List<String> currentList, String start)
		{
			if (currentString.equals(endWord))
			{
				List<String> newList = new ArrayList<>(currentList);
				newList.add(0, start); // adding beginWord at 1st position
				result.add(newList);
			}
			else if (wordGraph.containsKey(currentString))
			{
				for (String s : wordGraph.get(currentString))
				{
					currentList.add(s);
					generatePaths(s, endWord, currentList, start);
					currentList.remove(currentList.size() - 1);
				}
			}
		}
	}

	/**
	 * Two-directional BFS.
	 */
	class Solution3
	{
		List<List<String>> result = new ArrayList<>();
		Set<String> baseWords = new HashSet<>();
		Map<String, Set<String>> wordGraph = new HashMap<>();
		Set<String> globalSeen = new HashSet<>();

		public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
		{
			for (String s : wordList)
				if (!s.equals(beginWord))
					baseWords.add(s);

			if (!baseWords.contains(endWord))
				return result;

			globalSeen.add(beginWord);

			Set<String> leftQueueSeenSet = new HashSet<>();
			Set<String> rightQueueSeenSet = new HashSet<>();

			List<String> leftQueue = new ArrayList<>();
			leftQueue.add(beginWord);
			List<String> rightQueue = new ArrayList<>();
			rightQueue.add(endWord);

			while (!leftQueue.isEmpty() && !rightQueue.isEmpty())
			{
				// looking at all the variants at current level from the LEFT queue
				boolean foundInLeftQueue = false;
				int size1 = leftQueue.size();
				while (--size1 >= 0)
				{
					foundInLeftQueue = getVariantsForString(leftQueue, rightQueueSeenSet, endWord, leftQueueSeenSet, true) || foundInLeftQueue;
				}
				if (foundInLeftQueue)
				{
					break;
				}
				globalSeen.addAll(rightQueueSeenSet);
				rightQueueSeenSet.clear();

				// looking at all the variants at current level from the RIGHT queue
				boolean foundInRightQueue = false;
				int size2 = rightQueue.size();
				while (--size2 >= 0)
				{
					foundInRightQueue = getVariantsForString(rightQueue, leftQueueSeenSet, beginWord, rightQueueSeenSet, false) || foundInRightQueue;
				}
				if (foundInRightQueue)
				{
					break;
				}
				globalSeen.addAll(leftQueueSeenSet);
				leftQueueSeenSet.clear();

			}

			generatePaths(beginWord, endWord, new ArrayList<>(), beginWord);
			return result;
		}

		private boolean getVariantsForString(List<String> q, Set<String> otherQueueSet, String end, Set<String> cs, boolean leftQueue)
		{
			String currentWord = q.remove(0);
			boolean found = false;
			char[] charArray = currentWord.toCharArray();

			for (int i = 0; i < currentWord.length(); i++)
			{
				for (char c = 'a'; c <= 'z'; c++)
				{
					if (c == currentWord.charAt(i))
					{
						continue;
					}

					charArray[i] = c;
					String variant = String.valueOf(charArray);

					if (!baseWords.contains(variant) || globalSeen.contains(variant))
					{
						continue;
					}

					if (leftQueue)
					{
						wordGraph.putIfAbsent(currentWord, new HashSet<>());
						wordGraph.get(currentWord).add(variant);
					}
					else
					{
						wordGraph.putIfAbsent(variant, new HashSet<>());
						wordGraph.get(variant).add(currentWord);
					}

					if (otherQueueSet.contains(variant) || variant.equals(end))
					{
						found = true;
						continue;
					}

					q.add(variant);
					cs.add(variant);
				}

				charArray[i] = currentWord.charAt(i);
			}

			return found;
		}

		private void generatePaths(String currentString, String endWord, List<String> currentList, String start)
		{
			if (currentString.equals(endWord))
			{
				List<String> newList = new ArrayList<>(currentList);
				newList.add(0, start); // adding beginWord at 1st position
				result.add(newList);
			}
			else if (wordGraph.containsKey(currentString))
			{
				for (String s : wordGraph.get(currentString))
				{
					currentList.add(s);
					generatePaths(s, endWord, currentList, start);
					currentList.remove(currentList.size() - 1);
				}
			}
		}
	}

	/**
	 * Two-directional BFS. Similar to 3, faster.
	 */
	class Solution4
	{
		public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList)
		{

			List<List<String>> res = new ArrayList<>();
			Set<String> dict = new HashSet<>(wordList);
			if (!dict.contains(endWord))
				return res;
			Set<String> begin = new HashSet<>();
			Set<String> end = new HashSet<>();
			begin.add(beginWord);
			end.add(endWord);
			Map<String, List<String>> map = new HashMap<String, List<String>>();

			bfs(dict, begin, end, map);
			List<String> temp = new ArrayList<>(Arrays.asList(beginWord));
			dfs(res, temp, beginWord, endWord, map);

			return res;
		}

		private void bfs(Set<String> dict, Set<String> begin, Set<String> end, Map<String, List<String>> map)
		{

			boolean reversed = false;
			boolean terminate = false;
			while (begin.size() > 0)
			{
				dict.removeAll(begin);
				dict.removeAll(end);
				if (begin.size() > end.size())
				{
					reversed = !reversed;
					Set<String> temp = new HashSet<>(begin);
					begin = end;
					end = temp;
				}
				Set<String> set = new HashSet<>();
				for (String s : begin)
				{
					for (int i = 0; i < s.length(); i++)
					{
						char[] ch = s.toCharArray();
						for (char c = 'a'; c <= 'z'; c++)
						{
							if (ch[i] == c)
								continue;
							ch[i] = c;
							String word = new String(ch);
							if (end.contains(word))
							{
								if (!reversed)
								{
									List<String> list = map.containsKey(s) ? map.get(s) : new ArrayList<>();
									list.add(word);
									map.put(s, list);
								}
								else
								{
									List<String> list = map.containsKey(word) ? map.get(word) : new ArrayList<>();
									list.add(s);
									map.put(word, list);
								}
								terminate = true;
							}
							if (dict.contains(word))
							{
								if (!reversed)
								{
									List<String> list = map.containsKey(s) ? map.get(s) : new ArrayList<>();
									list.add(word);
									map.put(s, list);
								}
								else
								{
									List<String> list = map.containsKey(word) ? map.get(word) : new ArrayList<>();
									list.add(s);
									map.put(word, list);
								}
								set.add(word);
							}
						}
					}
				}
				begin = set;
				if (terminate)
					return;
			}

			return;
		}

		private void dfs(List<List<String>> res, List<String> temp, String start, String end, Map<String, List<String>> map)
		{

			if (start.equals(end))
			{
				res.add(new ArrayList<>(temp));
				return;
			}
			if (!map.containsKey(start))
				return;

			for (String word : map.get(start))
			{
				temp.add(word);
				dfs(res, temp, word, end, map);
				temp.remove(temp.size() - 1);
			}
		}
	}
}