package medium.dfs;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Queue;
import java.util.Set;

/*
 * 127. Word Ladder
 */
public class WordLadder
{
    /**
     * BFS
     * <p>
     * generate combinations of every word that we put in queue
     * leave only those that are in wordList
     * or when adding check through wordList
     * <p>
     * 2 gen a letter we will use charAt -> letter + 1
     * <p>
     * HINT : if there is an end condition we can try to use two-end bfs
     */
    class Solution
    {
        public int ladderLength(String beginWord, String endWord, List<String> wordList)
        {
            if (!wordList.contains(endWord)) return 0;
            wordList.remove(beginWord);

            // seen set for optimzation
            Set<String> seen = new HashSet<>();

            Queue<String> queue = new LinkedList<>();
            queue.add(beginWord);

            int transformationsCount = 0;
            while (!queue.isEmpty())
            {
                transformationsCount++;
                int queueSize = queue.size();
                for (int i = 0; i < queueSize; i++)
                {
                    String target = queue.poll();
                    if (endWord.equals(target)) return transformationsCount;
                    generateAndPlace(target, wordList, queue, seen);
                }
                if (transformationsCount == 1 && queue.size() == 0) return 0;
            }

            return transformationsCount;
        }

        private void generateAndPlace(String baseWord, List<String> wordList, Queue<String> queue, Set<String> seen)
        {
            ListIterator<String> iter = wordList.listIterator();
            while (iter.hasNext())
            {
                String word = iter.next();
                if (isSimilarTo(baseWord.toCharArray(), word.toCharArray()) && seen.add(word))
                {
                    queue.add(word);
                    iter.remove();
                }
            }
        }

        private boolean isSimilarTo(char[] source, char[] target)
        {
            int len = source.length;
            int bestCase = len - 1;
            int currentCount = len;

            for (int i = 0; i < len; i++)
            {
                if (source[i] != target[i]) currentCount--;
                if (currentCount < bestCase) return false;
            }

            return true;
        }
    }


}
/*
Tests

"hot"
"dog"
["hot","dog","dot"]
"hit"
"cog"
["hot","dot","dog","lot","log","cog"]
"hot"
"dog"
["hot","dog"]
*/
/*
  Given two words (beginWord and endWord), and a dictionary's word list, find the length of shortest transformation sequence from beginWord to endWord, such that:

  Only one letter can be changed at a time.
  Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
  Note:

  Return 0 if there is no such transformation sequence.
  All words have the same length.
  All words contain only lowercase alphabetic characters.
  You may assume no duplicates in the word list.
  You may assume beginWord and endWord are non-empty and are not the same.

  Example 1:

  Input:
  beginWord = "hit",
  endWord = "cog",
  wordList = ["hot","dot","dog","lot","log","cog"]

  Output: 5

  Explanation: As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
  return its length 5.

  Example 2:

  Input:
  beginWord = "hit"
  endWord = "cog"
  wordList = ["hot","dot","dog","lot","log"]

  Output: 0

  Explanation: The endWord "cog" is not in wordList, therefore no possible transformation.
 */