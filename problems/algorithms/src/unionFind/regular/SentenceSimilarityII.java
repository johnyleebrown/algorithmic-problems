package unionFind.regular;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 737
 */
public class SentenceSimilarityII {
    public static class Solution {
        public boolean areSentencesSimilarTwo(String[] w1, String[] w2, List<List<String>> pairs) {
            if (w1.length != w2.length) return false;
            UnionFind uf = new UnionFind();
            for (List<String> p : pairs) {
                uf.union(p.get(0), p.get(1));
            }
            for (int i = 0; i < w1.length; i++) {
                if (w1[i].equals(w2[i])) continue;
                if (uf.connected(w1[i], w2[i])) return false;
            }
            return true;
        }

        static class UnionFind {
            Map<String, String> parents;
            Map<String, Integer> rank;

            UnionFind() {
                parents = new HashMap<>();
                rank = new HashMap<>();
            }

            void union(String p, String q) {
                String parentP = find(p);
                String parentQ = find(q);
                if (parentP.equals(parentQ)) return;
                if (rank.get(parentP) > rank.get(parentQ)) {
                    parents.put(parentQ, parentP);
                } else if (rank.get(parentP) < rank.get(parentQ)) {
                    parents.put(parentP, parentQ);
                } else {
                    parents.put(parentQ, parentP);
                    rank.put(parentP, rank.get(parentP) + 1);
                }
            }

            String find(String p) {
                parents.putIfAbsent(p, p);
                rank.putIfAbsent(p, 0);
                while (!parents.get(p).equals(p)) {
                    parents.put(p, parents.get(parents.get(p)));
                    p = parents.get(p);
                }
                return p;
            }

            boolean connected(String p, String q) {
                return !find(p).equals(find(q));
            }
        }
    }
}