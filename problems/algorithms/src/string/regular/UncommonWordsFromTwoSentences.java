package string.regular;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * 888
 *
 * We are given two sentences A and B.  (A sentence is a string of space separated words.  Each word consists only of lowercase letters.)
 * A word is uncommon if it appears exactly once in one of the sentences, and does not appear in the other sentence.
 * Return a list of all uncommon words.
 * You may return the list in any order.
 */
public class UncommonWordsFromTwoSentences {
    /**
     * Time complexity: O()
     * Space complexity: O()
     */
    class Solution {
        public String[] uncommonFromSentences(String A, String B) {
            Map<String, Integer> map = new HashMap<>();
            for (String s : A.split(" ")) map.put(s, map.getOrDefault(s, 0) + 1);
            for (String s : B.split(" ")) map.put(s, map.getOrDefault(s, 0) + 1);
            ArrayList<String> list = new ArrayList<>();
            for (String s : map.keySet()) if (map.get(s) == 1) list.add(s);
            return list.toArray(new String[0]);
        }
    }
}
