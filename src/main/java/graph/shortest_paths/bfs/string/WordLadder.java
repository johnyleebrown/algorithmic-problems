package graph.shortest_paths.bfs.string;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;

/**
 * 127
 */
public class WordLadder {

	/**
	 * BFS
	 *
	 * generate combinations of every word that we put in queue leave only those that are in
	 * wordList or when adding
	 * check through wordList
	 *
	 * 2 gen a letter we will use charAt -> letter + 1
	 *
	 * HINT : if there is an end condition we can try to use two-end
	 * graph.shortestPaths.bfs
	 */
	static class Solution {

		public int ladderLength(String beginWord, String endWord, List<String> wordList) {
			if (!wordList.contains(endWord)) return 0;
			wordList.remove(beginWord);

			// seen set for optimzation
			Set<String> seen = new HashSet<>();

			Queue<String> queue = new LinkedList<>();
			queue.add(beginWord);

			int transformationsCount = 0;
			while (!queue.isEmpty()) {
				transformationsCount++;
				int queueSize = queue.size();
				for (int i = 0; i < queueSize; i++) {
					String target = queue.poll();
					if (endWord.equals(target)) return transformationsCount;
					generateAndPlace(target, wordList, queue, seen);
				}
				if (transformationsCount == 1 && queue.size() == 0) return 0;
			}

			return transformationsCount;
		}

		private void generateAndPlace(String baseWord, List<String> wordList,
				Queue<String> queue, Set<String> seen) {
			ListIterator<String> iter = wordList.listIterator();
			while (iter.hasNext()) {
				String word = iter.next();
				if (isSimilarTo(baseWord.toCharArray(), word.toCharArray()) && seen.add(word)) {
					queue.add(word);
					iter.remove();
				}
			}
		}

		private boolean isSimilarTo(char[] source, char[] target) {
			int len = source.length;
			int bestCase = len - 1;
			int currentCount = len;

			for (int i = 0; i < len; i++) {
				if (source[i] != target[i]) currentCount--;
				if (currentCount < bestCase) return false;
			}

			return true;
		}
	}
}