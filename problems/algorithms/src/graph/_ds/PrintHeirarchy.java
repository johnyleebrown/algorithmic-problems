package graph._ds;

import java.util.*;

/**
 * PrintHeirarchy
 *
 * ======
 *
 * Task.
 *
 * Print Heirarchy
 *
 * Input:
 * dog, poodle
 * mammal, dog
 * mammal, cat
 * dog, bulldog
 * dog, terrier
 * Output:
 * mammal
 * dog
 * poodle
 * bulldog
 * terrier
 * cat
 *
 * ======
 *
 * Company: Google
 * Source: Leetcode
 */
public class PrintHeirarchy {
    /**
     * We assume a[i][1] depends on a[i][0]
     */
    public static class Solution {
        private static final String LEVEL = "____";

        public void solve(String[][] a) {
            Map<String, List[]> g = new HashMap<>();
            for (String[] s : a) {
                g.putIfAbsent(s[1], new List[2]);
                if (g.get(s[1])[0] == null) g.get(s[1])[0] = new LinkedList<>();
                g.get(s[1])[0].add(s[0]);
                g.putIfAbsent(s[0], new List[2]);
                if (g.get(s[0])[1] == null) g.get(s[0])[1] = new LinkedList<>();
                g.get(s[0])[1].add(s[1]);
            }
//            if (hasCycle(g)) {
//                throw new RuntimeException("Has a cycle.");
//            }
//            dfs(g);
        }

        private void dfs(Map<String, List<List<String>>> g) {
            Set<String> globalSeen = new HashSet<>();
            for (String v : g.keySet()) {
                String root = dfs(v, g, globalSeen);
                dfs2(root, g, 0);
            }
        }

        private void dfs2(String v, Map<String, List<List<String>>> g, int level) {
            System.out.println(genString(level) + v);
            for (String w : g.get(v).get(1)) {
                dfs2(w, g, level + 1);
            }
        }

        private String dfs(String v, Map<String, List<List<String>>> g, Set<String> globalSeen) {
            if (!globalSeen.add(v)) return null;
            for (String w : g.getOrDefault(v, new LinkedList<>()).get(0)) {
                String ans = dfs(w, g, globalSeen);
                if (ans != null) return ans;
            }
            return v;
        }

        private String genString(int n) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < n; i++) sb.append(LEVEL);
            return sb.toString();
        }

        private boolean hasCycle(Map<String, List<List<String>>> g) {
            Set<String> globalSeen = new HashSet<>();
            for (String v : g.keySet()) {
                if (hasCycle(v, g, globalSeen, new HashSet<>())) {
                    return true;
                }
            }
            return false;
        }

        private boolean hasCycle(String v, Map<String, List<List<String>>> g, Set<String> globalSeen, Set<String> localSeen) {
            if (localSeen.contains(v)) return true;
            if (globalSeen.contains(v)) return false;
            localSeen.add(v);
            globalSeen.add(v);
            for (String w : g.getOrDefault(v, new LinkedList<>()).get(0)) {
                if (hasCycle(w, g, globalSeen, localSeen)) {
                    return true;
                }
            }
            return false;
        }
    }

    public static void main(String[] args) {
        new Solution().solve(new String[][]{{"dog", "poodle"}, {"mammal", "dog"}, {"mammal", "cat"}, {"dog", "bulldog"}, {"dog", "terrier"}});
    }
}