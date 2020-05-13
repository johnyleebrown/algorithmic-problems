package string.trie;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 642
 *
 * ======
 *
 * Task.
 *
 * Design a search autocomplete system for a search engine. Users may input a
 * sentence (at least one word and end with a special character '#'). For each
 * character they type except '#', you need to return the top 3 historical hot
 * sentences that have prefix the same as the part of sentence already typed.
 * Here are the specific rules:
 *
 * The hot degree for a sentence is defined as the number of times a user typed
 * the exactly same sentence before.
 * The returned top 3 hot sentences should be sorted by hot degree (The first is
 * the hottest one). If several sentences have the same degree of hot, you need
 * to use ASCII-code order (smaller one appears first).
 * If less than 3 hot sentences exist, then just return as many as you can.
 * When the input is a special character, it means the sentence ends, and in
 * this case, you need to return an empty list.
 *
 * ======
 *
 * Source: Leetcode
 */
public class DesignSearchAutocompleteSystem {
    /**
     * Optimize with top 3 map in each node.
     */
    public static class AutocompleteSystem {
        StringBuilder cur;
        Trie t;

        public AutocompleteSystem(String[] s, int[] times) {
            cur = new StringBuilder();
            t = new Trie();
            for (int i = 0; i < s.length; i++) {
                t.put(s[i], times[i]);
            }
        }

        public List<String> input(char c) {
            if (c == '#') {
                t.put(cur.toString());
                t.prevNode = null;
                t.prevSb = null;
                t.failedSearch = false;
                cur = new StringBuilder();
                return new LinkedList<>();
            }
            cur.append(c);
            return getTop();
        }

        private List<String> getTop() {
            List<String> ans = new LinkedList<>();
            if (t.failedSearch) return ans;
            PriorityQueue<Item> pq = new PriorityQueue<>((a, b) -> {
                if (b.c == a.c) {
                    return a.s.compareTo(b.s);
                }
                return b.c - a.c;
            });
            pq.addAll(t.search(cur.toString()));
            int k = 3;
            while (--k >= 0 && !pq.isEmpty()) {
                ans.add(pq.poll().s);
            }
            return ans;
        }

        class Item {
            String s;
            int c;

            Item(String str, int count) {
                s = str;
                c = count;
            }
        }

        class Trie {
            Node root;
            StringBuilder prevSb;
            Node prevNode;
            boolean failedSearch;

            Trie() {
                root = new Node();
            }

            // increment count if exists, or put 1
            void put(String s) {
                put(s, 1);
            }

            void put(String s, int count) {
                char[] ca = s.toCharArray();
                int n = ca.length;
                Node cur = root;
                for (int i = 0; i < n; i++) {
                    if (!cur.contains(ca[i])) {
                        cur.put(ca[i]);
                    }
                    cur = cur.get(ca[i]);
                }
                if (cur.isEnd()) {
                    cur.count++;
                } else {
                    cur.setEnd();
                    cur.count = count;
                }
            }

            List<Item> search(String s) {
                List<Item> ans = new LinkedList<>();
                char[] ca = s.toCharArray();
                int n = ca.length;

                StringBuilder sb = new StringBuilder();
                Node cur = root;
                int start = 0;
                if (prevNode != null) {
                    cur = prevNode;
                    sb = prevSb;
                    start = n - 1;
                }

                for (int i = start; i < n; i++) {
                    if (!cur.contains(ca[i])) {
                        failedSearch = true;
                        return ans;
                    }
                    sb.append(ca[i]);
                    prevSb = sb;
                    cur = cur.get(ca[i]);
                    prevNode = cur;
                }

                dfs(sb, cur, ans);
                return ans;
            }

            void dfs(StringBuilder sb, Node cur, List<Item> ans) {
                if (cur.isEnd()) {
                    ans.add(new Item(sb.toString(), cur.count));
                }
                for (int i = 0; i < 256; i++) {
                    if (cur.contains(i)) {
                        sb.append((char) i);
                        dfs(sb, cur.get(i), ans);
                        sb.deleteCharAt(sb.length() - 1);
                    }
                }
            }

            class Node {
                int R = 256;
                Node[] children;
                int count;
                boolean isEnd;

                Node() {
                    children = new Node[R];
                }

                Node get(char c) {
                    return children[c];
                }

                boolean contains(char c) {
                    return children[c] != null;
                }

                void put(char c) {
                    children[c] = new Node();
                }

                void setEnd() {
                    isEnd = true;
                }

                boolean contains(int c) {
                    return children[c] != null;
                }

                boolean isEnd() {
                    return isEnd;
                }

                Node get(int c) {
                    return children[c];
                }
            }
        }
    }
}
