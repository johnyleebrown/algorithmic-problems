package Medium.Graph;

import static Helpers.Helper.replaceBracets;

import java.util.Arrays;
import java.util.Stack;

/**
 * 785
 * Determining whether an undirected graph is bipartite or whether it has an odd-length cycle.
 */
public class IsGraphBipartite {
    /**
     * Time complexity: O(N + E)
     * Space complexity: O(N)
     */
    public static boolean solution1(int[][] graph) {
        boolean isBipartite = true;
        boolean[] marked = new boolean[graph.length];
        boolean[] color = new boolean[graph.length];

        for (int i = 0 ; i < graph.length ; i++) {
            if (!isBipartite)   return false;
            if (!marked[i])     isBipartite = bfs(graph, color, marked, i);
        }

        return isBipartite;
    }

    private static boolean bfs(int[][] graph, boolean[] color, boolean[] marked, int v) {
        marked[v] = true;
        boolean isBipartite = true;

        for (int w : graph[v]) {
            if (!marked[w]) {
                color[w] = !color[v];
                isBipartite &= bfs(graph, color, marked, w);
            } else if (color[w] == color[v]) return false;
        }

        return isBipartite;
    }

    /**
     * Time complexity: O(N + E)
     * Space complexity: O(N)
     */
    public static boolean solution2(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        Arrays.fill(color, -1);

        for (int start = 0; start < n; ++start) {
            if (color[start] == -1) {
                Stack<Integer> stack = new Stack<>();
                stack.push(start);
                color[start] = 0;

                while (!stack.empty()) {
                    Integer node = stack.pop();
                    for (int nei: graph[node]) {
                        if (color[nei] == -1) {
                            stack.push(nei);
                            color[nei] = color[node] ^ 1;
                        } else if (color[nei] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    public static void main(String[] args) {
        // System.out.println(v + " " + "| marked: " + marked[v] + " | color: " + color[v] + " | " + Arrays.toString(graph[v]));
        String arr = "[[3,4,6],[3,6],[3,6],[0,1,2,5],[0,7,8],[3],[0,1,2,7],[4,6],[4],[]]";
        System.out.println(solution1(replaceBracets(arr)));
    }
}
