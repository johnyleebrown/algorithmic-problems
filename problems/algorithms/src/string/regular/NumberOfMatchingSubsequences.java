package string.regular;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 792
 *
 * Given string S and a dictionary of words words,
 * find the number of words[i] that is a subsequence of S.
 */
public class NumberOfMatchingSubsequences {
    /**
     * Go through S once, and while doing that, move through all words accordingly.
     * Track of how much of each word I’ve already seen, and with each letter of S,
     * advance the words waiting for that letter. To quickly find the words waiting
     * for a certain letter, store each word (and its progress) in a list of words
     * waiting for that letter. Then for each of the lucky words whose current letter
     * just occurred in S, I update their progress and store them in the list for their
     * next letter.
     * by stefanpochmann
     *
     * Time complexity: O(n)
     * Space complexity: O(1)
     */
    public static int solution(String S, String[] words) {
        List<Integer[]>[] waiting = new List[26];
        for (int i = 0; i < 26; i++) waiting[i] = new ArrayList<>();
        for (int i = 0; i < words.length; i++)
            waiting[words[i].charAt(0) - 'a'].add(new Integer[]{i, 1});
        for (char c : S.toCharArray()) {
            List<Integer[]> advance = new ArrayList<>(waiting[c - 'a']);
            waiting[c - 'a'].clear();
            for (Integer[] a : advance)
                waiting[a[1] < words[a[0]].length() ? words[a[0]].charAt(a[1]++) - 'a' : 0].add(a);
        }
        return waiting[0].size();
    }

    // remove letter by letter from each word starting at S.charAt
    // 11% faster by setsuna214
    public int solution2(String S, String[] words) {
        Map<Character, Deque<String>> map = new HashMap<>();
        for (char c = 'a'; c <= 'z'; c++)   map.putIfAbsent(c, new LinkedList<>());
        for (String word : words)           map.get(word.charAt(0)).addLast(word);

        int count = 0;
        for (char c : S.toCharArray()) {
            Deque<String> queue = map.get(c);
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                String word = queue.removeFirst();

                if (word.length() == 1) count++;
                else                    map.get(word.charAt(1)).addLast(word.substring(1));
            }
        }
        return count;
    }
}
