package Medium.Graph;

import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

/**
 * 802
 *
 * In a directed graph, we start at some node and every turn, walk along a
 * directed edge of the graph.  If we reach a node that is terminal (that
 * is, it has no outgoing directed edges), we stop. Now, say our starting
 * node is eventually safe if and only if we must eventually walk to a
 * terminal node.  More specifically, there exists a natural number K so
 * that for any choice of where to walk, we must have stopped at a terminal
 * node in less than K steps. Which nodes are eventually safe?  Return them
 * as an array in sorted order. The directed graph has N nodes with labels
 * 0, 1, ..., N-1, where N is the length of graph.  The graph is given in
 * the following form: graph[i] is a list of labels j such that (i, j) is
 * a directed edge of the graph.
 *
 * Example:
 * Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
 * Output: [2,4,5,6]
 */
public class FindEventualSafeStates {
    public class solution{
        public List<Integer> eventualSafeNodes(int[][] graph) {
            List<Integer> list = new LinkedList<>();

            boolean[] marked = new boolean[graph.length];
            boolean[] banned = new boolean[graph.length];

            for (int i = 0; i < graph.length; i++) {
                if (!marked[i])
                    if (dfs(list, graph, marked, i, banned) && !banned[i])
                        list.add(i);
            }

            list.sort(Comparator.comparingInt(Integer::intValue));
            return list;
        }

        private boolean dfs(List<Integer> list, int[][] graph, boolean[] marked, int v, boolean[] banned) {
            marked[v] = true;
            banned[v] = true;

            for (int w : graph[v]) {
                if (!marked[w])
                    if (dfs(list, graph, marked, w, banned))
                        return true;

                if (banned[w])
                    return true;
            }

            banned[v] = false;
            list.add(v);
            return false;
        }
    }

    public class solution1{
        public List<Integer> eventualSafeNodes(int[][] graph) {
            List<Integer> list = new LinkedList<>();

            boolean[] marked = new boolean[graph.length];
            Set<Integer> set = new HashSet<>();

            for (int i = 0; i < graph.length; i++) {
                if (!marked[i]) {
                    dfs(list, graph, marked, set, i);
                }
            }
            list.sort(Comparator.comparingInt(Integer::intValue));
            return list;
        }

        void dfs(List<Integer> list, int[][] graph, boolean[] marked, Set<Integer> set, int v) {
            marked[v] = true;
            set.add(v);
            list.add(v);
            for (int w : graph[v]) {
                if (set.contains(w)) {
                    set.forEach(list::remove);
                }
                if (!marked[w])
                    dfs(list, graph, marked, set, w);
            }
            if (list.contains(v)) set.remove(v);
        }
    }

    private static boolean less(int max, int des) {
        int i = 0;
        for (int j = 1; j <= max; j++) i += j;
        return i < des;
    }

    public static void main(String[] args) {

        System.out.println(less(10, 100));

//        System.out.println(Arrays.toString(eventualSafeNodes(Helper.replaceBracets("[[1,2],[2,3],[5],[0],[5],[],[]]")).toArray()));

//        System.out.println(Arrays.toString(eventualSafeNodes(Helper.replaceBracets("[[0],[2,3,4],[3,4],[0,4],[]]")).toArray()));

//        System.out.println(Arrays.toString(eventualSafeNodes(Helper.replaceBracets("[[5],[2,3],[5],[0],[5],[],[]]")).toArray()));
    }
}
