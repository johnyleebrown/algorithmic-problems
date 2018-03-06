package Medium.Graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * 133
 */
public class CloneGraph {
    /**
     * Time complexity: O(E+V)
     * Space complexity: O(V)
     */
    public UndirectedGraphNode solution(UndirectedGraphNode node) {
        if (node == null) return null;
        return dfs(node, new HashMap<>());
    }

    private UndirectedGraphNode dfs(UndirectedGraphNode node, HashMap<Integer, UndirectedGraphNode> map) {
        if (node == null) return null;
        if (map.containsKey(node.label)) {
            return map.get(node.label);
        } else {
            UndirectedGraphNode clone = new UndirectedGraphNode(node.label);
            map.put(node.label, clone);
            for (UndirectedGraphNode n : node.neighbors)
                clone.neighbors.add(dfs(n, map));
            return clone;
        }
    }

    static class UndirectedGraphNode {
        int label;
        List<UndirectedGraphNode> neighbors;

        UndirectedGraphNode(int x) {
            label = x;
            neighbors = new ArrayList<>();
        }
    }

    public static void main(String[] args) {
        UndirectedGraphNode undirectedGraphNode = new UndirectedGraphNode(1);
        UndirectedGraphNode undirectedGraphNode2 = new UndirectedGraphNode(2);
        UndirectedGraphNode undirectedGraphNode3 = new UndirectedGraphNode(3);
        undirectedGraphNode.neighbors.add(undirectedGraphNode2);
        undirectedGraphNode2.neighbors.add(undirectedGraphNode3);
        undirectedGraphNode3.neighbors.add(undirectedGraphNode);

        CloneGraph cloneGraph = new CloneGraph();
        UndirectedGraphNode u = cloneGraph.solution(undirectedGraphNode);
        u.neighbors.stream().forEach((e) -> System.out.println(e.label));

    }
}
