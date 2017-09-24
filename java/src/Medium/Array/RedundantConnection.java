package Medium.Array;

import java.util.HashMap;

/**
 * 684
 * We are given a "tree" in the form of a 2D-array, with distinct values for each node.
 * In the given 2D-array, each element pair [u, v] represents that v is a child of u in the tree.
 * We can remove exactly one redundant pair in this "tree" to make the result a tree.
 * You need to find and output such a pair. If there are multiple answers for this question,
 * output the one appearing last in the 2D-array. There is always at least one answer.
 */
public class RedundantConnection {
    // UF
    class Solution {
        public int[] findRedundantConnection(int[][] edges) {
            int[] parent = new int[2001];
            for (int i = 0; i < parent.length; i++) parent[i] = i;
            for (int[] edge : edges) {
                int f = edge[0], t = edge[1];
                if (find(parent, f) == find(parent, t)) return edge;
                else parent[find(parent, f)] = find(parent, t);
            }
            return new int[2];
        }

        private int find(int[] parent, int f) {
            if (f != parent[f]) parent[f] = find(parent, parent[f]);
            return parent[f];
        }
    }

    class Solution2 {
        public int[] findRedundantConnection(int[][] edges) {
            HashMap<Integer, Integer> map = new HashMap<>();
            int[] res = null;
            for(int i=0; i<edges.length; i++){
                int[] p = edges[i];
                int a = searchRoot(p[0], map);
                int b = searchRoot(p[1], map);
                if(a == b){
                    res = p;
                    continue;
                }
                else map.put(a,b);
            }
            return res;
        }

        private int searchRoot(int v, HashMap<Integer, Integer> map){
            while(map.containsKey(v)) v = map.get(v);
            return v;
        }

    }
}
