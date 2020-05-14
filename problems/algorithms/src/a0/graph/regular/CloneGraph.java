package a0.graph.regular;

import util.ds.Node;

import java.util.HashMap;
import java.util.Map;

/**
 * 133
 */
public class CloneGraph {
    public static class Solution {
        Map<Integer, Node> m = new HashMap<>();

        public Node cloneGraph(Node cur) {
            if (cur == null) return null;
            if (m.containsKey(cur.val)) return m.get(cur.val);
            Node copy = new Node(cur.val);
            m.put(cur.val, copy);
            for (Node n : cur.neighbors) {
                copy.neighbors.add(cloneGraph(n));
            }
            return copy;
        }
    }
}