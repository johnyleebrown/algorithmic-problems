package Medium.Trie;

import java.util.HashMap;
import java.util.Map;

/**
 * 677
 * Implement a MapSum class with insert, and sum methods.
 * For the method insert, you'll be given a pair of (string, integer). The string represents the key and the integer represents the value. If the key already existed, then the original key-value pair will be overridden to the new one.
 * For the method sum, you'll be given a string representing the prefix, and you need to return the sum of all the pairs' value whose key starts with the prefix.
 */
public class MapSumPairs {

    // Every insert operation is O(1)
    // Every sum operation is O(N*P) where N is the number of items in the map, and P is the length of the input prefix.
    class MapSum {

        Map<String, Integer> map;
        public MapSum() {
            map = new HashMap();
        }

        public void insert(String key, int val) {
            map.put(key, val);
        }

        public int sum(String prefix) {
            int s = 0;
            for (Map.Entry<String, Integer> entry : map.entrySet()) {
                if (entry.getKey().startsWith(prefix)) {
                    s+=entry.getValue();
                }
            }
            return s;
        }
    }

    // Time Complexity: Every insert operation is O(k),where K is the length of the key
    // Space Complexity: The space used is linear in the size of the total input
    class MapSum2 {
        HashMap<String, Integer> map;
        TrieNode root;
        public MapSum2() {
            map = new HashMap();
            root = new TrieNode();
        }
        public void insert(String key, int val) {
            int delta = val - map.getOrDefault(key, 0);
            map.put(key, val);
            TrieNode cur = root;
            cur.score += delta;
            for (char c: key.toCharArray()) {
                cur.children.putIfAbsent(c, new TrieNode());
                cur = cur.children.get(c);
                cur.score += delta;
            }
        }
        public int sum(String prefix) {
            TrieNode cur = root;
            for (char c: prefix.toCharArray()) {
                cur = cur.children.get(c);
                if (cur == null) return 0;
            }
            return cur.score;
        }
    }
    class TrieNode {
        Map<Character, TrieNode> children = new HashMap();
        int score;
    }
}
