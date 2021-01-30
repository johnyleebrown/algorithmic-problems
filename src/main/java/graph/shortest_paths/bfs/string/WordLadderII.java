package graph.shortest_paths.bfs.string;

import java.util.*;

/**
 * 126
 *
 * ======
 *
 * Task.
 *
 * Given two words (beginWord and endWord), and a dictionary's word list, find
 * all shortest transformation sequence(s)
 * from beginWord to endWord, such that:
 *
 * Only one letter can be changed at a time Each transformed word must exist in
 * the word list. Note that beginWord is
 * not a transformed word. Note:
 *
 * Return an empty list if there is no such transformation sequence. All words
 * have the same length. All words contain
 * only lowercase alphabetic characters. You may assume no duplicates in the
 * word list. You may assume beginWord and
 * endWord are non-empty and are not the same.
 *
 * ======
 *
 * Source: Leetcode
 */
public class WordLadderII {
    /**
     * Create graph of connected words and then with dfs (backtracking) create
     * paths. Notice though that we allow to add edge to the levelVisit nodes.
     */
    public static class Solution1 {
        public List<List<String>> findLadders(String b, String e, List<String> l) {
            List<List<String>> res = new ArrayList<>();
            Set<String> set = new HashSet<>(l);
            if (!set.contains(e)) return res;
            Map<String, List<String>> g = createGraph(b, e, set);
            List<String> curList = new ArrayList<>();
            curList.add(b);
            generatePaths(res, g, b, e, curList);
            return res;
        }

        Map<String, List<String>> createGraph(String b, String e, Set<String> set) {
            int wl = b.length();
            Map<String, List<String>> g = new HashMap<>();
            List<String> q = new ArrayList<>();
            q.add(b);
            Set<String> seen = new HashSet<>();
            seen.add(b);
            boolean shouldEnd = false;
            while (!q.isEmpty()) {
                int s = q.size();
                Set<String> levelVisit = new HashSet<>();
                while (--s >= 0) {
                    String cur = q.remove(0);
                    if (e.equals(cur)) {
                        shouldEnd = true;
                    }
                    g.putIfAbsent(cur, new ArrayList<>());
                    char[] ca = cur.toCharArray();
                    for (int j = 0; j < wl; j++) {
                        char x = ca[j];
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (c == x) continue;
                            ca[j] = c;
                            String v = String.valueOf(ca); // variant
                            if (set.contains(v)) {
                                if (!seen.contains(v)) {
                                    g.get(cur).add(v);
                                    seen.add(v);
                                    levelVisit.add(v);
                                    q.add(v);
                                } else {
                                    if (levelVisit.contains(v)) {
                                        g.get(cur).add(v);
                                    }
                                }
                            }
                        }
                        ca[j] = x;
                    }
                }
                levelVisit.clear();
                if (shouldEnd) break;
            }
            return g;
        }

        void generatePaths(List<List<String>> res, Map<String, List<String>> g, String v, String e, List<String> curList) {
            if (!g.containsKey(v)) return;
            if (e.equals(v)) {
                res.add(new ArrayList<>(curList));
            } else {
                for (String w : g.get(v)) {
                    curList.add(w);
                    generatePaths(res, g, w, e, curList);
                    curList.remove(curList.size() - 1);
                }
            }
        }
    }

    /**
     * Two-directional BFS. Similar to 3, faster.
     */
    class Solution4 {
        public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {

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

        private void bfs(Set<String> dict, Set<String> begin, Set<String> end, Map<String, List<String>> map) {

            boolean reversed = false;
            boolean terminate = false;
            while (begin.size() > 0) {
                dict.removeAll(begin);
                dict.removeAll(end);
                if (begin.size() > end.size()) {
                    reversed = !reversed;
                    Set<String> temp = new HashSet<>(begin);
                    begin = end;
                    end = temp;
                }
                Set<String> set = new HashSet<>();
                for (String s : begin) {
                    for (int i = 0; i < s.length(); i++) {
                        char[] ch = s.toCharArray();
                        for (char c = 'a'; c <= 'z'; c++) {
                            if (ch[i] == c)
                                continue;
                            ch[i] = c;
                            String word = new String(ch);
                            if (end.contains(word)) {
                                if (!reversed) {
                                    List<String> list = map.containsKey(s) ? map.get(s) : new ArrayList<>();
                                    list.add(word);
                                    map.put(s, list);
                                } else {
                                    List<String> list = map.containsKey(word) ? map.get(word) : new ArrayList<>();
                                    list.add(s);
                                    map.put(word, list);
                                }
                                terminate = true;
                            }
                            if (dict.contains(word)) {
                                if (!reversed) {
                                    List<String> list = map.containsKey(s) ? map.get(s) : new ArrayList<>();
                                    list.add(word);
                                    map.put(s, list);
                                } else {
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

        private void dfs(List<List<String>> res, List<String> temp, String start, String end, Map<String, List<String>> map) {

            if (start.equals(end)) {
                res.add(new ArrayList<>(temp));
                return;
            }
            if (!map.containsKey(start))
                return;

            for (String word : map.get(start)) {
                temp.add(word);
                dfs(res, temp, word, end, map);
                temp.remove(temp.size() - 1);
            }
        }
    }
}